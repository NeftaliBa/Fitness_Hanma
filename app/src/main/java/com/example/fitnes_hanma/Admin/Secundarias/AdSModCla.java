package com.example.fitnes_hanma.Admin.Secundarias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.fitnes_hanma.Admin.Principal.AdPInstructor;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnFailureListener;

import com.example.fitnes_hanma.Admin.Principal.AdPClases;
import com.example.fitnes_hanma.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AdSModCla extends AppCompatActivity {
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    EditText nomCla, desCla, nomIns, LimCli;
    Button cancelar, actualizar, hour;
    TextView fecha, hora;

    String classId;

    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_ad_s_mod_cla);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        nomCla = (EditText) findViewById(R.id.claNa);
        desCla = (EditText) findViewById(R.id.desCla);
        nomIns = (EditText) findViewById(R.id.naInst);
        LimCli = (EditText) findViewById(R.id.limCliM);
        cancelar = (Button) findViewById(R.id.cancel);
        hour = (Button) findViewById(R.id.hour);
        actualizar = (Button) findViewById(R.id.save);
        hora = (TextView) findViewById(R.id.hora);

        Intent intent = getIntent();
        if (intent != null) {
            String nombreClase = intent.getStringExtra("nombreClase");
            String descripcion = intent.getStringExtra("descripcion");
            String nombreInstructor = intent.getStringExtra("nombreInstructor");
            String horaClase = intent.getStringExtra("horaClase");
            String LimCliM = intent.getStringExtra("limCli");
            classId = intent.getStringExtra("id_clase");


            // Obtén una referencia a la colección "clases" en Firestore
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            CollectionReference clasesRef = db.collection("clases");

            // Crea un nuevo documento en la colección "clases"
            DocumentReference nuevaClaseRef = clasesRef.document();

            // Obtiene la ID generada automáticamente por Firebase
            String idDocumento = nuevaClaseRef.getId();

            // Configura los campos con los datos
            nomCla.setText(nombreClase);
            desCla.setText(descripcion);
            nomIns.setText(nombreInstructor);
            hora.setText(horaClase);
            LimCli.setText(LimCliM);

            hour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Calendar calendario = Calendar.getInstance();
                    int horaActual = calendario.get(Calendar.HOUR_OF_DAY);
                    int minutoActual = calendario.get(Calendar.MINUTE);

                    TimePickerDialog timePickerDialog = new TimePickerDialog(AdSModCla.this, R.style.MyTimePickerDialog, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            // Verifica si es AM o PM
                            String amPm;
                            if (hourOfDay < 12) {
                                amPm = "AM";
                            } else {
                                amPm = "PM";
                                if (hourOfDay > 12) {
                                    hourOfDay -= 12;
                                }
                            }
                            String horaSeleccionada = String.format(Locale.getDefault(), "%02d:%02d %s", hourOfDay, minute, amPm);
                            hora.setText(horaSeleccionada);
                        }
                    }, horaActual, minutoActual, false); // El último argumento es "false" para utilizar el formato de 12 horas

                    timePickerDialog.show();
                }
            });
            cancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i = new Intent(AdSModCla.this, AdPClases.class);
                    startActivity(i);
                }
            });
            actualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateClass();

                }
            });

        }
    }
    private void updateClass() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference clasRef = db.collection("clases").document(classId);

        // Actualizar el campo de nombre y correo en Firestore
        clasRef.update("nombreClase", nomCla.getText().toString(), "descripcion", desCla.getText().toString(),
                        "nombreInstructor", nomIns.getText().toString(), "limCli", LimCli.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AdSModCla.this, "Asi es, soy la mera verga", Toast.LENGTH_SHORT).show();
                        i = new Intent(AdSModCla.this, AdPClases.class);
                        startActivity(i);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AdSModCla.this, "Que pendejo", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}