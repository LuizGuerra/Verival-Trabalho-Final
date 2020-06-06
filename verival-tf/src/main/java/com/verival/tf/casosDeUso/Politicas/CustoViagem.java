package com.verival.tf.casosDeUso.Politicas;

import com.verival.tf.entidades.Passageiro;
import com.verival.tf.entidades.Roteiro;

public class CustoViagem {
    private CalculoCustoViagem ccv;

    public CustoViagem(CalculoCustoViagem ccv) {
        this.ccv = ccv;
    }

    public double custoViagem(Roteiro roteiro, Passageiro passageiro) {
        ccv.defineRoteiro(roteiro);
        ccv.definePassageiro(passageiro);
        return ccv.custoViagem();
    }
}