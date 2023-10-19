package com.example.fitnes_hanma.Admin.Secundarias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fitnes_hanma.Admin.Principal.AdCliente;
import com.example.fitnes_hanma.Admin.Principal.AdServicios;
import com.example.fitnes_hanma.R;

public class AdModSe extends AppCompatActivity {
    EditText nombre,email;
    Button cancelar, guardar;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_mod_se);
        nombre = (EditText) findViewById(R.id.Name);
        email =(EditText) findViewById(R.id.mail);
        cancelar = (Button) findViewById(R.id.cancel);
        guardar = (Button) findViewById(R.id.save);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(AdModSe.this, AdServicios.class);
                startActivity(i);
            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(AdModSe.this, AdServicios.class);
                startActivity(i);
            }
        });

    }
}