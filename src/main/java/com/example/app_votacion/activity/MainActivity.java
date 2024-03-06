package com.example.app_votacion.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.*;
import android.view.*;


import com.example.app_votacion.R;
import com.example.app_votacion.activity.estudiante.EstudianteActivity;
import com.example.app_votacion.datos.FileManager;
import com.example.app_votacion.datos.Urna;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Urna urna;
    private Button estudianteButton;
    private Button adminButton;
    private Button ayudaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener Urna
        urna = new Urna(this);
        if(existenciaDeArchivo(urna.getRutaArchivo())){
            urna = FileManager.cargarUrna(urna.getRutaArchivo());
        }
        else{
            urna.initializarCandidatos();
            urna.guardarUrna(); // Guarda la urna después de inicializar los candidatos
        }

        //Obtener referencias de botones
        estudianteButton = findViewById(R.id.Estudiante);
        adminButton = findViewById(R.id.Admin);
        ayudaButton = findViewById(R.id.Ayuda);

        // Manejar el evento de clic en el botón "Estudiante"
        estudianteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirSeleccionCursoGrado();
            }
        });

        // Manejar el evento de clic en el botón "Admin"
        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirActividadAdmin();
            }
        });

        // Manejar el evento de clic en el botón "Ayuda"
        ayudaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //COMPLETAR
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

    public boolean existenciaDeArchivo(String ruta){
        // Crear un objeto File con la ruta del archivo
        File archivo = new File(ruta);

        // Verificar si el archivo existe
        if (archivo.exists()) {
            return true;
        } else {
            return false;
        }
    }

}