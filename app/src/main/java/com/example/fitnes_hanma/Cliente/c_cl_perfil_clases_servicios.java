package com.example.fitnes_hanma.Cliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnes_hanma.Objetos.Inscripciones;
import com.example.fitnes_hanma.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class c_cl_perfil_clases_servicios extends AppCompatActivity {

    TextView nomCla, desCla, nomIns ,dia1, dia2, dia3;
    LinearLayout btnInscribirme;
    FirebaseAuth firebaseAuth;
    String userId, nombreClase , descripcion, nombreInstructor, horario1, horario2, horario3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_cl_perfil_clases_servicios);

        nomCla = (TextView) findViewById(R.id.nombreClaseTextView);
        desCla = (TextView) findViewById(R.id.descripcionTextView);
        nomIns = (TextView) findViewById(R.id.nombreInstructorTextViewc_cl);
        dia1 =  (TextView)  findViewById(R.id.dia1Perfil);
        dia2 =  (TextView)  findViewById(R.id.dia2Perfil);
        dia3 =  (TextView)  findViewById(R.id.dia3Perfil);
        btnInscribirme = (LinearLayout) findViewById(R.id.btnInscribirme);

        // Configurar el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configurar el botón de retroceso
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Al presionar el botón de retroceso, ir al activity principal
                Intent intent = new Intent(c_cl_perfil_clases_servicios.this, servicios.class);
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
            String idClase = intent.getStringExtra("idDocumento");
            Log.d("ID_CLASE", "ID de la clase: " + idClase);

            nombreClase = intent.getStringExtra("nombreClase");
            descripcion = intent.getStringExtra("descripcion");
            nombreInstructor = intent.getStringExtra("nombreInstructor");
            horario1 = intent.getStringExtra("hor1");
            horario2 = intent.getStringExtra("hor2");
            horario3 = intent.getStringExtra("hor3");

            // Configura los campos con los datos

            nomCla.setText(nombreClase);
            desCla.setText(descripcion);
            nomIns.setText(nombreInstructor);
            dia1.setText(horario1);
            dia2.setText(horario2);
            dia3.setText(horario3);
        }
        btnInscribirme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                userId = firebaseUser.getUid();

                String clienteID = userId;
                String claseID = getIntent().getStringExtra("idDocumento");
                Inscripciones inscripcion = new Inscripciones();
                inscripcion.setCleinteID(clienteID);
                inscripcion.setClaseID(claseID);
                guardarInscripcion(inscripcion);


                Intent intent = new Intent(c_cl_perfil_clases_servicios.this, servicios.class);
                startActivity(intent);
            }
        });
    }
    private void guardarInscripcion(Inscripciones inscripcion) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference inscripcionesRef = db.collection("inscripciones");

        inscripcionesRef.add(inscripcion)
                .addOnSuccessListener(documentReference -> {
                    Log.d("Inscripción", "Inscripción guardada con ID: " + documentReference.getId());
                    Toast.makeText(this, "Se ha incrito a "+nombreClase, Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    // Ocurrió un error al guardar la inscripción
                    Log.e("Inscripción", "Error al guardar la inscripción", e);
                });
    }

}