package com.example.app_votacion.activity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_votacion.R;

public class AyudaActivity extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);

        videoView = findViewById(R.id.videoView);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.Tutorial;
        videoView.setVideoURI(Uri.parse(videoPath));
        videoView.start();  // Inicia la reproducción del video


    }
}
