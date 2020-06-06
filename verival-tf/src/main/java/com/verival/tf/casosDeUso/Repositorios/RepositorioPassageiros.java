package com.verival.tf.casosDeUso.Repositorios;

import java.util.List;

import com.verival.tf.entidades.Passageiro;

public interface RepositorioPassageiros {
    List<Passageiro> listaPassageiros();

    Passageiro recuperaPorCPF(String cpf);

    void atualizaPassageiro(Passageiro passageiro);
}