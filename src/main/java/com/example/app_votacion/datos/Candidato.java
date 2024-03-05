package com.example.app_votacion.datos;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.Objects;

public abstract class Candidato implements Serializable {
    private static final long serialVersionUID = 1L;
    private int numero;
    private int nvotostotales;
    private int[][][] votosPorGrupoGrado;

    public static final int NUM_GRUPOS = 3;
    public static final int NUM_GRADOS = 11;

    public Candidato(int numero) {
        this.numero = numero;
        this.nvotostotales = 0;
    }

    public Candidato(JsonObject jsonObject) {
        this.numero = jsonObject.get("numero").getAsInt();
        this.nvotostotales = jsonObject.get("nvotostotales").getAsInt();

        JsonArray votosPorGrupoGradoJsonArray = jsonObject.getAsJsonArray("votosPorGrupoGrado");
        this.votosPorGrupoGrado = new int[NUM_GRUPOS][NUM_GRADOS][3];
        for (int i = 0; i < NUM_GRUPOS; i++) {
            for (int j = 0; j < NUM_GRADOS; j++) {
                JsonArray votosPorGrupoGradoJsonArrayInner = votosPorGrupoGradoJsonArray.get(i).getAsJsonArray();
                for (int k = 0; k < 3; k++) {
                    this.votosPorGrupoGrado[i][j][k] = votosPorGrupoGradoJsonArrayInner.get(k).getAsInt();
                }
            }
        }
    }

    public void registrarVoto(int grado, int curso) {
        this.nvotostotales++;
    }

    public int getNumero() {
        return numero;
    }

    public int getNvotostotales() {
        return nvotostotales;
    }

    public int[][][] getVotosPorGrupoGrado() {
        return votosPorGrupoGrado;
    }

    public abstract String getTipo();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidato)) return false;
        Candidato candidato = (Candidato) o;
        return getNumero() == candidato.getNumero() && Objects.equals(getTipo(), candidato.getTipo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTipo(), getNumero());
    }

}