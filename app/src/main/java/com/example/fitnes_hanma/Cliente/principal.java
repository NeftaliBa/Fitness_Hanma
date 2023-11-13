package com.example.fitnes_hanma.Cliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fitnes_hanma.Instructor.configuraciones.Configuraciones;
import com.example.fitnes_hanma.MenuConceptual;
import com.example.fitnes_hanma.Objetos.Clases;
import com.example.fitnes_hanma.Objetos.ClasesCienteAdapter;
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
    Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_cl_principal);

        // Configurar el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Configurar el título de la barra de herramientas
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Menu Principal");


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
            i = new Intent(principal.this, Configuracion.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.m3) {
            finish();
        }
        return true;
    }
}