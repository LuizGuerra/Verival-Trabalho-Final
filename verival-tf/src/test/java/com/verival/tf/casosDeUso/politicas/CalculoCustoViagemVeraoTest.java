package com.verival.tf.casosDeUso.politicas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import com.verival.tf.casosDeUso.Politicas.CalculoCustoViagemVerao;
import com.verival.tf.entidades.Passageiro;
import com.verival.tf.entidades.Roteiro;
import com.verival.tf.entidades.Bairro;

public class CalculoCustoViagemVeraoTest {
    private CalculoCustoViagemVerao ccvv;
    private Roteiro rout;
    private Passageiro passenger;
    
    @BeforeEach
    public void setup() {
        rout = mock(Roteiro.class);
        passenger = mock(Passageiro.class);
        ccvv = new CalculoCustoViagemVerao(rout, passenger);
    }

    @Test
    public void lowPontuationForNormalDiscountTest() {

        Bairro b1 = mock(Bairro.class);
        Bairro b2 = mock(Bairro.class);
        when(b1.getCustoTransporte()).thenReturn(10.0);
        when(b2.getCustoTransporte()).thenReturn(20.0);

        when(rout.bairrosPercorridos()).thenReturn(new ArrayList<Bairro>(
            Arrays.asList(b1, b2)
        ));

        when(passenger.getPontuacaoMedia()).thenReturn(5);
        final double expected = 0;
        final double actual = ccvv.descontoPontuacao();
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void highPontuationForNormalDiscountTest() {
        Bairro b1 = mock(Bairro.class);
        Bairro b2 = mock(Bairro.class);
        when(b1.getCustoTransporte()).thenReturn(10.0);
        when(b2.getCustoTransporte()).thenReturn(20.0);

        when(rout.bairrosPercorridos()).thenReturn(new ArrayList<Bairro>(
            Arrays.asList(b1, b2)
        ));

        when(passenger.getPontuacaoMedia()).thenReturn(10);
        final double expected = 2.7;
        final double actual = ccvv.descontoPontuacao();
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void smallRoutSeasonalDiscountTest() {
        Bairro b1 = mock(Bairro.class);
        when(b1.getCustoTransporte()).thenReturn(10.0);
        when(rout.bairrosPercorridos()).thenReturn(new ArrayList<Bairro>(
            Arrays.asList(b1)
        ));
        final double expected = 0;
        final double actual = ccvv.descontoPromocaoSazonal();

        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void bigRoutForSeasonalDiscountTest() {
        Bairro b1 = mock(Bairro.class);
        Bairro b2 = mock(Bairro.class);
        Bairro b3 = mock(Bairro.class);
        when(b1.getCustoTransporte()).thenReturn(10.0);
        when(b2.getCustoTransporte()).thenReturn(10.0);
        when(b3.getCustoTransporte()).thenReturn(10.0);
        when(rout.bairrosPercorridos()).thenReturn(new ArrayList<Bairro>(
            Arrays.asList(b1, b2, b3)
        ));
        final double expected = 3;
        final double actual = ccvv.descontoPromocaoSazonal();

        assertEquals(expected, actual, 0.001);
    }

}