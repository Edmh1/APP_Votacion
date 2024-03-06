package com.example.app_votacion.datos;


import java.io.Serializable;

public class Contralor extends Candidato implements Serializable {

    public Contralor(int numero) {
        super(numero);
    }

    @Override
    public String getTipo() {
        return "Contralor";
    }
}
