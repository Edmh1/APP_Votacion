package com.example.app_votacion.activity.estudiante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.app_votacion.R;
import com.example.app_votacion.activity.MainActivity;
import com.example.app_votacion.datos.FileManager;
import com.example.app_votacion.datos.Urna;

public class EstudiantePersoneroActivity extends AppCompatActivity {
    private Button votarButton1, votarButton2, votoBlancoButton, botonMenu;

    private String grado, curso;

    private Urna urna;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_personero);

        //Obtener Urna
        urna = new Urna(this);
        urna = FileManager.cargarUrna(urna.getRutaArchivo());

        // Obtener datos de la actividad anterior
        Intent intent = getIntent();
        grado = intent.getStringExtra("grado");
        curso = intent.getStringExtra("curso");

        // Inicializar vistas
        votarButton1 = findViewById(R.id.buttonVotar1_personero);
        votarButton2 = findViewById(R.id.buttonVotar2_personero);
        votoBlancoButton = findViewById(R.id.buttonVotar_en_blanco);

        // Obtener referencia al botón "Menu"
        botonMenu = findViewById(R.id.Menu);

        // Manejar el evento de clic en el botón "Salir"
        botonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cerrar la actividad actual
                volverAlMainActivity();
            }
        });

        // Manejar el evento de clic en el botón de votar para el candidato 1
        votarButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Guardar el voto para el candidato 1 en la urna
                urna.registrarVoto("Personero", Integer.parseInt(grado), Integer.parseInt(curso), 1);
                FileManager.guardarUrna(urna, urna.getRutaArchivo());

                // Mostrar mensaje de éxito
                Toast.makeText(EstudiantePersoneroActivity.this, "Voto registrado para Candidato 1", Toast.LENGTH_SHORT).show();

                // Ir al layout del Contralor
                irALayoutContralor();
            }
        });

        // Manejar el evento de clic en el botón de votar para el candidato 2
        votarButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Guardar el voto para el candidato 2 en la urna
                urna.registrarVoto("Personero", Integer.parseInt(grado), Integer.parseInt(curso), 2);
                FileManager.guardarUrna(urna, urna.getRutaArchivo());

                // Mostrar mensaje de éxito
                Toast.makeText(EstudiantePersoneroActivity.this, "Voto registrado para Candidato 2", Toast.LENGTH_SHORT).show();

                // Ir al layout del Contralor
                irALayoutContralor();
            }
        });

        // Manejar el evento de clic en el botón de votar en blanco
        votoBlancoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Guardar el voto en blanco en la urna
                urna.registrarVoto("Personero", Integer.parseInt(grado), Integer.parseInt(curso), 3);
                FileManager.guardarUrna(urna, urna.getRutaArchivo());

                // Mostrar mensaje de éxito
                Toast.makeText(EstudiantePersoneroActivity.this, "Voto en blanco registrado", Toast.LENGTH_SHORT).show();

                // Ir al layout del Contralor
                irALayoutContralor();
            }
        });
    }

    private void volverAlMainActivity() {
        Intent intent = new Intent(EstudiantePersoneroActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Esto cierra la actividad actual para evitar que se acumulen en el stack
    }

    private void irALayoutContralor() {
        Intent intent = new Intent(EstudiantePersoneroActivity.this, EstudianteContralorActivity.class);
        intent.putExtra("grado", grado);
        intent.putExtra("curso", curso);
        startActivity(intent);
        finish(); // Terminar esta actividad para evitar volver atrás
    }
}

