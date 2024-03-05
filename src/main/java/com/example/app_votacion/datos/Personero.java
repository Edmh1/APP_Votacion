package com.example.app_votacion.datos;


import com.google.gson.JsonObject;

public class Personero extends Candidato {
    private static final long serialVersionUID = 1L;

    public Personero(int numero) {
        super(numero);
    }

    public Personero(JsonObject jsonObject) {
        super(jsonObject);
    }

    @Override
    public String getTipo() {
        return "Personero";
    }
}