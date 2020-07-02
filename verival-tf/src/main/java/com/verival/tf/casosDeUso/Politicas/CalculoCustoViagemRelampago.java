package com.verival.tf.casosDeUso.Politicas;

import com.verival.tf.entidades.Passageiro;
import com.verival.tf.entidades.Roteiro;

public class CalculoCustoViagemRelampago extends CalculoCustoViagemBasico {
    public CalculoCustoViagemRelampago(Roteiro roteiro, Passageiro passageiro) throws NullPointerException {
        super(roteiro, passageiro);
        
    }

    @Override
    public double descontoPontuacao() {
        double custobasico = calculoCustoBasico();
        if (getPassageiro().getPontuacaoMedia() > 5.0 && getPassageiro().getQtdadeAvaliacoes() > 30) {
            return custobasico * 0.05;
        } else {
            return 0.0;
        }
    }

    @Override
    public double descontoPromocaoSazonal() {
        int qtdadeBairros = getRoteiro().bairrosPercorridos().size();
        double cb = calculoCustoBasico();
        if (qtdadeBairros > 3) {
            return cb * 0.05;
        } else {
            return 0.0;
        }
    }
}