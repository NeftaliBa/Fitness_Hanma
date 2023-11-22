package com.example.fitnes_hanma.Cliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fitnes_hanma.R;

public class c_cl_perfil_clases extends AppCompatActivity {

    TextView nomCla, desCla, nomIns ,dia1, dia2, dia3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccl_perfil_clases);

        nomCla = (TextView) findViewById(R.id.nombreClaseTextView);
        desCla = (TextView) findViewById(R.id.descripcionTextView);
        nomIns = (TextView) findViewById(R.id.nombreInstructorTextViewc_cl);
        dia1 =  (TextView)  findViewById(R.id.dia1Perfil);
        dia2 =  (TextView)  findViewById(R.id.dia2Perfil);
        dia3 =  (TextView)  findViewById(R.id.dia3Perfil);


        // Configurar el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configurar el botón de retroceso
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Al presionar el botón de retroceso, ir al activity principal
                Intent intent = new Intent(c_cl_perfil_clases.this, servicios.class);
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
    }
}