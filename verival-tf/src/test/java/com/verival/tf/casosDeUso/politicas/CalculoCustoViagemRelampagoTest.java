package com.verival.tf.casosDeUso.politicas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import com.verival.tf.casosDeUso.Politicas.CalculoCustoViagemRelampago;
import com.verival.tf.entidades.Bairro;
import com.verival.tf.entidades.Passageiro;
import com.verival.tf.entidades.Roteiro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculoCustoViagemRelampagoTest {
    private CalculoCustoViagemRelampago ccvr;
    private Roteiro rout;
    private Passageiro passenger;

    @BeforeEach
    public void setup() {
        rout = mock(Roteiro.class);
        passenger = mock(Passageiro.class);
        ccvr = new CalculoCustoViagemRelampago(rout, passenger);
    }

    @Test
    public void passengerWithLessThanThirtyRidesAndRegularDiscountTest() {
        when(passenger.getQtdadeAvaliacoes()).thenReturn(0);

        final double expected = 0;
        final double actual = ccvr.descontoPontuacao();
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void passengerWithLowPontuationAndRegularDiscountTest() {
        when(passenger.getQtdadeAvaliacoes()).thenReturn(100);
        when(passenger.getPontuacaoMedia()).thenReturn(1);

        final double expected = 0;
        final double actual = ccvr.descontoPontuacao();
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void passengerWithMoreThanThirtyTripsAndHighPontuationAndRegularDiscountTest() {
        Bairro b1 = mock(Bairro.class);
        Bairro b2 = mock(Bairro.class);
        when(b1.getCustoTransporte()).thenReturn(10.0);
        when(b2.getCustoTransporte()).thenReturn(20.0);
        when(rout.bairrosPercorridos()).thenReturn(new ArrayList<Bairro>(
            Arrays.asList(b1, b2)
        ));
        when(passenger.getQtdadeAvaliacoes()).thenReturn(100);
        when(passenger.getPontuacaoMedia()).thenReturn(10);

        final double expected = 30*0.05;
        final double actual = ccvr.descontoPontuacao();
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void sazonalDiscountWithSmallRoutTest() {
        Bairro b1 = mock(Bairro.class);
        Bairro b2 = mock(Bairro.class);
        when(b1.getCustoTransporte()).thenReturn(10.0);
        when(b2.getCustoTransporte()).thenReturn(20.0);
        when(rout.bairrosPercorridos()).thenReturn(new ArrayList<Bairro>(
            Arrays.asList(b1, b2)
        ));

        final double expected = 0;
        final double actual = ccvr.descontoPromocaoSazonal();
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void sazonalDiscountWithBigRoutTest() {
        Bairro b1 = mock(Bairro.class);
        Bairro b2 = mock(Bairro.class);
        Bairro b3 = mock(Bairro.class);
        Bairro b4 = mock(Bairro.class);
        when(b1.getCustoTransporte()).thenReturn(10.0);
        when(b2.getCustoTransporte()).thenReturn(10.0);
        when(b3.getCustoTransporte()).thenReturn(10.0);
        when(b4.getCustoTransporte()).thenReturn(10.0);
        when(rout.bairrosPercorridos()).thenReturn(new ArrayList<Bairro>(
            Arrays.asList(b1, b2, b3, b4)
        ));

        final double expected = 40*0.05;
        final double actual = ccvr.descontoPromocaoSazonal();
        assertEquals(expected, actual, 0.001);
    }
    
}