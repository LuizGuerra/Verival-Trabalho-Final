package com.verival.tf.entidades;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.sun.javafx.geom.Area;
import com.verival.tf.entidades.geometria.Ponto;
import com.verival.tf.entidades.geometria.Reta;
import com.verival.tf.entidades.geometria.SituacaoReta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;
import org.junit.jupiter.api.Assertions;

public class RoteiroTest {
    private List<Bairro> districts;
    // private RepositorioBairrosImplMem districtData;
    private Roteiro roteiro;

    @BeforeEach
    public void setup() {
        districts = new ArrayList<>(
            Arrays.asList(
                Bairro.novoBairroQuadrado   ("B1", (new Ponto(0,6)), 2,     10),
                Bairro.novoBairroRetangular ("B2", (new Ponto(2,6)), 4, 2,  10),
                Bairro.novoBairroRetangular ("B3", (new Ponto(0,4)), 2, 4,  10),
                Bairro.novoBairroQuadrado   ("B4", (new Ponto(2,4)), 2,     10),
                Bairro.novoBairroQuadrado   ("B5", (new Ponto(4,4)), 2,     10),
                Bairro.novoBairroRetangular ("B6", (new Ponto(2,2)), 4, 2,  10)
                )
            );
            mock(districts.get(0).getArea().getClass());
            mock(districts.get(1).getArea().getClass());
            mock(districts.get(2).getArea().getClass());
            mock(districts.get(3).getArea().getClass());
            mock(districts.get(4).getArea().getClass());
            mock(districts.get(5).getArea().getClass());
    }

    @Test
    public void routWithInexistentDistrictTest() {
        Bairro fakeDistrict = Bairro.novoBairroQuadrado("B99", (new Ponto(10, 10)), 3, 10);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            roteiro = new Roteiro(districts.get(0), fakeDistrict, districts);
        });

        String expectedMessage = "Impossible to calculate route with a district outside off the map.";
        String actualMessage = exception.getMessage();
                            
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void routWithNullDistrictTest() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            roteiro = new Roteiro(districts.get(0), null, districts);
        });

        String expectedMessage = "Impossible to calculate route with a district outside off the map.";
        String actualMessage = exception.getMessage();
                            
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void lineThatRunsThroughTwoDistrictsTest() {
        roteiro = new Roteiro(districts.get(0), districts.get(1), districts);
        final Reta expected = new Reta((new Ponto(1, 5)), (new Ponto(4,5)));

        when(districts.get(0).getArea().classifica(expected)).thenReturn(SituacaoReta.INTERSECTA);
        when(districts.get(1).getArea().classifica(expected)).thenReturn(SituacaoReta.INTERSECTA);
        when(districts.get(2).getArea().classifica(expected)).thenReturn(SituacaoReta.TODA_FORA);
        when(districts.get(3).getArea().classifica(expected)).thenReturn(SituacaoReta.TODA_FORA);
        when(districts.get(4).getArea().classifica(expected)).thenReturn(SituacaoReta.TODA_FORA);
        when(districts.get(5).getArea().classifica(expected)).thenReturn(SituacaoReta.TODA_FORA);
        

        final Reta actual = roteiro.getRota();
        final Boolean equalLines = pointsAreEqual(expected.getP1(), actual.getP1()) && pointsAreEqual(expected.getP2(), actual.getP2());

        assertTrue(equalLines);
    }

    @Test
    public void lineThatRunsDiagonallyThroghMapTest() {
        roteiro = new Roteiro(districts.get(0), districts.get(5), districts);
        final Reta expected = new Reta( (new Ponto(1,5)), (new Ponto(4,1)) );
        final Reta actual = roteiro.getRota();
        final Boolean equalLines = pointsAreEqual(expected.getP1(), actual.getP1()) && pointsAreEqual(expected.getP2(), actual.getP2());
        assertTrue(equalLines);
    }

    @Test
    private void coveredDistrictsForSimpleRoutTest() {
        roteiro = new Roteiro(districts.get(0), districts.get(2), districts);
        List<Bairro> expected = new ArrayList<>(Arrays.asList(districts.get(0), districts.get(2)));
        Collection<Bairro> actual = roteiro.bairrosPercoridos();

        assertTrue(actual.equals(expected));
    }

    @Test
    private void coveredDistrictsForDiagonalComplexRoutTest() {
        roteiro = new Roteiro(districts.get(0), districts.get(5), districts);
        List<Bairro> expected = new ArrayList<>(Arrays.asList(
            districts.get(0), districts.get(2), districts.get(3), districts.get(5)
            ));
        Collection<Bairro> actual = roteiro.bairrosPercoridos();

        assertTrue(actual.equals(expected));
    }

    private Boolean pointsAreEqual(Ponto p1, Ponto p2) {
        return p1.getX() == p2.getX() && p1.getY() == p2.getY();
    }
    
}