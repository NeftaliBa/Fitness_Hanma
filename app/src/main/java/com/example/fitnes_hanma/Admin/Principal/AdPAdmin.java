package com.example.fitnes_hanma.Admin.Principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.fitnes_hanma.Admin.Secundarias.AdSModAdm;
import com.example.fitnes_hanma.MenuConceptual;
import com.example.fitnes_hanma.Objetos.Administrador;
import com.example.fitnes_hanma.Objetos.AdministradorAdapter;
import com.example.fitnes_hanma.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AdPAdmin extends AppCompatActivity {
    Intent i;
    EditText searchAdmin; // Cambié el nombre del EditText a searchAdmin
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_ad_p_admin);
        searchAdmin = findViewById(R.id.seaAdm); // Cambié el ID para que coincida con el layout actual
        ImageView buscar = findViewById(R.id.buscarAdm);
        ImageView regre = findViewById(R.id.regre);
        ListView listViewAdmin = findViewById(R.id.listviewAdmin);

        List<Administrador> adminList = new ArrayList<>();
        AdministradorAdapter adapter = new AdministradorAdapter(this, adminList);

        // Configura el adaptador con el ListView
        listViewAdmin.setAdapter(adapter);

        // Recupera las clases de Firebase Firestore y agrega a la lista
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference adminRef = db.collection("admin");

        // Obtén todos los administradores por defecto
        obtenerAdministradores(adminRef, adapter, adminList);

        // Agrega un TextWatcher al EditText para escuchar los cambios en el texto de búsqueda
        searchAdmin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("TextWatcher", "beforeTextChanged: " + charSequence.toString());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("TextWatcher", "onTextChanged: " + charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d("TextWatcher", "afterTextChanged: " + editable.toString());
                String searchText = editable.toString().trim();
                Log.d("SearchText", "Search Text: " + searchText);
                if (searchText.isEmpty()) {
                    // Si el campo de búsqueda está vacío, obtén todos los administradores nuevamente
                    obtenerAdministradores(adminRef, adapter, adminList);
                } else {
                    // Filtra la lista de administradores al escribir en el EditText
                    filtrarAdministradores(adminRef, adapter, adminList, searchText);
                }
            }
        });

        listViewAdmin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtén la clase seleccionada
                Administrador adminSeleccionado = adminList.get(position);

                // Pasa los datos necesarios a AdSModAdm
                Intent intent = new Intent(AdPAdmin.this, AdSModAdm.class);
                intent.putExtra("aname", adminSeleccionado.getAname());
                intent.putExtra("aemail", adminSeleccionado.getAemail());
                intent.putExtra("arole", adminSeleccionado.getArole());
                intent.putExtra("aid", adminSeleccionado.getAid());
                startActivity(intent);
            }
        });

        regre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(AdPAdmin.this, MenuConceptual.class);
                startActivity(i);
            }
        });
    }

    private void obtenerAdministradores(CollectionReference adminRef, AdministradorAdapter adapter, List<Administrador> adminList) {
        adminRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                try {
                    Log.d("AdPAdmin", "Número de documentos recuperados: " + queryDocumentSnapshots.size());
                    adminList.clear();

                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Administrador admin = documentSnapshot.toObject(Administrador.class);
                        if (admin != null) {
                            Log.d("AdPAdmin", "Administrador recuperado: " + admin.getAname());
                            adminList.add(admin);
                        }
                    }
                    // Notifica al adaptador que los datos han cambiado
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Log.e("AdPAdmin", "Error al procesar documentos", e);
                }
            }
        });
    }

    private void filtrarAdministradores(CollectionReference adminRef, AdministradorAdapter adapter, List<Administrador> adminList, String searchText) {
        // Filtra los administradores por el campo 'aemail'
        Query query = adminRef.whereGreaterThanOrEqualTo("aemail", searchText)
                .whereLessThanOrEqualTo("aemail", searchText + "\uf8ff");

        Log.d("FiltrarAdministradores", "Search Text: " + searchText);

        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                try {
                    Log.d("AdPAdmin", "Número de documentos recuperados: " + queryDocumentSnapshots.size());
                    adminList.clear();

                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Administrador admin = documentSnapshot.toObject(Administrador.class);
                        if (admin != null) {
                            Log.d("AdPAdmin", "Administrador recuperado: " + admin.getAname());
                            adminList.add(admin);
                        }
                    }
                    // Notifica al adaptador que los datos han cambiado
                    adapter.notifyDataSetChanged();
                    } catch (Exception e) {
                    Log.e("AdPAdmin", "Error al procesar documentos", e);
                }
            }
        });
    }
}