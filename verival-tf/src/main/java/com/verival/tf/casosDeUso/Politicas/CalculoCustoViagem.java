package com.verival.tf.casosDeUso.Politicas;

import com.verival.tf.entidades.Passageiro;
import com.verival.tf.entidades.Roteiro;

public interface CalculoCustoViagem {
    void defineRoteiro(Roteiro roteiro);

    void definePassageiro(Passageiro passageiro);

    public Roteiro getRoteiro();

    public Passageiro getPassageiro();

    double calculoCustoBasico();

    double descontoPontuacao();

    double descontoPromocaoSazonal();

    double custoViagem();
}