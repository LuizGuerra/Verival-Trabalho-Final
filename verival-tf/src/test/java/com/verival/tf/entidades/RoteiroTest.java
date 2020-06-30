package com.verival.tf.entidades;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.verival.tf.entidades.geometria.Ponto;
import com.verival.tf.entidades.geometria.Reta;
import com.verival.tf.entidades.geometria.SituacaoReta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoteiroTest {
    private List<Bairro> districts;
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
    }

    @Test
    public void routWithNullOriginDistrictTest() {

        Exception exception = assertThrows(NullPointerException.class, () -> {
            roteiro = new Roteiro(null, districts.get(0), districts);
        });

        String expectedMessage = "Origin district cannot be null on initializer";
        String actualMessage = exception.getMessage();
                            
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void routWithNullDestinationDistrictTest() {

        Exception exception = assertThrows(NullPointerException.class, () -> {
            roteiro = new Roteiro(districts.get(0), null, districts);
        });

        String expectedMessage = "Destination district cannot be null on initializer";
        String actualMessage = exception.getMessage();
                            
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void nullMapTest() {

        Exception exception = assertThrows(NullPointerException.class, () -> {
            roteiro = new Roteiro(districts.get(0), districts.get(1), null);
        });

        String expectedMessage = "City map cannot be null on initializer";
        String actualMessage = exception.getMessage();
                            
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void emptyMapTest() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            roteiro = new Roteiro(districts.get(0), districts.get(1), (new ArrayList<Bairro>()));
        });

        String expectedMessage = "City map cannot be empty on initializer";
        String actualMessage = exception.getMessage();
                            
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void fakeOriginDistrictTest() {
        Bairro fakeDistrict = mock(Bairro.class);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            roteiro = new Roteiro(fakeDistrict, districts.get(0), districts);
        });

        String expectedMessage = "City map doesn't contain origin district";
        String actualMessage = exception.getMessage();
                            
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void fakeDestinationDistrictTest() {
        Bairro fakeDistrict = mock(Bairro.class);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            roteiro = new Roteiro(districts.get(0), fakeDistrict, districts);
        });

        String expectedMessage = "City map doesn't contain destination district";
        String actualMessage = exception.getMessage();
                            
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void integrationTest() {
        roteiro = mock(Roteiro.class);
        Collection<Bairro> expected = new ArrayList<>(
            Arrays.asList(districts.get(0), districts.get(1))
        );
        when(roteiro.bairrosPercoridos()).thenReturn(expected);
        Collection<Bairro> actual = roteiro.bairrosPercoridos();

        assertTrue(expected.equals(actual));
    }
    
}