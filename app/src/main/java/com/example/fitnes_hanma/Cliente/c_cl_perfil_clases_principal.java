package com.example.fitnes_hanma.Cliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fitnes_hanma.R;

public class c_cl_perfil_clases_principal extends AppCompatActivity {

    TextView nomCla, desCla, nomIns ,dia1, dia2, dia3;
    LinearLayout btnSalir;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_cl_perfil_clases_principal);

        nomCla = (TextView) findViewById(R.id.nameClaPri);
        nomIns = (TextView) findViewById(R.id.nameInsPrincipal);
        desCla = (TextView) findViewById(R.id.decClaPrincipal);
        dia1 =  (TextView)  findViewById(R.id.dia1ClaPrin);
        dia2 =  (TextView)  findViewById(R.id.dia2ClaPrin);
        dia3 =  (TextView)  findViewById(R.id.dia3ClaPrin);
        btnSalir = (LinearLayout) findViewById(R.id.btnSalir);
        image = (ImageView) findViewById(R.id.image);

        // Configurar el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbarClaPrincipal);
        setSupportActionBar(toolbar);

        // Configurar el botón de retroceso
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Al presionar el botón de retroceso, ir al activity principal
                Intent intent = new Intent(c_cl_perfil_clases_principal.this, servicios.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Configurar el título de la barra de herramientas
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("");

        Intent intent = getIntent();
        if (intent != null) {
            String nombreClase = intent.getStringExtra("nombreClase");
            String descripcion = intent.getStringExtra("descripcion");
            String nombreInstructor = intent.getStringExtra("nombreInstructor");
            String horario1 = intent.getStringExtra("hor1");
            String horario2 = intent.getStringExtra("hor2");
            String horario3 = intent.getStringExtra("hor3");

            // Configura los campos con los datos
            nomCla.setText(nombreClase);
            desCla.setText(descripcion);
            nomIns.setText(nombreInstructor);
            dia1.setText(horario1);
            dia2.setText(horario2);
            dia3.setText(horario3);
        }
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c_cl_perfil_clases_principal.this, principal.class);
                startActivity(intent);
            }
        });
    }
}