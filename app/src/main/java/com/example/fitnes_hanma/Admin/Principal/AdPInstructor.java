package com.example.fitnes_hanma.Admin.Principal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
import android.widget.TextView;

import com.example.fitnes_hanma.Admin.Secundarias.AdSModIns;
import com.example.fitnes_hanma.MenuConceptual;
import com.example.fitnes_hanma.Objetos.Instructor;
import com.example.fitnes_hanma.Objetos.InstructorAdapter;
import com.example.fitnes_hanma.R;
import com.example.fitnes_hanma.menuRL;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AdPInstructor extends AppCompatActivity {
    Intent i;
    EditText searchInstructor; // Cambié el nombre del EditText a searchInstructor
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_ad_p_instructor);

        // Configurar el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configurar el botón de retroceso
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Al presionar el botón de retroceso, ir al activity principal
                Intent intent = new Intent(AdPInstructor.this, MenuConceptual.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Configurar el título de la barra de herramientas
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Menú de Instructor");

        searchInstructor = findViewById(R.id.seIns); // Cambié el ID a coincidir con el layout actual
        ImageView plus = findViewById(R.id.plus);

        ListView listViewInstructor = findViewById(R.id.listViewInstructor);

        List<Instructor> instructorList = new ArrayList<>();
        InstructorAdapter adapter = new InstructorAdapter(this, instructorList);

        // Configura el adaptador con el ListView
        listViewInstructor.setAdapter(adapter);

        // Recupera las clases de Firebase Firestore y agrega a la lista
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference instructorRef = db.collection("trainer");

        // Obtén todos los instructores por defecto
        obtenerInstructores(instructorRef, adapter, instructorList);

        // Agrega un TextWatcher al EditText para escuchar los cambios en el texto de búsqueda
        searchInstructor.addTextChangedListener(new TextWatcher() {
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
                    // Si el campo de búsqueda está vacío, obtén todos los instructores nuevamente
                    obtenerInstructores(instructorRef, adapter, instructorList);
                } else {
                    // Filtra la lista de instructores al escribir en el EditText
                    filtrarInstructores(instructorRef, adapter, instructorList, searchText);
                }
            }
        });

        listViewInstructor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtén el instructor seleccionado
                Instructor instructorSeleccionado = instructorList.get(position);

                // Pasa los datos necesarios a AdSModIns
                Intent intent = new Intent(AdPInstructor.this, AdSModIns.class);
                intent.putExtra("tname", instructorSeleccionado.getTname());
                intent.putExtra("temail", instructorSeleccionado.getTemail());
                intent.putExtra("trole", instructorSeleccionado.getTrole());
                intent.putExtra("tid", instructorSeleccionado.getTid());
                startActivity(intent);
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(AdPInstructor.this, AdSModIns.class);
                startActivity(i);
            }
        });
    }

    private void obtenerInstructores(CollectionReference instructorRef, InstructorAdapter adapter, List<Instructor> instructorList) {
        instructorRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                try {
                    Log.d("AdPInstructor", "Número de documentos recuperados: " + queryDocumentSnapshots.size());
                    instructorList.clear();

                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Instructor instructor = documentSnapshot.toObject(Instructor.class);
                        if (instructor != null) {
                            Log.d("AdPInstructor", "Instructor recuperado: " + instructor.getTname());
                            instructorList.add(instructor);
                        }
                    }
                    // Notifica al adaptador que los datos han cambiado
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Log.e("AdPInstructor", "Error al procesar documentos", e);
                }
            }
        });
    }

    private void filtrarInstructores(CollectionReference instructorRef, InstructorAdapter adapter, List<Instructor> instructorList, String searchText) {
        // Filtra los instructores por el campo 'temail'
        Query query = instructorRef.whereGreaterThanOrEqualTo("temail", searchText)
                .whereLessThanOrEqualTo("temail", searchText + "\uf8ff");

        Log.d("FiltrarInstructores", "Search Text: " + searchText);

        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                try {
                    Log.d("AdPInstructor", "Número de documentos recuperados: " + queryDocumentSnapshots.size());
                    instructorList.clear();

                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Instructor instructor = documentSnapshot.toObject(Instructor.class);
                        if (instructor != null) {
                            Log.d("AdPInstructor", "Instructor recuperado: " + instructor.getTname());
                            instructorList.add(instructor);
                        }
                    }
                    // Notifica al adaptador que los datos han cambiado
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Log.e("AdPInstructor", "Error al procesar documentos", e);
                }
            }
        });
    }
}
