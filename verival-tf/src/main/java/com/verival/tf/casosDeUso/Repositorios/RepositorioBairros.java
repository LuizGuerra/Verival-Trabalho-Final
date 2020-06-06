package com.verival.tf.casosDeUso.Repositorios;

import java.util.List;

import com.verival.tf.entidades.Bairro;

public interface RepositorioBairros {
    Bairro recuperaPorNome(String nomeBairro);

    List<Bairro> recuperaListaBairros();
}