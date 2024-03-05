package com.example.app_votacion.datos;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
public class Urna {
    private static Urna instance;
    private Context context;
    private UrnaPreferences urnaPreferences;
    private List<Candidato> candidatos;
    private boolean isInitialized;

    private Urna(Context context) {
        this.context = context;
        urnaPreferences = UrnaPreferences.getInstance(context);
        isInitialized = false;
    }

    public static Urna getInstance(Context context) {
        if (instance == null) {
            synchronized (Urna.class) {
                if (instance == null) {
                    instance = new Urna(context);
                }
            }
        }
        return instance;
    }

    public void initializeCandidatos() {
        if (!isInitialized) {
            if (urnaPreferences.getCandidatos().isEmpty()) {
                candidatos = new ArrayList<>();
                candidatos.add(new Personero(1));
                candidatos.add(new Personero(2));
                candidatos.add(new Personero(3)); //en blanco
                candidatos.add(new Contralor(1));
                candidatos.add(new Contralor(2));
                candidatos.add(new Contralor(3)); //en blanco

                // Guardar los candidatos en las preferencias compartidas
                urnaPreferences.saveCandidatos(candidatos);
            } else {
                candidatos = urnaPreferences.getCandidatos();
            }
            isInitialized = true;
        }
    }

    public void registrarVoto(String tipo, int grado, int curso, int numero) {
        initializeCandidatos(); // Ensure candidates are initialized before registering a vote

        for (Candidato candidato : candidatos) {
            if (candidato.getTipo().equals(tipo) && candidato.getNumero() == numero) {
                candidato.registrarVoto(grado, curso);
                break;
            }
        }
        urnaPreferences.saveCandidatos(candidatos); // Guardar los cambios después de registrar el voto
    }

    public List<Candidato> getCandidatos() {
        initializeCandidatos(); // Ensure candidates are initialized before retrieving them
        return candidatos;
    }
}