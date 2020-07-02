package com.verival.tf.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PassageiroTest {
  Passageiro passageiro;

  @Test
  public void createANewUserTest() {
    passageiro = Passageiro.novoPassageiro("864.452.555-75", "Marlon");

    assertTrue(passageiro.getCPF().equals("864.452.555-75"));
    assertTrue(passageiro.getNome().equals("Marlon"));
  }

  @Test
  public void updateExistingUser() {
    passageiro = Passageiro.passageiroExistente("864.452.555-75", "Marlon", 20, 5);

    assertTrue(passageiro.getCPF().equals("864.452.555-75"));
    assertTrue(passageiro.getNome().equals("Marlon"));
    assertTrue(passageiro.getPontuacaoAcumulada() == 20);
    assertTrue(passageiro.getQtdadeAvaliacoes() == 5);
  }

  @Test
  public void getAverageScoreTest() {
    passageiro = Passageiro.novoPassageiro("864.452.555-75", "Marlon");

    passageiro.infoPontuacao(9);

    final int expected = 8;
    final int actual = passageiro.getPontuacaoMedia();

    assertEquals(expected, actual);
  }
}