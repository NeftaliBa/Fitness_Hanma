package com.example.fitnes_hanma.Instructor.configuraciones;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnes_hanma.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracionIns extends AppCompatActivity {

    private EditText cambiarNombre;
    private Button btnCambiarNombre;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_cla_configuraciones); // Reemplaza "tu_layout" por el nombre de tu archivo XML

        // Obtener la instancia de FirebaseAuth y DatabaseReference
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Vincular elementos de UI con variables en Java
        cambiarNombre = findViewById(R.id.cambiarNombre);
        btnCambiarNombre = findViewById(R.id.BtnCambiarNombre);

        // Agregar un listener al botón de cambiar nombre
        btnCambiarNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuevoNombre = cambiarNombre.getText().toString().trim();

                if (!TextUtils.isEmpty(nuevoNombre)) {
                    // Obtener el ID del usuario actual
                    String userId = firebaseAuth.getCurrentUser().getUid();

                    // Actualizar el nombre en la base de datos Firebase
                    databaseReference.child("Usuarios").child(userId).child("nombre").setValue(nuevoNombre);

                    // Muestra un mensaje de éxito al cambiar el nombre
                    Toast.makeText(ConfiguracionIns.this, "Nombre cambiado con éxito", Toast.LENGTH_SHORT).show();
                } else {
                    // Muestra un mensaje si el campo de nombre está vacío
                    Toast.makeText(ConfiguracionIns.this, "Por favor, ingresa un nombre", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
