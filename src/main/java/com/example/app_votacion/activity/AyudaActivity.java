package com.example.app_votacion.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_votacion.R;

public class AyudaActivity extends AppCompatActivity {

    private Button botonMenu;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);

        //obtener las referencias
        botonMenu = findViewById(R.id.Menu);
        videoView = findViewById(R.id.videoView);

        // Manejar el evento click para el botón de "Menú"
        botonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverAlMainActivity();
            }
        });

        // Establecer ruta del video
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.tutorial;
        videoView.setVideoURI(Uri.parse(videoPath));

        // Crear un MediaController
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView); // Anclar el MediaController al VideoView

        // Asignar el MediaController al VideoView
        videoView.setMediaController(mediaController);
        videoView.start();  // Inicia la reproducción del video

    }
    // Método para volver al MainActivity
    private void volverAlMainActivity() {
        Intent intent = new Intent(AyudaActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Esto cierra la actividad actual para evitar que se acumulen en el stack
    }
}
