package com.verival.tf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.verival.tf.entidades.geometria.Area;
import com.verival.tf.entidades.geometria.Ponto;
import com.verival.tf.entidades.geometria.Reta;
import com.verival.tf.entidades.geometria.SituacaoReta;

import org.junit.Test;

public class AreaTest {
  private Ponto pSupEsq;
  private Ponto pInfDir;
  private Area area;

  @Test
  public void getCentralPointOfAreaTestWithPositiveParamsTest() {
    pSupEsq = new Ponto(1, 5);
    pInfDir = new Ponto(5, 1);

    area = new Area(pSupEsq, pInfDir);

    Ponto expected = new Ponto(3, 3);
    Ponto actual = area.pontoCentral();

    assertTrue(expected.getX() == actual.getX() && expected.getY() == actual.getY());
  }

  @Test
  public void getCentralPointOfAreaTestWithNegativeParamsTest() {
    pSupEsq = new Ponto(-10, -2);
    pInfDir = new Ponto(-2, -6);

    area = new Area(pSupEsq, pInfDir);

    Ponto expected = new Ponto(-6, -4);
    Ponto actual = area.pontoCentral();

    assertTrue(expected.getX() == actual.getX() && expected.getY() == actual.getY());
  }

  @Test
  public void getCentralPointWithNegativeAndPositiveNumbersTest() {
    pSupEsq = new Ponto(-5, 5);
    pInfDir = new Ponto(3, -3);

    area = new Area(pSupEsq, pInfDir);

    Ponto expected = new Ponto(-1, 1);
    Ponto actual = area.pontoCentral();

    assertTrue(expected.getX() == actual.getX() && expected.getY() == actual.getY());
  }

  /**
   * Teste do método 'codificaPonto'
   * Guia para entender os bits de resposta:
   * 
   * 0 Dentro do rect
   * 1 Acima e dentro do rect
   * 2 Abaixo e dentro do rect
   * 3 IMPOSSÍVEL
   * 4 Dir e dentro do rect
   * 5 Dir e acima do rect
   * 6 Dir e abaixo do rect 
   * 7 IMPOSSÍVEL
   * 8 Esq e dentro do rect
   * 9 Esq e acima do rect
   * 10 Esq e abaixo do rect
   */

  @Test
  public void pointInsideAreaTest() {
    pSupEsq = new Ponto(-2, 2);
    pInfDir = new Ponto(2, -2);
    area = new Area(pSupEsq, pInfDir);
    Ponto ponto = new Ponto(0, 0);

    byte expected = (byte) 0;
    byte actual = area.codificaPonto(ponto);

    assertTrue(expected == actual);
  }

  @Test
  public void pointInsideThePerimeterTest() {
    pSupEsq = new Ponto(-2, 2);
    pInfDir = new Ponto(2, -2);
    area = new Area(pSupEsq, pInfDir);
    Ponto ponto = new Ponto(0, -2);

    byte expected = (byte) 0;
    byte actual = area.codificaPonto(ponto);

    assertTrue(expected == actual);
  }

  @Test
  public void pointAboveAreaTest() {
    pSupEsq = new Ponto(-2, 2);
    pInfDir = new Ponto(2, -2);
    area = new Area(pSupEsq, pInfDir);
    Ponto ponto = new Ponto(0, 3);

    byte expected = (byte) 1;
    byte actual = area.codificaPonto(ponto);

    assertTrue(expected == actual);
  }

  @Test
  public void pointBellowAreaTest() {
    pSupEsq = new Ponto(-2, 2);
    pInfDir = new Ponto(2, -2);
    area = new Area(pSupEsq, pInfDir);
    Ponto ponto = new Ponto(0, -3);

    byte expected = (byte) 2;
    byte actual = area.codificaPonto(ponto);

    assertTrue(expected == actual);
  }

  @Test
  public void pointRightAreaTest() {
    pSupEsq = new Ponto(-2, 2);
    pInfDir = new Ponto(2, -2);
    area = new Area(pSupEsq, pInfDir);
    Ponto ponto = new Ponto(3, 0);

    byte expected = (byte) 4;
    byte actual = area.codificaPonto(ponto);

    assertTrue(expected == actual);
  }

