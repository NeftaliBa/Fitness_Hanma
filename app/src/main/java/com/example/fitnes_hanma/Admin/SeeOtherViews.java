package com.example.fitnes_hanma.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


import com.example.fitnes_hanma.Admin.Principal.AdPClases;
import com.example.fitnes_hanma.Cliente.Configuracion;
import com.example.fitnes_hanma.Cliente.principal;
import com.example.fitnes_hanma.Instructor.configuraciones.ConfiguracionIns;
import com.example.fitnes_hanma.Instructor.home.Home;
import com.example.fitnes_hanma.R;
import com.example.fitnes_hanma.menuRL;
import com.example.fitnes_hanma.register;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SeeOtherViews extends AppCompatActivity {


    Button ViAd, ViCl, ViEn;
    Intent i;
    TextView txt;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_ad_see_other_views);

        // Configurar el Toolbar
        Toolbar toolbar = findViewById(R.id.seetoolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Configurar el título de la barra de herramientas
        TextView toolbarTitle = findViewById(R.id.seetoolbarTitle);
        toolbarTitle.setText("Menu Principal");

        ViAd = (Button) findViewById(R.id.viAd);
        ViCl = (Button) findViewById(R.id.viCl);
        ViEn = (Button) findViewById(R.id.viEn);
        txt = (TextView) findViewById(R.id.textView);
        registerForContextMenu(ViAd);
        registerForContextMenu(ViCl);
        registerForContextMenu(ViEn);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        ViAd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                i = new Intent(SeeOtherViews.this, AdPClases.class);
                startActivity(i);
            }
        });
        ViCl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                i = new Intent(SeeOtherViews.this, principal.class);
                startActivity(i);
            }
        });
        ViEn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                i = new Intent(SeeOtherViews.this, Home.class);
                startActivity(i);
            }
        });

    }
    //1. Opciones Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_admin_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         if (item.getItemId() == R.id.m2) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(SeeOtherViews.this, menuRL.class));
            Toast.makeText(this, "Cerraste sesion", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}