package com.verival.tf.casosDeUso.politicas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import com.verival.tf.casosDeUso.Politicas.CalculoCustoViagemBasico;
import com.verival.tf.entidades.Bairro;
import com.verival.tf.entidades.Passageiro;
import com.verival.tf.entidades.Roteiro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculoCustoViagemBasicoTest {
    private Roteiro rout;
    private Passageiro passenger;
    private CalculoCustoViagemBasico ccvb;

    @BeforeEach
    public void setup() {
        rout = mock(Roteiro.class);
        passenger = mock(Passageiro.class);
        ccvb = new CalculoCustoViagemBasico(rout, passenger);
    }

    @Test
    public void calculatesRideCostTest() {
        Bairro b1 = mock(Bairro.class);
        Bairro b2 = mock(Bairro.class);
        when(b1.getCustoTransporte()).thenReturn(10.0);
        when(b2.getCustoTransporte()).thenReturn(20.0);

        when(rout.bairrosPercoridos()).thenReturn(new ArrayList<Bairro>(
            Arrays.asList(b1, b2)
        ));

        final double expected = 30;
        final double actual = ccvb.calculoCustoBasico();

        assertEquals(expected, actual);
    }

    @Test
    public void basicTripDiscountTest() {
        double expected = 0;
        double actual = ccvb.descontoPontuacao();
        assertEquals(expected, actual);
    }

    @Test
    public void basicTripSeasonalDiscountTest() {
        double expected = 0;
        double actual = ccvb.descontoPromocaoSazonal();
        assertEquals(expected, actual);
    }

    @Test
    public void tripCostTest() {
        Bairro b1 = mock(Bairro.class);
        Bairro b2 = mock(Bairro.class);
        when(b1.getCustoTransporte()).thenReturn(10.0);
        when(b2.getCustoTransporte()).thenReturn(20.0);

        when(rout.bairrosPercoridos()).thenReturn(new ArrayList<Bairro>(
            Arrays.asList(b1, b2)
        ));

        double expected = 30;
        double actual = ccvb.custoViagem();

        assertEquals(expected, actual);
    }
    
}