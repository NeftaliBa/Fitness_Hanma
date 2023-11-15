package com.example.fitnes_hanma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class pantalla_carga extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_pantalla_carga);

        int Tiempo = 3000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(pantalla_carga.this, menuRL.class));
                finish();
            }
        }, Tiempo);

    }
}