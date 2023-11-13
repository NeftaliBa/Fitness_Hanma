package com.example.fitnes_hanma.Instructor.grupo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fitnes_hanma.R;

public class Grupo_mi_post extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_cla_grupo_mi_post);
        Button mi_post, general;

        general = (Button) findViewById(R.id.general);

        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Grupo_mi_post.this, Grupo.class);
                startActivity(intent);
            }
        });
    }
}