  @Test
  public void pointRightAndAboveAreaTest() {
    pSupEsq = new Ponto(-2, 2);
    pInfDir = new Ponto(2, -2);
    area = new Area(pSupEsq, pInfDir);
    Ponto ponto = new Ponto(5, 5);

    byte expected = (byte) 5;
    byte actual = area.codificaPonto(ponto);

    assertTrue(expected == actual);
  }

  @Test
  public void pointRightAndBelowAreaTest() {
    pSupEsq = new Ponto(-2, 2);
    pInfDir = new Ponto(2, -2);
    area = new Area(pSupEsq, pInfDir);
    Ponto ponto = new Ponto(5, -4);

    byte expected = (byte) 6;
    byte actual = area.codificaPonto(ponto);

    assertTrue(expected == actual);
  }

  @Test
  public void pointLeftAreaTest() {
    pSupEsq = new Ponto(-2, 2);
    pInfDir = new Ponto(2, -2);
    area = new Area(pSupEsq, pInfDir);
    Ponto ponto = new Ponto(-4, 0);

    byte expected = (byte) 8;
    byte actual = area.codificaPonto(ponto);

    assertTrue(expected == actual);
  }

  @Test
  public void pointLeftAndAboveAreaTest() {
    pSupEsq = new Ponto(-2, 2);
    pInfDir = new Ponto(2, -2);
    area = new Area(pSupEsq, pInfDir);
    Ponto ponto = new Ponto(-4, 4);

    byte expected = (byte) 9;
    byte actual = area.codificaPonto(ponto);

    assertTrue(expected == actual);
  }

  @Test
  public void pointLeftAndBelowAreaTest() {
    pSupEsq = new Ponto(-2, 2);
    pInfDir = new Ponto(2, -2);
    area = new Area(pSupEsq, pInfDir);
    Ponto ponto = new Ponto(-4, -4);

    byte expected = (byte) 10;
    byte actual = area.codificaPonto(ponto);

    assertTrue(expected == actual);
  }

  /**
   * Teste da função 'classifica', que verifica estado da reta relativo a área 
   */

   // Teste dentro dá área
  @Test
  public void lineInsideAreaTest() {
    pSupEsq = new Ponto(-2, 2);
    pInfDir = new Ponto(2, -2);
    area = new Area(pSupEsq, pInfDir);
    Reta reta = new Reta((new Ponto(-2,-2)), (new Ponto(2,2)));

    SituacaoReta expected = SituacaoReta.TODA_DENTRO;
    SituacaoReta actual = area.classifica(reta);

    assertEquals(expected, actual);
  }

  @Test
  public void lineIsInsideOfRectangleTouchingPerimeterTest() {
    pSupEsq = new Ponto(-2, 2);
    pInfDir = new Ponto(2, -2);
    area = new Area(pSupEsq, pInfDir);
    Reta reta = new Reta((new Ponto(-2, 2)), (new Ponto(2, 2)));

    SituacaoReta expected = SituacaoReta.TODA_DENTRO;
    SituacaoReta actual = area.classifica(reta);

    assertTrue(expected == actual);
  }

  @Test
  public void lineIntersectsRectangleTest() {
    pSupEsq = new Ponto(-2, 2);
    pInfDir = new Ponto(2, -2);
    area = new Area(pSupEsq, pInfDir);
    Reta reta = new Reta((new Ponto(-3, 0)), (new Ponto(0, 3)));

    SituacaoReta expected = SituacaoReta.INTERSECTA;
    SituacaoReta actual = area.classifica(reta);

    assertTrue(expected == actual);
  }

  @Test
  public void lineIsTotallyOutOfRectangleTest() {
    pSupEsq = new Ponto(-2, 2);
    pInfDir = new Ponto(2, -2);
    area = new Area(pSupEsq, pInfDir);
    Reta reta = new Reta((new Ponto(-5, 0)), (new Ponto(-4, 1)));

    SituacaoReta expected = SituacaoReta.TODA_FORA;
    SituacaoReta actual = area.classifica(reta);

    assertTrue(expected == actual);
  }

}