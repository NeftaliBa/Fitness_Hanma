package com.example.fitnes_hanma.Instructor.calendario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.ImageView;


import com.example.fitnes_hanma.R;

public class Calendario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);
        ImageView semana;

        semana = (ImageView) findViewById(R.id.semanal);

        semana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la página MainActivity
                Intent intent = new Intent(Calendario.this, Calendario_semanal.class);
                startActivity(intent);
            }
        });
    }
}