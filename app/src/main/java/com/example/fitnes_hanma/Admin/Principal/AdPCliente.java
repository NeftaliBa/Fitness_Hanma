package com.example.fitnes_hanma.Admin.Principal;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;

import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.fitnes_hanma.MenuConceptual;
import com.example.fitnes_hanma.R;
import com.example.fitnes_hanma.Objetos.Usuarios;
import com.example.fitnes_hanma.Objetos.UsuarioAdapter;
import com.example.fitnes_hanma.Admin.Secundarias.AdSModCli;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class AdPCliente extends AppCompatActivity {
    Intent i;
    EditText searchClient;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_ad_p_cliente);

        searchClient = (EditText) findViewById(R.id.seCli);
        ImageView buscar = findViewById(R.id.buscar);
        ImageView regre = findViewById(R.id.regre);
        ListView listViewClientes = findViewById(R.id.listviewCliente);

        List<Usuarios> clientList = new ArrayList<>();
        UsuarioAdapter adapter = new UsuarioAdapter(this, clientList);

        // Configura el adaptador con el ListView
        listViewClientes.setAdapter(adapter);

        // Recupera las clases de Firebase Firestore y agrega a la lista
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference userRef = db.collection("user");

        // Obtén todos los usuarios por defecto
        obtenerUsuarios(userRef, adapter, clientList);

        // Agrega un TextWatcher al EditText para escuchar los cambios en el texto de búsqueda
        searchClient.addTextChangedListener(new TextWatcher() {
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
                    // Si el campo de búsqueda está vacío, obtén todos los usuarios nuevamente
                    obtenerUsuarios(userRef, adapter, clientList);
                } else {
                    // Filtra la lista de usuarios al escribir en el EditText
                    filtrarUsuarios(userRef, adapter, clientList, searchText);
                }
            }
        });

        listViewClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtén la clase seleccionada
                Usuarios clienteSeleccionado = clientList.get(position);

                // Pasa los datos necesarios a AdSModCla
                Intent intent = new Intent(AdPCliente.this, AdSModCli.class);
                intent.putExtra("name", clienteSeleccionado.getName());
                intent.putExtra("email", clienteSeleccionado.getEmail());
                intent.putExtra("role", clienteSeleccionado.getRole());
                intent.putExtra("id", clienteSeleccionado.getId());
                startActivity(intent);
            }
        });
        regre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(AdPCliente.this, MenuConceptual.class);
                startActivity(i);
            }
        });
    }
    private void obtenerUsuarios(CollectionReference userRef, UsuarioAdapter adapter, List<Usuarios> clientList) {
        userRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                try {
                    Log.d("AdPCliente", "Número de documentos recuperados: " + queryDocumentSnapshots.size());
                    clientList.clear();

                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Usuarios usuario = documentSnapshot.toObject(Usuarios.class);
                        if (usuario != null) {
                            Log.d("AdPCliente", "Usuario recuperado: " + usuario.getName());
                            clientList.add(usuario);
                        }
                    }
                    // Notifica al adaptador que los datos han cambiado
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Log.e("AdPCliente", "Error al procesar documentos", e);
                }
            }
        });
    }

    private void filtrarUsuarios(CollectionReference userRef, UsuarioAdapter adapter, List<Usuarios> clientList, String searchText) {
        // Filtra los usuarios por el campo 'email'
        Query query = userRef.whereGreaterThanOrEqualTo("email", searchText)
                .whereLessThanOrEqualTo("email", searchText + "\uf8ff"); // \uf8ff es un carácter Unicode alto para garantizar el final del rango

        Log.d("FiltrarUsuarios", "Search Text: " + searchText);

        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                try {
                    Log.d("AdPCliente", "Número de documentos recuperados: " + queryDocumentSnapshots.size());
                    clientList.clear();

                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Usuarios usuario = documentSnapshot.toObject(Usuarios.class);
                        if (usuario != null) {
                            Log.d("AdPCliente", "Usuario recuperado: " + usuario.getName());
                            clientList.add(usuario);
                        }
                    }
                    // Notifica al adaptador que los datos han cambiado
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Log.e("AdPCliente", "Error al procesar documentos", e);
                }
            }
        });
    }
}


