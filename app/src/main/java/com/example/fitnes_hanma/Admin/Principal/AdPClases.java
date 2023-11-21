package com.example.fitnes_hanma.Admin.Principal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.fitnes_hanma.Admin.SeeOtherViews;
import com.example.fitnes_hanma.Objetos.ClasesAdapter;
import com.example.fitnes_hanma.Admin.Secundarias.AdSCreCla;
import com.example.fitnes_hanma.Admin.Secundarias.AdSModCla;
import com.example.fitnes_hanma.R;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import com.example.fitnes_hanma.Objetos.Clases;
import com.example.fitnes_hanma.menuRL;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;



public class AdPClases extends AppCompatActivity {

    Intent i;
    EditText searchClases;
    List<Clases> clasesList;  // Declarar como variable de instancia
    List<Clases> filteredList;  // Lista temporal para la búsqueda
    ClasesAdapter adapter;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_ad_p_clases);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        // Configurar el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configurar el botón de retroceso
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Al presionar el botón de retroceso, ir al activity principal
                Intent intent = new Intent(AdPClases.this, SeeOtherViews.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Configurar el título de la barra de herramientas
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Menú de Clases");


        searchClases = (EditText) findViewById(R.id.seCla);
        ImageView plus = findViewById(R.id.plus);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(AdPClases.this, AdSCreCla.class);
                startActivity(i);
            }
        });

        // Inicializa las listas
        clasesList = new ArrayList<>();
        filteredList = new ArrayList<>();

        searchClases.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No es necesario implementar este método
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Filtra la lista de clases según el texto ingresado
                filterClasses(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No es necesario implementar este método
            }
        });

        ListView listViewClases = findViewById(R.id.listViewClases);
        adapter = new ClasesAdapter(this, filteredList);

        // Configura el adaptador con el ListView
        listViewClases.setAdapter(adapter);

        // Recupera las clases de Firebase Firestore y agrega a la lista
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference clasesRef = db.collection("clases");

        clasesRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                // Limpia las listas antes de agregar elementos
                clasesList.clear();
                filteredList.clear();

                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Clases clase = documentSnapshot.toObject(Clases.class);
                    if (clase != null) {
                        clasesList.add(clase);
                    }
                }

                // Inicializa la lista temporal con todas las clases
                filteredList.addAll(clasesList);

                // Notifica al adaptador que los datos han cambiado
                adapter.notifyDataSetChanged();
            }
        });

        listViewClases.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtén la clase seleccionada
                Clases claseSeleccionada = filteredList.get(position);

                // Pasa los datos necesarios a AdSModCla
                Intent intent = new Intent(AdPClases.this, AdSModCla.class);
                intent.putExtra("nombreClase", claseSeleccionada.getNombreClase());
                intent.putExtra("descripcion", claseSeleccionada.getDescripcion());
                intent.putExtra("correoInstructor", claseSeleccionada.getSearchInstructor());
                intent.putExtra("horaClase", claseSeleccionada.getHoraClase());
                intent.putExtra("limCli", claseSeleccionada.getLimCli());


                intent.putExtra("id_clase", claseSeleccionada.getId_clase());

                startActivity(intent);
            }
        });
    }

    private void filterClasses(String searchText) {
        filteredList.clear();  // Limpia la lista temporal

        // Si el searchText está vacío, cargar todas las clases
        if (searchText.isEmpty()) {
            filteredList.addAll(clasesList);
        } else {
            // Filtra por nombre de instructor o nombre de clase
            for (Clases clase : clasesList) {
                if (clase.getSearchInstructor().toLowerCase().contains(searchText.toLowerCase()) ||
                        clase.getNombreClase().toLowerCase().contains(searchText.toLowerCase())) {
                    filteredList.add(clase);
                }
            }
        }

        // Notifica al adaptador que los datos han cambiado
        adapter.notifyDataSetChanged();
    }


    //1. Opciones Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_admin_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.m2) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(AdPClases.this, menuRL.class));
            Toast.makeText(this, "Cerraste sesion", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}