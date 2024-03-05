package com.example.app_votacion.activity.estudiante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.app_votacion.R;
import com.example.app_votacion.activity.MainActivity;
import com.example.app_votacion.datos.Candidato;
import com.example.app_votacion.datos.Urna;

public class EstudianteContralorActivity extends AppCompatActivity {
    private Button votarButton1, votarButton2, votoBlancoButton;

    private String grado, curso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_contralor);

        // Obtener datos de la actividad anterior
        Intent intent = getIntent();
        grado = intent.getStringExtra("grado");
        curso = intent.getStringExtra("curso");

        // Inicializar vistas
        votarButton1 = findViewById(R.id.buttonVotar1_contralor);
        votarButton2 = findViewById(R.id.buttonVotar2_contralor);
        votoBlancoButton = findViewById(R.id.buttonVotar_en_blanco);

        // Obtener referencia al botón "Menu"
        Button botonMenu = findViewById(R.id.Menu);

        // Manejar el evento de clic en el botón "Salir"
        botonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cerrar la actividad actual
                volverAlMainActivity();
            }
        });

        // Dentro del evento de clic del botón de votar para el candidato 1
        votarButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Guardar el voto para el candidato 1 en la urna
                Urna.getInstance(getApplicationContext()).registrarVoto("Contralor", Integer.parseInt(grado), Integer.parseInt(curso), 1);

                // Mostrar mensaje de éxito
                Toast.makeText(EstudianteContralorActivity.this, "Voto registrado para Candidato 1", Toast.LENGTH_SHORT).show();

                // Volver al MainActivity
                volverAlMainActivity();
            }
        });

        // Dentro del evento de clic del botón de votar para el candidato 2
        votarButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Guardar el voto para el candidato 2 en la urna
                Urna.getInstance(getApplicationContext()).registrarVoto("Contralor", Integer.parseInt(grado), Integer.parseInt(curso), 2);

                // Mostrar mensaje de éxito
                Toast.makeText(EstudianteContralorActivity.this, "Voto registrado para Candidato 2", Toast.LENGTH_SHORT).show();

                // Volver al MainActivity
                volverAlMainActivity();
            }
        });

        // Dentro del evento de clic del botón de votar en blanco
        votoBlancoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Guardar el voto en blanco en la urna
                Urna.getInstance(getApplicationContext()).registrarVoto("Contralor", Integer.parseInt(grado), Integer.parseInt(curso), 3);

                // Mostrar mensaje de éxito
                Toast.makeText(EstudianteContralorActivity.this, "Voto en blanco registrado", Toast.LENGTH_SHORT).show();

                // Volver al MainActivity
                volverAlMainActivity();
            }
        });
    }
    // Método para volver al MainActivity
    private void volverAlMainActivity() {
        Intent intent = new Intent(EstudianteContralorActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Esto cierra la actividad actual para evitar que se acumulen en el stack
    }
}

