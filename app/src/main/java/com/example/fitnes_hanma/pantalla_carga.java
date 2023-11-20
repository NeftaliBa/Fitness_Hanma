package com.example.fitnes_hanma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;


import com.example.fitnes_hanma.Admin.SeeOtherViews;
import com.example.fitnes_hanma.Cliente.principal;
import com.example.fitnes_hanma.Instructor.home.Home;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class pantalla_carga extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    String role, trole, arole;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_pantalla_carga);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        int Tiempo = 3000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                VerificarUsuario();
            }
        }, Tiempo);
    }

    private void VerificarUsuario() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser == null) {
            // No hay sesión iniciada, redirigir al menú principal
            startActivity(new Intent(pantalla_carga.this, menuRL.class));
            finish();
        } else {
            // Asegúrate de obtener el UID aquí después de verificar que firebaseUser no es nulo
            userId = firebaseUser.getUid();
            obtenerColeccionUsuario();
        }
    }


    private void obtenerColeccionUsuario() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Comprueba la existencia de documentos en las colecciones
        String userId = firebaseAuth.getCurrentUser().getUid(); // Asegúrate de obtener el UID aquí

        db.collection("admin").document(userId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult().exists()) {
                Log.d("Usuario", "Colección: Admin");
                startActivity(new Intent(pantalla_carga.this, SeeOtherViews.class));
                finish();
            } else {
                // Si no hay un documento en "admin", verifica "trainer"
                db.collection("trainer").document(userId).get().addOnCompleteListener(task1 -> {
                    if (task1.isSuccessful() && task1.getResult().exists()) {
                        Log.d("Usuario", "Colección: Trainer");
                        startActivity(new Intent(pantalla_carga.this, Home.class));
                        finish();
                    } else {
                        // Si no hay un documento en "trainer", asumimos "user"
                        Log.d("Usuario", "Colección: User");
                        startActivity(new Intent(pantalla_carga.this, principal.class));
                        finish();
                    }
                });
            }
        });
    }
}

