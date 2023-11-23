package com.example.fitnes_hanma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fitnes_hanma.Instructor.configuraciones.ConfiguracionIns;
import com.example.fitnes_hanma.Instructor.home.Home;

public class sobre_nosotros extends AppCompatActivity {
    TextView d1, d2, d3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre_nosotros);

        // Configurar el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Sobre nosotros");

        // Configurar el botón de retroceso
        ImageView backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Al presionar el botón de retroceso, ir al activity principal
                Intent intent = new Intent(sobre_nosotros.this, ConfiguracionIns.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        d1 = findViewById(R.id.d1);
        d2 = findViewById(R.id.d2);
        d3 = findViewById(R.id.d3);

        d1.setText("Desarrollador principiante de Aplicaciones Moviles encargado principalmente de la parte front-end y una gran parte de back-end y gestor de la base de datos, con cosas que mejorar como su adiccion a la morras culonas y la musica triste");
        d2.setText("Desarrollador principiante de Aplicaciones Moviles encargado principalmente de la base de datos y una gran mayoria en desarrollo Back-end, con cosas a mejorar como el Frond-end y su adiccion a la mota");
        d3.setText("Desarrolladora principiante de Aplicaciones Moviles encargado principalmente documentación");
    }
}