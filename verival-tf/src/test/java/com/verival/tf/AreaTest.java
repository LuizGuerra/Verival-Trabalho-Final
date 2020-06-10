package com.verival.tf;

import static org.junit.Assert.assertTrue;

import com.verival.tf.entidades.geometria.Area;
import com.verival.tf.entidades.geometria.Ponto;

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
}