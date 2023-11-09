package com.example.fitnes_hanma.Actividad8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;


import com.example.fitnes_hanma.Admin.Principal.AdPClases;
import com.example.fitnes_hanma.Admin.Principal.AdPCliente;
import com.example.fitnes_hanma.Admin.Principal.AdPInstructor;
import com.example.fitnes_hanma.Cliente.asistencia;
import com.example.fitnes_hanma.Cliente.principal;
import com.example.fitnes_hanma.Cliente.servicios;
import com.example.fitnes_hanma.Instructor.calendario.Calendario;
import com.example.fitnes_hanma.Instructor.configuraciones.Configuraciones;
import com.example.fitnes_hanma.Instructor.grupo.Grupo;
import com.example.fitnes_hanma.Instructor.home.Home;
import com.example.fitnes_hanma.R;
import com.example.fitnes_hanma.inicio;
import com.example.fitnes_hanma.login;
import com.example.fitnes_hanma.recuperarconstrasena;
import com.example.fitnes_hanma.register;

public class MenuConceptual extends AppCompatActivity {


    Button ViAd, ViCl, ViEn;
    Intent i;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_conceptual);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViAd = (Button) findViewById(R.id.viAd);
        ViCl = (Button) findViewById(R.id.viCl);
        ViEn = (Button) findViewById(R.id.viEn);
        txt = (TextView) findViewById(R.id.textView);
        registerForContextMenu(ViAd);
        registerForContextMenu(ViCl);
        registerForContextMenu(ViEn);

        ViAd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                i = new Intent(MenuConceptual.this, AdPClases.class);
                startActivity(i);
            }
        });
        ViCl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                i = new Intent(MenuConceptual.this, register.class);
                startActivity(i);
            }
        });
        ViEn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                i = new Intent(MenuConceptual.this, Home.class);
                startActivity(i);
            }
        });
    }
    //1. menu principal
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.m1) {
            i = new Intent(MenuConceptual.this, Configuraciones.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.m3) {
            finish();
        }
        return true;
    }

    //3
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.viAd) {
            getMenuInflater().inflate(R.menu.menu_admin, menu);
        } else if (v.getId() == R.id.viEn) {
            getMenuInflater().inflate(R.menu.menu_instructor, menu);
        } else if (v.getId() == R.id.viCl) {
            getMenuInflater().inflate(R.menu.menu_clientes, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
            //Administrador
        if (item.getItemId() == R.id.i1) {
            i = new Intent(MenuConceptual.this, AdPClases.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.i2) {
            i = new Intent(MenuConceptual.this, AdPInstructor.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.i3) {
            i = new Intent(MenuConceptual.this, AdPCliente.class);
            startActivity(i);

            //Instructor
        } else if (item.getItemId() == R.id.s1) {
            i = new Intent(MenuConceptual.this, Home.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.s2) {
            i = new Intent(MenuConceptual.this, Calendario.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.s3) {
            i = new Intent(MenuConceptual.this, Grupo.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.s4) {
            i = new Intent(MenuConceptual.this, Configuraciones.class);
            startActivity(i);

            //Cliente
        } else if (item.getItemId() == R.id.bt1) {
            i = new Intent(MenuConceptual.this, register.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.bt2) {
            i = new Intent(MenuConceptual.this, login.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.bt3) {
            i = new Intent(MenuConceptual.this, recuperarconstrasena.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.bt4) {
            i = new Intent(MenuConceptual.this, asistencia.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.bt5) {
            i = new Intent(MenuConceptual.this, servicios.class);
            startActivity(i);
        }
        return true;
    }
}