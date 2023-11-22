package com.example.fitnes_hanma.Instructor.home;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnes_hanma.Instructor.configuraciones.ConfiguracionIns;
import com.example.fitnes_hanma.Objetos.Clases;
import com.example.fitnes_hanma.Objetos.ClasesAdapterInstructor;
import com.example.fitnes_hanma.R;
import com.example.fitnes_hanma.menuRL;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_cla_home);

        // Configurar el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Configurar el título de la barra de herramientas
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Menu Principal");

        ImageView QR;
        QR = (ImageView) findViewById(R.id.qr);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        QR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Home_QR.class);
                startActivity(intent);
            }
        });


        ListView listViewInsClases = findViewById(R.id.listViewInsClases);
        List<Clases> clasesInsList = new ArrayList<>();
        ClasesAdapterInstructor adapter = new ClasesAdapterInstructor(this, clasesInsList);
        listViewInsClases.setAdapter(adapter);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference clasesRef = db.collection("clases");
        String instructorID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        clasesRef.whereEqualTo("iDInstructor", instructorID)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        // Borra la lista de clases actual
                        clasesInsList.clear();

                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            Clases clase = documentSnapshot.toObject(Clases.class);
                            if (clase != null) {
                                clasesInsList.add(clase);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

// ...

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
            i = new Intent(Home.this, ConfiguracionIns.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.m2) {
            firebaseAuth.signOut();
            startActivity(new Intent(Home.this, menuRL.class));
            Toast.makeText(this, "Cerraste sesion", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
