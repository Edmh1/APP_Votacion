package com.example.app_votacion.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;
import android.text.InputType;
import android.widget.*;
import android.view.*;


import com.example.app_votacion.R;
import com.example.app_votacion.activity.estudiante.EstudianteActivity;
import com.example.app_votacion.datos.FileManager;
import com.example.app_votacion.datos.Urna;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Urna urna;
    private Button estudianteButton, adminButton, ayudaButton;

    private String contraseña = "edulamaria";

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
                // Crear un cuadro de diálogo para solicitar la contraseña
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Ingrese la contraseña");

                // Crear un campo de texto para ingresar la contraseña
                final EditText input = new EditText(MainActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                builder.setView(input);

                // Manejar el evento de clic en el botón "Aceptar" del cuadro de diálogo
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Verificar si la contraseña ingresada es correcta
                        String contraseñausuario = input.getText().toString();
                        if (contraseñausuario.equals(contraseña)) {
                            abrirActividadAdmin();
                        } else {
                            Toast.makeText(MainActivity.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                // Manejar el evento de clic en el botón "Cancelar" del cuadro de diálogo
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });

                // Mostrar el cuadro de diálogo
                builder.show();
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