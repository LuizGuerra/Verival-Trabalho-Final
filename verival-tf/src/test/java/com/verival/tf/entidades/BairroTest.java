package com.verival.tf.entidades;

import com.verival.tf.entidades.Bairro;
import com.verival.tf.entidades.geometria.Area;
import com.verival.tf.entidades.geometria.Ponto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class BairroTest {
  private Bairro bairro;
  private Area area;
  private Ponto pontoSupEsq;
  private Ponto pontoInfDir;

  @BeforeEach
  public void setup() {
    pontoSupEsq = new Ponto(-5, 0);
    pontoInfDir = new Ponto(0, -5);

    area = new Area(pontoSupEsq, pontoInfDir);
  }

  @Test
  public void updateTransportCostTest() {
    bairro = new Bairro("Azenha", area, 5.50);
    bairro.alteraCustoTransporte(9.35);

    final double expected = 9.35;
    final double actual = bairro.getCustoTransporte();
    Assertions.assertEquals(expected, actual, 0.001);
  }

  @Test
  public void updateTransportCostWithLessThanZeroTest() {
    bairro = new Bairro("Azenha", area, 5.50);

    Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
      bairro.alteraCustoTransporte(0);
    });

    final String expectedMessage = "Valor invalido";
    final String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  public void getNameTest() {
    bairro = new Bairro("Menino Deus", area, 5.50);
    final String expected = "Menino Deus";
    final String actual = bairro.getNome();

    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void getTransportCost() {
    bairro = new Bairro("Menino Deus", area, 5.50);

    final double expected = 5.50;
    final double actual = bairro.getCustoTransporte();

    Assertions.assertEquals(expected, actual, 0.001);
  }

  @Test
  public void createSquareDistrictTest() {
    pontoSupEsq = new Ponto(-5, 0);

    Bairro novoBairroQuadrado = Bairro.novoBairroQuadrado("Menino Deus", pontoSupEsq, 2, 5.50);
    Ponto pInfDir = novoBairroQuadrado.getArea().getPInfDir();

    Assertions.assertTrue((pInfDir.getX() == -3) && (pInfDir.getY() == -2));
  }

  @Test
  public void createRectangleDistrictTest() {
    pontoSupEsq = new Ponto(-5, 0);

    Bairro novoBairroRetangular = Bairro.novoBairroRetangular("Menino Deus", pontoSupEsq, 5, 3, 5.50);
    Ponto pInfDir = novoBairroRetangular.getArea().getPInfDir();

    Assertions.assertTrue((pInfDir.getX() == 0) && (pInfDir.getY() == -3));
  }
}