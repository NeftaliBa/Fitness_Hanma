package com.example.fitnes_hanma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.fitnes_hanma.Cliente.principal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class pantalla_carga extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_pantalla_carga);

        firebaseAuth = FirebaseAuth.getInstance();

        int Tiempo = 3000;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               /* startActivity(new Intent(pantalla_carga.this, menuRL.class));
                finish(); */
                VerificarUsuario();
            }
        }, Tiempo);
    }

    private void VerificarUsuario(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser == null) {
            startActivity(new Intent(pantalla_carga.this, menuRL.class));
            finish();
        }else {
            startActivity(new Intent(pantalla_carga.this, principal.class));
            finish();
        }
    }
}