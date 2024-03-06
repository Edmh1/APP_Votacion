package com.example.app_votacion.datos;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Urna implements Serializable {
    private List<Candidato> candidatos;
    private String rutaArchivo;

    public Urna(Context context) {
        this.candidatos = new ArrayList<>();
        this.rutaArchivo = context.getFilesDir() + "/usuario.dat";
    }
    // Método para inicializar los candidatos sin guardar la urna
    public void initializarCandidatos() {
        this.candidatos = new ArrayList<>();
        this.candidatos.add(new Personero(1));
        this.candidatos.add(new Personero(2));
        this.candidatos.add(new Personero(3)); //en blanco
        this.candidatos.add(new Contralor(1));
        this.candidatos.add(new Contralor(2));
        this.candidatos.add(new Contralor(3)); //en blanco
    }

    // Método para guardar la urna
    public void guardarUrna() {
        FileManager.guardarUrna(this, getRutaArchivo());
    }

    public List<Candidato> getCandidatos() {
        return candidatos;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void registrarVoto(String tipo, int grado, int curso, int numero) {
        for (Candidato candidato : candidatos) {
            if (candidato.getTipo().equals(tipo) && candidato.getNumero() == numero) {
                candidato.registrarVoto(grado, curso);
                break;
            }
        }
    }

    public void vaciar(){
        this.candidatos.clear();
    }

    //OTROS METODOS

}