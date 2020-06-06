package com.bcopstein.entidades;

import com.bcopstein.entidades.geometria.Area;
import com.bcopstein.entidades.geometria.Ponto;

public class Bairro {
    private String nome;
    private Area area;
    private double custoTransporte;

    public static Bairro novoBairroQuadrado(String nome,Ponto pSupEsq,int lado,double custoTransporte){
        Ponto pInfDir = new Ponto(pSupEsq.getX()+lado,pSupEsq.getY()-lado);
        return new Bairro(nome,new Area(pSupEsq,pInfDir),custoTransporte);
    }

    public static Bairro novoBairroRetangular(String nome,Ponto pSupEsq,int ladoH,int ladoV,double custoTransporte){
        Ponto pInfDir = new Ponto(pSupEsq.getX()+ladoH,pSupEsq.getY()-ladoV);
        return new Bairro(nome,new Area(pSupEsq,pInfDir),custoTransporte);
    }
    
    private Bairro(String nome, Area area, double custoTransporte) {
        this.nome = nome;
        this.area = area;
        this.custoTransporte = custoTransporte;
    }

    public String getNome() {
        return nome; 
    }

    public Area getArea() {
        return area;
    }

    public double getCustoTransporte(){
        return this.custoTransporte;
    }

    public void alteraCustoTransporte(double novoValor){
        if (novoValor < 0.0){
            throw new IllegalArgumentException("Valor invalido");
        }else{
            this.custoTransporte = novoValor;
        }
    }

    @Override
    public String toString() {
        return "Bairro [area=" + area + ", nome=" + nome + "]";
    }
}