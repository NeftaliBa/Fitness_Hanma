package com.example.fitnes_hanma.Instructor.calendario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.example.fitnes_hanma.Objetos.Clases;
import com.example.fitnes_hanma.Objetos.ClasesAdapter;
import com.example.fitnes_hanma.Objetos.ClasesClienteServicioAdapter;
import com.example.fitnes_hanma.Objetos.ClassesCalendario;
import com.example.fitnes_hanma.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Calendario extends AppCompatActivity {

    private List<TextView> dayTextViews;
    ListView calendarListView;

    Intent i;
    EditText searchClases;
    List<Clases> clasesList;
    List<Clases> filteredList; 
    ClasesAdapter adapter;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_cla_calendario);

        // Obtén referencias a los TextViews
        dayTextViews = new ArrayList<>();
        dayTextViews.add(findViewById(R.id.dom));
        dayTextViews.add(findViewById(R.id.lun));
        dayTextViews.add(findViewById(R.id.mar));
        dayTextViews.add(findViewById(R.id.mie));
        dayTextViews.add(findViewById(R.id.jue));
        dayTextViews.add(findViewById(R.id.vie));
        dayTextViews.add(findViewById(R.id.sab));

        ListView calendarListView = findViewById(R.id.calendarListView);
        List<Clases> clasesCalendarList = new ArrayList<>();
        ClassesCalendario adapter = new ClassesCalendario(this, clasesCalendarList);

        // Configura el adaptador con el ListView
        calendarListView.setAdapter(adapter);

        // Recupera las clases de Firebase Firestore y agrega a la lista
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference clasesRef = db.collection("clases");

        clasesList = new ArrayList<>();
        filteredList = new ArrayList<>();

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


        // Agrega OnClickListener a cada TextView
        for (TextView textView : dayTextViews) {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Restaura el color de fondo de todos los TextViews
                    for (TextView tv : dayTextViews) {
                        tv.setBackgroundResource(R.drawable.b_cla_fondo_days);
                    }

                    // Cambia el color de fondo del TextView seleccionado
                    v.setBackgroundResource(R.drawable.b_cla_fondo_days_colored);
                }
            });
        }

    }
}


