package com.example.fitnes_hanma.Cliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fitnes_hanma.Instructor.configuraciones.Configuraciones;
import com.example.fitnes_hanma.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class asistenciaQR extends AppCompatActivity {
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_cl_asistencia_qr);



        // Configurar el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Configurar el título de la barra de herramientas
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Asistencia por QR");


        // Obtener referencia al botón de escaneo
        Button scanButton = findViewById(R.id.scanButton);

        // Agregar un listener al botón de escaneo
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la biblioteca ZXing para escanear
                new IntentIntegrator(asistenciaQR.this).initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                // Aquí puedes manejar el resultado del escaneo (result.getContents())
                // Puedes realizar acciones adicionales según tu lógica de la aplicación
            }
        }
    }
    //1. Opciones Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.c_cli_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.m1) {
            i = new Intent(asistenciaQR.this, Configuracion.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.m3) {
            finish();
        }
        return true;
    }
}