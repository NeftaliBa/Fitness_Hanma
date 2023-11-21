package com.example.fitnes_hanma.Cliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnes_hanma.Objetos.Clases;
import com.example.fitnes_hanma.Objetos.claseInscribir;
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

public class servicios extends AppCompatActivity {
    Intent i;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_cl_servicios);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        // Configurar el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Configurar el título de la barra de herramientas
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Servicios");

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        ListView listViewClases = findViewById(R.id.listViewClaCliente);
        List<Clases> clasesList = new ArrayList<>();
        claseInscribir adapter = new claseInscribir(this, clasesList);

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
        listViewClases.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtén la clase seleccionada
                Clases claseSeleccionada = clasesList.get(position);

                // Pasa los datos necesarios a AdSModCla
                Intent intent = new Intent(servicios.this, c_cl_perfil_clases.class);
                intent.putExtra("nombreClase", claseSeleccionada.getNombreClase());
                intent.putExtra("descripcion", claseSeleccionada.getDescripcion());
                intent.putExtra("correoInstructor", claseSeleccionada.getSearchInstructor());
                intent.putExtra("horaClase", claseSeleccionada.getHoraClase());
                intent.putExtra("limCli", claseSeleccionada.getLimCli());

                // Incluso puedes pasar el ID del documento si lo necesitas
                intent.putExtra("idDocumento", claseSeleccionada.getId_clase());

                startActivity(intent);
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
            i = new Intent(servicios.this, Configuracion.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.m2) {
            firebaseAuth.signOut();
            startActivity(new Intent(servicios.this, menuRL.class));
            Toast.makeText(this, "Cerraste sesion", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}