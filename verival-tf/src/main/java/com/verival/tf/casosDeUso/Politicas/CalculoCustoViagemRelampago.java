package com.verival.tf.casosDeUso.Politicas;

import com.verival.tf.entidades.Passageiro;
import com.verival.tf.entidades.Roteiro;

public class CalculoCustoViagemRelampago extends CalculoCustoViagemBasico {
    public CalculoCustoViagemRelampago(Roteiro roteiro, Passageiro passageiro) throws NullPointerException {
        super(roteiro, passageiro);
    }

    @Override
    public double descontoPontuacao() {
        Passageiro p = getPassageiro();
        return (p.getQtdadeAvaliacoes() > 30 && p.getPontuacaoMedia() > 5) ? calculoCustoBasico()*0.05 : 0;
    }

    @Override
    public double descontoPromocaoSazonal() {
        return getRoteiro().bairrosPercorridos().size() > 3 ? calculoCustoBasico() * 0.05 : 0;
    }
}