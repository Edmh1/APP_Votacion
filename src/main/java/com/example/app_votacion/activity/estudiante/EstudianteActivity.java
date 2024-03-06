package com.example.app_votacion.activity.estudiante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.app_votacion.R;

public class EstudianteActivity extends AppCompatActivity {
    private RadioGroup rgGrado, rgCurso;
    private Button continuarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante);

        //Obtener las referencias de los botones
        rgGrado = findViewById(R.id.rgGrado);
        rgCurso = findViewById(R.id.rgCurso);
        continuarButton = findViewById(R.id.Continuar);

        //Manejador de evento "Continuar"
        continuarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Obtener valores de Grado y Curso del estudiante
                int selectedGradoId = rgGrado.getCheckedRadioButtonId();
                int selectedCursoId = rgCurso.getCheckedRadioButtonId();

                if (selectedGradoId != -1 && selectedCursoId != -1) {
                    RadioButton selectedGrado = findViewById(selectedGradoId);
                    RadioButton selectedCurso = findViewById(selectedCursoId);

                    String grado = selectedGrado.getText().toString();
                    String curso = selectedCurso.getTag().toString();

                    Intent intent = new Intent(EstudianteActivity.this, EstudiantePersoneroActivity.class);
                    intent.putExtra("grado", grado);
                    intent.putExtra("curso", curso);
                    startActivity(intent);
                } else {
                    Toast.makeText(EstudianteActivity.this, "Por favor, selecciona grado y curso", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

