package com.example.fitnes_hanma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class menuRL extends AppCompatActivity {

    Button irLogin, irRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_rl);

        irLogin = findViewById(R.id.irLogin);
        irRegistro = findViewById(R.id.irRegistro);

        irLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menuRL.this, login.class));
            }
        });

        irRegistro.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(menuRL.this, register.class));
            }
        });
        
    }
}