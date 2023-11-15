package com.example.fitnes_hanma.Admin.Secundarias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fitnes_hanma.Admin.Principal.AdPCliente;
import com.example.fitnes_hanma.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdSModCli extends AppCompatActivity {
    EditText nombre,email;
    Button cancelar, guardar;

    SwitchCompat trainer, admin;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_ad_s_mod_cli);
        nombre = (EditText) findViewById(R.id.Name);
        email =(EditText) findViewById(R.id.mail);
        trainer = (SwitchCompat) findViewById(R.id.trainer);
        admin = (SwitchCompat) findViewById(R.id.admin);
        cancelar = (Button) findViewById(R.id.cancel);
        guardar = (Button) findViewById(R.id.save);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(AdSModCli.this, AdPCliente.class);
                startActivity(i);
            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(AdSModCli.this, AdPCliente.class);
                startActivity(i);
            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String correo = intent.getStringExtra("email");
            String trainerI = intent.getStringExtra("role");


            // Obtén una referencia a la colección "clases" en Firestore
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            CollectionReference clieRef = db.collection("user");

            // Configura los campos con los datos
            nombre.setText(name);
            email.setText(correo);


    }
}
}