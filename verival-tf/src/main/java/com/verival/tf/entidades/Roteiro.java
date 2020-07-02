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

    public Roteiro(Bairro bairroOrigem, Bairro bairroDestino, Collection<Bairro> todosBairros) throws IllegalArgumentException, NullPointerException {
        if (bairroOrigem == null) {
            throw new NullPointerException("Origin district cannot be null on initializer");
        }
        if (bairroDestino == null) {
            throw new NullPointerException("Destination district cannot be null on initializer");
        }
        if (todosBairros == null) {
            throw new NullPointerException("City map cannot be null on initializer");
        }
        if ( todosBairros.isEmpty()) {
            throw new IllegalArgumentException("City map cannot be empty on initializer");
        }
        if (!todosBairros.contains(bairroOrigem)) {
            throw new IllegalArgumentException("City map doesn't contain origin district");
        }
        if (!todosBairros.contains(bairroDestino)) {
            throw new IllegalArgumentException("City map doesn't contain destination district");
        }

        this.bairroOrigem = bairroOrigem;
        this.bairroDestino = bairroDestino;
        this.bairrosPercorridos = todosBairros;

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
