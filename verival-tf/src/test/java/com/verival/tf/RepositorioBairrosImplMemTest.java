package com.verival.tf;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.verival.tf.interfaces.Persistencia.RepositorioBairrosImplMem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class RepositorioBairrosImplMemTest {
    private RepositorioBairrosImplMem rbTeste;

    @BeforeEach
    public void setup() {
        rbTeste = new RepositorioBairrosImplMem();
    }

    @Test
    public void recuperaNomeExistente() {
        String expected = "Bom Fim";
        String actual = rbTeste.recuperaPorNome(expected).getNome();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void recuperaNomeInexistente() {

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            rbTeste.recuperaPorNome("Madara");
          });
      
          final String expectedMessage = "Bairro inexistente: Madara";
          final String actualMessage = exception.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
    //stonks
}