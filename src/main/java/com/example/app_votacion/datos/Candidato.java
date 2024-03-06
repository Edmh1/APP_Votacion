package com.example.app_votacion.datos;


import java.io.Serializable;


public abstract class Candidato implements Serializable{
    private int numero;
    private int nvotostotales;
    private int[][] votosPorGrupoGrado;

    public Candidato(int numero) {
        this.numero = numero;
        this.nvotostotales = 0;
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

    public abstract String getTipo();

}