package com.example.fitnes_hanma.Admin.Secundarias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AdSCreCla extends AppCompatActivity implements /*View.OnClickListener,*/ AdapterView.OnItemSelectedListener {

    private FirebaseFirestore db;
    private FirebaseAuth auth;
    EditText nomCla, desCla, nomIns, LimCli;
    Button newH, cancelar, guardar, hour;
    TextView fecha, hora;
    Intent i;
    String[] Semana = {"Seleccionar dia", "Lunes", "Martes", "Miercoles", "Jueves", "Sabado", "Domingo"};
    Spinner semsem;
    LinearLayout horarioContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_ad_scre_cla);

        //ArrayAdapter<String> aa = new ArrayAdapter<String>(
        //        AdSCreCla.this, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, Semana);



        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        cancelar = (Button) findViewById(R.id.cancel);
        guardar = (Button) findViewById(R.id.save);
        nomCla = (EditText) findViewById(R.id.claNa);
        desCla = (EditText) findViewById(R.id.desCla);
        nomIns = (EditText) findViewById(R.id.naInst);
        LimCli = (EditText) findViewById(R.id.limCli);
        hour = (Button) findViewById(R.id.hourButton);

        //newH = (Button) findViewById(R.id.newH);
        //horarioContainer = findViewById(R.id.horarioContainer);

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
                String id_clase = auth.getCurrentUser().getUid();
                String nombreClase = nomCla.getText().toString();
                String descripcion = desCla.getText().toString();
                String nombreInstructor = nomIns.getText().toString();
                //String horaClase = hora.getText().toString();
                String limCli = LimCli.getText().toString();

                Map<String, Object> clase = new HashMap<>();
                clase.put("nombreClase", nombreClase);
                clase.put("descripcion", descripcion);
                clase.put("nombreInstructor", nombreInstructor);
                //clase.put("horaClase", horaClase);
                clase.put("limCli", limCli);
                clase.put("id_clase", id_clase);

                // Obtén la referencia de la colección "clases" en Firestore
                // por ahora no quiero especificar un ID ya que Firestore genera uno automáticamente
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
                        hour.setText(horaSeleccionada);
                    }
                }, horaActual, minutoActual, false); // El último argumento es "false" para utilizar el formato de 12 horas
                timePickerDialog.show();
            }
        });
        //newH.setOnClickListener(this);

    }
    //@Override
    //public void onClick(View v) {
    //    if (v.getId() == R.id.newH) {
    //        // Inflar el diseño del LinearLayout
    //        LayoutInflater inflater = getLayoutInflater();
    //        View horarioView = inflater.inflate(R.layout.a_ad_agregar_horarios_a_adscrecla, null);
//
    //        // Agregar el LinearLayout al contenedor
    //        horarioContainer.addView(horarioView);
//
    //        // Buscar y encontrar los elementos dentro del diseño del horario
    //        semsem = horarioView.findViewById(R.id.day);
    //        hour = horarioView.findViewById(R.id.hour);
//
    //        // Resto de tu código para configurar el Spinner y el Button
    //        ArrayAdapter<String> aa = new ArrayAdapter<String>(
    //                AdSCreCla.this, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, Semana);
//
    //        semsem.setAdapter(aa);
    //        semsem.setOnItemSelectedListener(this);
//
    //        hour.setOnClickListener(new View.OnClickListener() {
    //            @Override
    //            public void onClick(View v) {
    //                final Calendar calendario = Calendar.getInstance();
    //                int horaActual = calendario.get(Calendar.HOUR_OF_DAY);
    //                int minutoActual = calendario.get(Calendar.MINUTE);
    //                TimePickerDialog timePickerDialog = new TimePickerDialog(AdSCreCla.this, R.style.MyTimePickerDialog, new TimePickerDialog.OnTimeSetListener() {
    //                    @Override
    //                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
    //                        // Verifica si es AM o PM
    //                        String amPm;
    //                        if (hourOfDay < 12) {
    //                            amPm = "AM";
    //                        } else {
    //                            amPm = "PM";
    //                            if (hourOfDay > 12) {
    //                                hourOfDay -= 12;
    //                            }
    //                        }
    //                        String horaSeleccionada = String.format(Locale.getDefault(), "%02d:%02d %s", hourOfDay, minute, amPm);
    //                        hour.setText(horaSeleccionada);
    //                    }
    //                }, horaActual, minutoActual, false); // El último argumento es "false" para utilizar el formato de 12 horas
    //                timePickerDialog.show();
    //            }
    //        });
    //    }
    //}



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.day) {
            // Tu código para el Spinner con id "day"
            // Puedes acceder al id del Spinner seleccionado usando parent.getId()
            // Ejemplo: int spinnerId = parent.getId();
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}