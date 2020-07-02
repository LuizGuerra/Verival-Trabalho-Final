package com.verival.tf.casosDeUso.Politicas;

import com.verival.tf.entidades.Passageiro;
import com.verival.tf.entidades.Roteiro;

public class CalculoCustoViagemBasico implements CalculoCustoViagem {
    private Roteiro roteiro;
    private Passageiro passageiro;

    public CalculoCustoViagemBasico(Roteiro roteiro, Passageiro passageiro) throws NullPointerException {
        if (roteiro == null) {
            throw new NullPointerException("Rout cannot be null");
        }
        if (passageiro == null) {
            throw new NullPointerException("Passenger cannot be null");
        }
        this.roteiro = roteiro;
        this.passageiro = passageiro;
    }

    @Override
    public void defineRoteiro(Roteiro roteiro) {
        this.roteiro = roteiro;
    }

    @Override
    public void definePassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    public Roteiro getRoteiro() {
        return roteiro;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    @Override
    public double calculoCustoBasico() {
        return roteiro.bairrosPercorridos().stream()
            .mapToDouble(b -> b.getCustoTransporte()).sum();
    }

    @Override
    public double descontoPontuacao() {
        return 0.0;
    }

    @Override
    public double descontoPromocaoSazonal() {
        return 0.0;
    }

    @Override
    public double custoViagem() {
        return calculoCustoBasico() - descontoPontuacao() - descontoPromocaoSazonal();
    }
}
