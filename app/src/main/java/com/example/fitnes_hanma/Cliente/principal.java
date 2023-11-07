package com.example.fitnes_hanma.Cliente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.fitnes_hanma.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class principal extends AppCompatActivity {

    TextView bienvenidoUsu;
    String nameUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        bienvenidoUsu = findViewById(R.id.bienvenidoUsu); // Reemplaza con el ID real del TextView

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();

        FirebaseFirestore.getInstance().collection("users").document(userId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            nameUser = documentSnapshot.getString("name");
                            if (nameUser != null) {
                                String mensajeBienvenida = "Hola, " + nameUser + " en el activity_principal";
                                bienvenidoUsu.setText(mensajeBienvenida);
                            }
                        }
                    }
                });
    }
}
