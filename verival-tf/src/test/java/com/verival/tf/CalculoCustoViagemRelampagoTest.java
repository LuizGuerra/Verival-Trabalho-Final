package com.verival.tf;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.verival.tf.casosDeUso.Politicas.CalculoCustoViagemRelampago;
import com.verival.tf.entidades.Passageiro;
import com.verival.tf.entidades.Roteiro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class CalculoCustoViagemRelampagoTest {
    private CalculoCustoViagemRelampago calcTest;
    private Passageiro passageiro;
    private Roteiro roteiro;

    @BeforeEach
    public void setup() {
        calcTest = new CalculoCustoViagemRelampago();
        calcTest.definePassageiro(passageiro);
        calcTest.defineRoteiro(roteiro);

    }

}