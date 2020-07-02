package com.verival.tf.entidades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class ViagemTest {
  @Test
  public void createANewTripTrest() {
    Passageiro passageiro = mock(Passageiro.class);
    Roteiro roteiro = mock(Roteiro.class);
    LocalDateTime dataHora = LocalDateTime.now();

    Viagem viagem = new Viagem(1, dataHora, roteiro, passageiro, 15.50);

    assertEquals(viagem.getId(), 1);
    assertEquals(viagem.getDataHora(), dataHora);
    assertEquals(viagem.getRoteiro(), roteiro);
    assertEquals(viagem.getPassageiro(), passageiro);
    assertEquals(viagem.getValorCobrado(), 15.50);
  }
}