package com.example.fitnes_hanma.Admin.Secundarias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fitnes_hanma.Admin.Principal.AdPersonal;
import com.example.fitnes_hanma.R;

public class AdModPer extends AppCompatActivity {
    EditText nombre,email;
    Button cancelar, guardar;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_mod_per);
        nombre = (EditText) findViewById(R.id.Name);
        email =(EditText) findViewById(R.id.mail);
        cancelar = (Button) findViewById(R.id.cancel);
        guardar = (Button) findViewById(R.id.save);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(AdModPer.this, AdPersonal.class);
                startActivity(i);
            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(AdModPer.this, AdPersonal.class);
                startActivity(i);
            }
        });

    }
}