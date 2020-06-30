package com.verival.tf.casosDeUso.Politicas;

import com.verival.tf.entidades.Passageiro;
import com.verival.tf.entidades.Roteiro;

public class CustoViagem {
    private CalculoCustoViagem ccv;

    public CustoViagem(CalculoCustoViagem ccv) throws NullPointerException {
        if (ccv == null) {
            throw new NullPointerException("Object on initialization cannot be null");
        }
        this.ccv = ccv;
    }

    public double custoViagem(Roteiro roteiro, Passageiro passageiro) throws NullPointerException {
        if (roteiro == null) {
            throw new NullPointerException("Null rout instead of declared object");
        }
        if (passageiro == null) {
            throw new NullPointerException("Null passenger instead of declared object");
        }
        ccv.defineRoteiro(roteiro);
        ccv.definePassageiro(passageiro);
        return ccv.custoViagem();
    }
} 