package com.example.fitnes_hanma.Admin.Principal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fitnes_hanma.Admin.SeeViews;
import com.example.fitnes_hanma.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdPCliente extends AppCompatActivity {
    Intent i;
    EditText searchClient;
    TextView name, email;
    RecyclerView clientes;
    FirebaseDatabase frdb;
    DatabaseReference BD_clientes;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AdminStatusBar);
        setContentView(R.layout.a_ad_p_cliente);
        searchClient = (EditText) findViewById(R.id.seCli);
        ImageView buscar = findViewById(R.id.buscar);
        ImageView regre = findViewById(R.id.regre);

        regre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(AdPCliente.this, SeeViews.class);
                startActivity(i);
            }
        });

    }

}
