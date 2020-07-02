package com.verival.tf.casosDeUso.Politicas;

import com.verival.tf.entidades.Passageiro;
import com.verival.tf.entidades.Roteiro;

public class CalculoCustoViagemVerao extends CalculoCustoViagemBasico {
    public CalculoCustoViagemVerao(Roteiro roteiro, Passageiro passageiro) throws NullPointerException {
        super(roteiro, passageiro);
    }

    @Override
    public double descontoPontuacao() {
        double custobasico = calculoCustoBasico();
        if (getPassageiro().getPontuacaoMedia() > 9.0) {
            return custobasico * 0.9;
        } else {
            return 0.0;
        }
    }

    @Override
    public double descontoPromocaoSazonal() {
        int qtdadeBairros = getRoteiro().bairrosPercorridos().size();
        double cb = calculoCustoBasico();
        if (qtdadeBairros > 2) {
            return cb * 0.1;
        } else {
            return 0.0;
        }
    }
}