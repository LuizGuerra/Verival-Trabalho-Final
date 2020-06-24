package com.verival.tf;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.verival.tf.interfaces.Persistencia.RepositorioPassageirosImplMem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class RepositorioPassageirosImplMemTest {
    private RepositorioPassageirosImplMem rpTest;

    @BeforeEach
    public void setup() {
        rpTest = new RepositorioPassageirosImplMem();

    }

    @Test
    public void recuperaCPFExistente() {
        String expected = "123456789";
        String actual = rpTest.recuperaPorCPF(expected).getCPF();
        Assertions.assertEquals(expected, actual);
    }

    //CORRIGIR ESSE TESTE!!!
    @Test
    public void recuperaCPFInexistente() {

        final String expectedMessage = null;
        final String actualMessage = rpTest.recuperaPorCPF("0123456789").getCPF();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));

    }
    ///////////////////////////

    





}