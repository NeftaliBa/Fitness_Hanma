package com.example.fitnes_hanma.Cliente;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fitnes_hanma.Admin.Objetos.Clases;
import com.example.fitnes_hanma.Admin.Objetos.ClasesCienteAdapter;
import com.example.fitnes_hanma.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class principal extends AppCompatActivity {

    TextView bienvenidoUsu;
    String nameUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_cl_principal);

        bienvenidoUsu = findViewById(R.id.bienvenidoUsu);

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
        ListView listViewClases = findViewById(R.id.listViewClaCliente);
        List<Clases> clasesList = new ArrayList<>();
        ClasesCienteAdapter adapter = new ClasesCienteAdapter(this, clasesList);

        // Configura el adaptador con el ListView
        listViewClases.setAdapter(adapter);

        // Recupera las clases de Firebase Firestore y agrega a la lista
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference clasesRef = db.collection("clases");

        clasesRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                // Borra la lista de clases actual
                clasesList.clear();

                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Clases clase = documentSnapshot.toObject(Clases.class);
                    if (clase != null) {
                        clasesList.add(clase);
                    }
                }

                // Notifica al adaptador que los datos han cambiado
                adapter.notifyDataSetChanged();
            }
        });


    }
}