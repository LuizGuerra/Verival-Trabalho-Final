package com.verival.tf.casosDeUso.politicas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.verival.tf.casosDeUso.Politicas.CalculoCustoViagem;
import com.verival.tf.casosDeUso.Politicas.CalculoCustoViagemBasico;
import com.verival.tf.casosDeUso.Politicas.CustoViagem;
import com.verival.tf.entidades.Passageiro;
import com.verival.tf.entidades.Roteiro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustoViagemTest {
    private CalculoCustoViagemBasico calculoCustoViagemBasico;
    private CustoViagem custoViagem;

    @BeforeEach
    public void setup() {
        calculoCustoViagemBasico = mock(CalculoCustoViagemBasico.class);
        custoViagem = new CustoViagem(calculoCustoViagemBasico);
    }

    @Test
    public void CustoViagemInitializationTest() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new CustoViagem(null);
        });
        final String expectedMessage = "Object on initialization cannot be null";
        final String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void invalidRoutTest() {
        Passageiro passenger = mock(Passageiro.class);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            custoViagem.custoViagem(null, passenger);
        });
        final String expectedMessage = "Null rout instead of declared object";
        final String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void invalidPassengerTest() {
        Roteiro rout = mock(Roteiro.class);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            custoViagem.custoViagem(rout, null);
        });
        final String expectedMessage = "Null passenger instead of declared object";
        final String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void calculateTripCostTest() {
        Roteiro rout = mock(Roteiro.class);
        Passageiro passenger = mock(Passageiro.class);
        when(calculoCustoViagemBasico.custoViagem()).thenReturn(10.0);

        double expected = 10;
        double actual = custoViagem.custoViagem(rout, passenger);

        assertEquals(expected, actual);
    }



}