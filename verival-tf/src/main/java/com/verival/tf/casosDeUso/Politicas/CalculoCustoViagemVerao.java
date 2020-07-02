package com.verival.tf.casosDeUso.Politicas;

import com.verival.tf.entidades.Passageiro;
import com.verival.tf.entidades.Roteiro;

public class CalculoCustoViagemVerao extends CalculoCustoViagemBasico {
    public CalculoCustoViagemVerao(Roteiro roteiro, Passageiro passageiro) throws NullPointerException {
        super(roteiro, passageiro);
    }

    @Override
    public double descontoPontuacao() {
        return getPassageiro().getPontuacaoMedia() > 9 ? calculoCustoBasico() * 0.09 : 0;
    }

    @Override
    public double descontoPromocaoSazonal() {
        return getRoteiro().bairrosPercorridos().size() > 2 ? calculoCustoBasico() * 0.1 : 0;
    }
}