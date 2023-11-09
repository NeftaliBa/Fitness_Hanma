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

import com.example.fitnes_hanma.Admin.Principal.AdPClases;
import com.example.fitnes_hanma.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AdSCreCla extends AppCompatActivity {
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    EditText nomCla, desCla, nomIns, registros;
    Button calendar, cancelar, guardar, hour;
    TextView fecha, hora;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AdminStatusBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_ad_scre_cla);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        cancelar = (Button) findViewById(R.id.cancel);
        calendar = (Button) findViewById(R.id.calendar);
        hour = (Button) findViewById(R.id.hour);
        guardar = (Button) findViewById(R.id.save);
        nomCla = (EditText) findViewById(R.id.claNa);
        desCla = (EditText) findViewById(R.id.desCla);
        nomIns = (EditText) findViewById(R.id.naInst);
        registros = (EditText) findViewById(R.id.registros);
        fecha = (TextView) findViewById(R.id.fecha);
        hora = (TextView) findViewById(R.id.hora);

        final int[] dia = new int[1];
        final int[] mes = new int[1];
        final int[] año = new int[1];

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendario = Calendar.getInstance();
                dia[0] = calendario.get(Calendar.DAY_OF_MONTH);
                mes[0] = calendario.get(Calendar.MONTH);
                año[0] = calendario.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog =new DatePickerDialog(AdSCreCla.this, R.style.MyTimePickerDialog,  new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String diaF, mesF;
                        if (dayOfMonth<10){
                            diaF = 0+String.valueOf(dayOfMonth);
                        }else {
                            diaF = String.valueOf(dayOfMonth);
                        }
                        int Mes = month + 1;
                        if (month<10){
                            mesF = 0+String.valueOf(month);
                        }else {
                            mesF = String.valueOf(month);
                        }
                        fecha.setText(diaF + "/" + mesF + "/" + year);
                    }
                }, año[0], mes[0], dia[0]);
                datePickerDialog.show();
            }
        });
        hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendario = Calendar.getInstance();
                int horaActual = calendario.get(Calendar.HOUR_OF_DAY);
                int minutoActual = calendario.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(AdSCreCla.this, R.style.MyTimePickerDialog, new TimePickerDialog.OnTimeSetListener() {
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
                i = new Intent(AdSCreCla.this, AdPClases.class);
                startActivity(i);
            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreClase = nomCla.getText().toString();
                String descripcion = desCla.getText().toString();
                String nombreInstructor = nomIns.getText().toString();
                String fechaClase = fecha.getText().toString();
                String horaClase = hora.getText().toString();
                String CliRegis =  registros.getText().toString();

                Map<String, Object> clase = new HashMap<>();
                clase.put("nombreClase", nombreClase);
                clase.put("descripcion", descripcion);
                clase.put("nombreInstructor", nombreInstructor);
                clase.put("fechaClase", fechaClase);
                clase.put("horaClase", horaClase);
                clase.put("CliRegis", CliRegis);

                // Obtén la referencia de la colección "clases" en Firestore
                // No especificamos un ID para que Firestore genere uno automáticamente
                db.collection("clases")
                        .add(clase)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                // Éxito al guardar los datos
                                Toast.makeText(AdSCreCla.this, "Datos guardados con éxito", Toast.LENGTH_SHORT).show();
                                i = new Intent(AdSCreCla.this, AdPClases.class);
                                startActivity(i);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Error al guardar los datos
                                Toast.makeText(AdSCreCla.this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });



        //fechaAct();
    }

     /*private void fechaAct(){
        String fechayhora = new SimpleDateFormat("dd-mm-yyyy/hh:mm:ss a",
                Locale.getDefault()).format(System.currentTimeMillis());
         fecha.setText(fechayhora);
     }
     */
}