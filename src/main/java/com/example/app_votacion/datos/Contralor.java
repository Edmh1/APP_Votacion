package com.example.app_votacion.datos;

import com.google.gson.JsonObject;

public class Contralor extends Candidato {
    private static final long serialVersionUID = 1L;

    public Contralor(int numero) {
        super(numero);
    }

    public Contralor(JsonObject jsonObject) {
        super(jsonObject);
    }

    @Override
    public String getTipo() {
        return "Contralor";
    }
}
