package com.example.fitnes_hanma.Admin.Principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fitnes_hanma.Admin.Secundarias.AdSModCli;
import com.example.fitnes_hanma.Admin.Secundarias.AdSModIns;
import com.example.fitnes_hanma.MenuConceptual;
import com.example.fitnes_hanma.Objetos.Instructor;
import com.example.fitnes_hanma.Objetos.InstructorAdapter;
import com.example.fitnes_hanma.Objetos.UsuarioAdapter;
import com.example.fitnes_hanma.Objetos.Usuarios;
import com.example.fitnes_hanma.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AdPInstructor extends AppCompatActivity {
    Intent i;
    EditText searchClient;
    TextView name, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_ad_p_instructor);

        searchClient = (EditText) findViewById(R.id.seCli);
        ImageView buscar = findViewById(R.id.buscar);
        ImageView regre = findViewById(R.id.regre);
        ImageView plus = findViewById(R.id.plus);

        ListView listViewInstructor = findViewById(R.id.listViewInstructor);


        List<Instructor> intructList = new ArrayList<>();
        InstructorAdapter adapter = new InstructorAdapter(this, intructList);

        // Configura el adaptador con el ListView
        listViewInstructor.setAdapter(adapter);

        // Recupera las clases de Firebase Firestore y agrega a la lista
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference userRef = db.collection("Instructor");

        userRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                Log.d("AdPInstructor", "Número de documentos recuperados: " + queryDocumentSnapshots.size());
                intructList.clear();

                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Instructor instruc = documentSnapshot.toObject(Instructor.class);
                    if (instruc != null) {
                        Log.d("AdPInstructor", "Usuario recuperado: " + instruc.getNombre());
                        intructList.add(instruc);
                    }
                }
                // Notifica al adaptador que los datos han cambiado
                adapter.notifyDataSetChanged();
            }
        });
        listViewInstructor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtén la clase seleccionada
                Instructor instructorSelec = intructList.get(position);

                // Pasa los datos necesarios a AdSModCla
                Intent intent = new Intent(AdPInstructor.this, AdSModIns.class);
                intent.putExtra("Nombre", instructorSelec.getNombre());
                intent.putExtra("Correo", instructorSelec.getCorreo());
                startActivity(intent);
            }
        });
        regre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(AdPInstructor.this, MenuConceptual.class);
                startActivity(i);
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(AdPInstructor.this, AdPCliente.class);
                startActivity(i);
            }
        });
    }
}
