package com.example.fitnes_hanma.Admin.Principal;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.fitnes_hanma.Actividad8.MenuConceptual;
import com.example.fitnes_hanma.Objetos.ClasesAdapter;
import com.example.fitnes_hanma.Admin.Secundarias.AdSCreCla;
import com.example.fitnes_hanma.Admin.Secundarias.AdSModCla;
import com.example.fitnes_hanma.R;

import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import com.example.fitnes_hanma.Objetos.Clases;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;



public class AdPClases extends AppCompatActivity {

    Intent i;
    EditText searchClases;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_p_clases);

        searchClases = (EditText) findViewById(R.id.seCla);
        ImageView buscar = findViewById(R.id.buscar);
        ImageView regre = findViewById(R.id.regre);
        ImageView plus = findViewById(R.id.plus);
        regre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(AdPClases.this, MenuConceptual.class);
                startActivity(i);
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(AdPClases.this, AdSCreCla.class);
                startActivity(i);
            }
        });
        ListView listViewClases = findViewById(R.id.listViewClases);
        List<Clases> clasesList = new ArrayList<>();
        ClasesAdapter adapter = new ClasesAdapter(this, clasesList);

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
                Intent intent = new Intent(AdPClases.this, AdSModCla.class);
                intent.putExtra("nombreClase", claseSeleccionada.getNombreClase());
                intent.putExtra("descripcion", claseSeleccionada.getDescripcion());
                intent.putExtra("nombreInstructor", claseSeleccionada.getNombreInstructor());
                intent.putExtra("fechaClase", claseSeleccionada.getFechaClase());
                intent.putExtra("horaClase", claseSeleccionada.getHoraClase());
                intent.putExtra("limCli", claseSeleccionada.getCliRegis());

                // Incluso puedes pasar el ID del documento si lo necesitas
                intent.putExtra("idDocumento", claseSeleccionada.getId_clase());

                startActivity(intent);
            }
        });
    }
}
