package com.example.app_votacion.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.*;
import android.view.*;


import com.example.app_votacion.R;
import com.example.app_votacion.activity.estudiante.EstudianteActivity;
import com.example.app_votacion.activity.estudiante.EstudiantePersoneroActivity;
import com.example.app_votacion.datos.Urna;

public class MainActivity extends AppCompatActivity {

    private Urna urna;
    private Button estudianteButton;
    private Button adminButton;
    private Button ayudaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener instancia de Urna desde el objeto App
        urna = Urna.getInstance(getApplicationContext());

        estudianteButton = findViewById(R.id.Estudiante);
        adminButton = findViewById(R.id.Admin);
        ayudaButton = findViewById(R.id.Ayuda);

        estudianteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirSeleccionCursoGrado();
            }
        });

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirActividadAdmin();
            }
        });
        ayudaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void abrirSeleccionCursoGrado() {
        Intent intent = new Intent(MainActivity.this, EstudianteActivity.class);
        startActivity(intent);
    }
    private void abrirActividadAdmin() {
        Intent intent = new Intent(MainActivity.this, AdminActivity.class);
        startActivity(intent);
    }


}