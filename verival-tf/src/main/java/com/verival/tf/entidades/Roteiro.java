package com.verival.tf.entidades;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.verival.tf.entidades.geometria.Ponto;
import com.verival.tf.entidades.geometria.Reta;
import com.verival.tf.entidades.geometria.SituacaoReta;

public class Roteiro {
    private Bairro bairroOrigem;
    private Bairro bairroDestino;
    private Reta rota;
    private Collection<Bairro> bairrosPercorridos;

    private void determinaBairrosPercorridos(Reta rota, Collection<Bairro> todosBairros) {
        for (Bairro bairro : todosBairros) {
            SituacaoReta sr = bairro.getArea().classifica(rota);
            if (sr != SituacaoReta.TODA_FORA) {
                bairrosPercorridos.add(bairro);
            }
        }
    }

    public Roteiro(Bairro bairroOrigem, Bairro bairroDestino, Collection<Bairro> todosBairros) throws IllegalArgumentException {
        if (!todosBairros.contains(bairroOrigem) ||
         !todosBairros.contains(bairroDestino) ||
          bairroOrigem == null || bairroDestino == null) {
            throw new IllegalArgumentException("Impossible to calculate route with a district outside off the map.");
        }
        this.bairroOrigem = bairroOrigem;
        this.bairroDestino = bairroDestino;
        bairrosPercorridos = new ArrayList<>();

        Ponto pOrig = bairroOrigem.getArea().pontoCentral();
        Ponto pDest = bairroDestino.getArea().pontoCentral();
        rota = new Reta(pOrig, pDest);
        determinaBairrosPercorridos(rota, todosBairros);
    }

    public Reta getRota() {
        return rota;
    }

    public Bairro getBairroOrigem() {
        return bairroOrigem;
    }

    public Bairro getBairroDestino() {
        return bairroDestino;
    }

    public Collection<Bairro> bairrosPercorridos() {
        return bairrosPercorridos;
    }

    @Override
    public String toString() {
        return "Roteiro [bairroDestino=" + bairroDestino + ", bairroOrigem=" + bairroOrigem + "]";
    }
}