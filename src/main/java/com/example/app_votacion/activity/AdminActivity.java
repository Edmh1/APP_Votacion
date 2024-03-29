package com.example.app_votacion.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_votacion.R;
import com.example.app_votacion.datos.Candidato;
import com.example.app_votacion.datos.FileManager;
import com.example.app_votacion.datos.Urna;

import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private Button actualizarDatos, vaciarUrna, botonMenu;
    private Urna urna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);

        //Obtener Urna
        urna = new Urna(this);
        urna = FileManager.cargarUrna(urna.getRutaArchivo());

        // Obtener referencias de los botones
        actualizarDatos = findViewById(R.id.ActualizarDatos);
        vaciarUrna = findViewById(R.id.VaciarUrna);
        botonMenu = findViewById(R.id.Menu);

        // Manejar el evento click para el botón de "Menú"
        botonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverAlMainActivity();
            }
        });

        // Mostrar la cantidad de votos de cada candidato
        actualizarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarCantidadVotos();
            }
        });

        
        // Elimina los votos almacenados
        vaciarUrna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urna.vaciar();
                urna.initializarCandidatos();
                urna.guardarUrna();
            }
        });

    }

    // Método para volver al MainActivity
    private void volverAlMainActivity() {
        Intent intent = new Intent(AdminActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Esto cierra la actividad actual para evitar que se acumulen en el stack
    }

    // Método para mostrar la cantidad de votos de cada candidato
    private void mostrarCantidadVotos() {
        List<Candidato> candidatos = urna.getCandidatos();
        for (Candidato candidato : candidatos) {
            if (candidato != null) {
                int votos = candidato.getNvotostotales();
                TextView textViewVotos = obtenerTextViewPorTipoCandidato(candidato);
                if (textViewVotos != null) {
                    textViewVotos.setText("Votos: " + votos);
                }
            }
        }
    }

    // Método para obtener el TextView según el tipo de candidato
    private TextView obtenerTextViewPorTipoCandidato(Candidato candidato) {
        int textViewId = obtenerTextViewIdSegunTipoCandidato(candidato);
        if (textViewId != -1) {
            return findViewById(textViewId);
        }
        return null;
    }

    // Método para obtener el ID del TextView según el tipo de candidato
    private int obtenerTextViewIdSegunTipoCandidato(Candidato candidato) {
        int[] personeroTextViews = {R.id.textVotosPersonero1, R.id.textVotosPersonero2, R.id.textVotosPersoneroEnBlanco};
        int[] contralorTextViews = {R.id.textVotosContralor1, R.id.textVotosContralor2, R.id.textVotosContralorEnBlanco};

        switch (candidato.getTipo()) {
            case "Personero":
                if (candidato.getNumero() >= 1 && candidato.getNumero() <= 3) {
                    return personeroTextViews[candidato.getNumero() - 1];
                }
                break;
            case "Contralor":
                if (candidato.getNumero() >= 1 && candidato.getNumero() <= 3) {
                    return contralorTextViews[candidato.getNumero() - 1];
                }
                break;
            default:
                return -1; // Si no coincide con ningún tipo conocido, retorna -1
        }
        return  0;
    }


}





