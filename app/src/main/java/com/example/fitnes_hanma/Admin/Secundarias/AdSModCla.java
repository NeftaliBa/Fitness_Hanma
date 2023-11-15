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

    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_ad_s_mod_cla);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        cancelar = (Button) findViewById(R.id.cancel);
        hour = (Button) findViewById(R.id.hour);
        actualizar = (Button) findViewById(R.id.save);
        nomCla = (EditText) findViewById(R.id.claNa);
        desCla = (EditText) findViewById(R.id.desCla);
        nomIns = (EditText) findViewById(R.id.naInst);
        LimCli = (EditText) findViewById(R.id.limCliM);
        hora = (TextView) findViewById(R.id.hora);


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

        Intent intent = getIntent();
        if (intent != null) {
            String nombreClase = intent.getStringExtra("nombreClase");
            String descripcion = intent.getStringExtra("descripcion");
            String nombreInstructor = intent.getStringExtra("nombreInstructor");
            String horaClase = intent.getStringExtra("horaClase");
            String LimCliM = intent.getStringExtra("limCli");

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

            actualizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Obtiene los nuevos valores de los campos
                    String nuevoNombreClase = nomCla.getText().toString();
                    String nuevaDescripcion = desCla.getText().toString();
                    String nuevoNombreInstructor = nomIns.getText().toString();
                    String nuevaHoraClase = hora.getText().toString();
                    String nlimCli =  LimCli.getText().toString();

                    // Obtén una referencia al documento del ID obtenido
                    DocumentReference claseRef = clasesRef.document(idDocumento);

                    // Crea un mapa con los nuevos valores
                    Map<String, Object> nuevosDatos = new HashMap<>();
                    nuevosDatos.put("nombreClase", nuevoNombreClase);
                    nuevosDatos.put("descripcion", nuevaDescripcion);
                    nuevosDatos.put("nombreInstructor", nuevoNombreInstructor);
                    nuevosDatos.put("horaClase", nuevaHoraClase);
                    nuevosDatos.put("limCli", nlimCli);

                    // Actualiza los datos en Firestore
                    claseRef.set(nuevosDatos)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // Éxito al actualizar los datos
                                    Toast.makeText(AdSModCla.this, "Datos actualizados con éxito", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Error al actualizar los datos
                                    Toast.makeText(AdSModCla.this, "Error al actualizar los datos", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            });
        }
    }
}