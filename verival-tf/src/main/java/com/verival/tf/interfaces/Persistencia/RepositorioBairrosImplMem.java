package com.verival.tf.interfaces.Persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.verival.tf.casosDeUso.Repositorios.RepositorioBairros;
import com.verival.tf.entidades.Bairro;
import com.verival.tf.entidades.geometria.Ponto;

public class RepositorioBairrosImplMem implements RepositorioBairros {
    private Map<String, Bairro> bairros;

    public RepositorioBairrosImplMem() {
        bairros = new HashMap<>();

        bairros.put("Petropolis", Bairro.novoBairroRetangular("Petropolis", new Ponto(2, 6), 4, 2, 10));
        bairros.put("Ipiranga", Bairro.novoBairroRetangular("Ipiranga", new Ponto(2, 2), 4, 2, 6));
        bairros.put("Solidao", Bairro.novoBairroQuadrado("Solidao", new Ponto(0, 6), 2, 5));
        bairros.put("Bom Fim", Bairro.novoBairroQuadrado("Bom Fim", new Ponto(2, 4), 2, 10));
        bairros.put("Vila Nova", Bairro.novoBairroQuadrado("Vila Nova", new Ponto(4, 4), 2, 15));
        bairros.put("Gavea", Bairro.novoBairroRetangular("Gavea", new Ponto(0, 4), 2, 4, 20));
    }

    @Override
    public Bairro recuperaPorNome(String nomeBairro) {
        Bairro bairro = bairros.get(nomeBairro);
        if (bairro == null) {
            throw new IllegalArgumentException("Bairro inexistente: " + nomeBairro);
        }
        return bairro;
    }

    @Override
    public List<Bairro> recuperaListaBairros() {
        return new ArrayList<Bairro>(bairros.values());
    }
}