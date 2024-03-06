package com.example.app_votacion.datos;



import java.io.Serializable;

public class Personero extends Candidato implements Serializable {

    public Personero(int numero) {
        super(numero);
    }

    @Override
    public String getTipo() {
        return "Personero";
    }
}