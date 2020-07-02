package com.verival.tf.casosDeUso.Servicos;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.verival.tf.casosDeUso.Politicas.CalculoCustoViagem;
import com.verival.tf.casosDeUso.Repositorios.RepositorioBairros;
import com.verival.tf.casosDeUso.Repositorios.RepositorioPassageiros;
import com.verival.tf.entidades.Bairro;
import com.verival.tf.entidades.Passageiro;
import com.verival.tf.entidades.Roteiro;
import com.verival.tf.entidades.Viagem;

import com.verival.tf.interfaces.Persistencia.RepositorioBairrosImplMem;
import com.verival.tf.interfaces.Persistencia.RepositorioPassageirosImplMem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class ServicosPassageiroTest {
  Passageiro passageiro;
  Roteiro roteiro;
  Viagem viagem;
  RepositorioBairros repBairros;
  RepositorioPassageiros repPassageiros;
  ServicosPassageiro servicoPassageiro;
  CalculoCustoViagem ccv;

  @BeforeEach
  public void setup() {
    repBairros = new RepositorioBairrosImplMem();
    repPassageiros = new RepositorioPassageirosImplMem();
    servicoPassageiro = new ServicosPassageiro(repBairros, repPassageiros, ccv);
  }

  @Test
  public void createNewServiceTest() {
    assertTrue(servicoPassageiro.getListaBairros() != null);
    assertTrue(servicoPassageiro.getPassageirosCadastrados() != null);
  }

  @Test
  public void createNewRoadMapTest() {
    // roteiro = servicoPassageiro.criaRoteiro("Petropolis", "Vila Nova");

    Roteiro roteiro = mock(Roteiro.class);
    Collection<Bairro> bairrosPercorridos = new ArrayList<Bairro>();
    bairrosPercorridos.add(repBairros.recuperaPorNome("Petropolis"));
    bairrosPercorridos.add(repBairros.recuperaPorNome("Vila Nova"));

    when(roteiro.bairrosPercorridos()).thenReturn(bairrosPercorridos);

    Object[] actual = roteiro.bairrosPercorridos().toArray();
    Object[] expected = { repBairros.recuperaPorNome("Petropolis"), repBairros.recuperaPorNome("Vila Nova") };

    assertArrayEquals(expected, actual);
  }
}