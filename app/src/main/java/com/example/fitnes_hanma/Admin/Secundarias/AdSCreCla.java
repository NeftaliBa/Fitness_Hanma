package com.example.fitnes_hanma.Admin.Secundarias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;

import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.fitnes_hanma.Admin.Principal.AdPClases;
import com.example.fitnes_hanma.Objetos.Instructor;
import com.example.fitnes_hanma.Objetos.InstructorAdapter;
import com.example.fitnes_hanma.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.example.fitnes_hanma.Objetos.SpinnerColor.CustomSpinnerAdapter;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.DocumentSnapshot;

public class AdSCreCla extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {

    private FirebaseFirestore db;
    private FirebaseAuth auth;
    EditText nomCla, desCla, LimCli, searchInstructor;
    Button cancelar, guardar, hour1, hour2, hour3;
    Intent i;
    String[] Semana = {"Seleccionar dia", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
    Spinner dia1, dia2, dia3;
    FirebaseAuth firebaseAuth;
    String horario1, horario2, horario3;
    String nameC = "", desC = "", emailI = "", limU = "", hor1 = "", hor2 = "", hor3 = "", nameI= "", idIns = "", idInstructor;
    TextView nameInstructor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_ad_s_cre_cla);


        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        cancelar = (Button) findViewById(R.id.cancelCreCla);
        guardar = (Button) findViewById(R.id.save);
        nomCla = (EditText) findViewById(R.id.claNa);
        desCla = (EditText) findViewById(R.id.desCla);
        searchInstructor = (EditText) findViewById(R.id.searchInsCreCla);
        nameInstructor = (TextView) findViewById(R.id.nameInstructor);
        LimCli = (EditText) findViewById(R.id.limCli);

        hour1 = (Button) findViewById(R.id.hour1);
        hour2 = (Button) findViewById(R.id.hour2);
        hour3 = (Button) findViewById(R.id.hour3);


        dia1 = (Spinner) findViewById(R.id.day1);
        dia2 = (Spinner) findViewById(R.id.day2);
        dia3 = (Spinner) findViewById(R.id.day3);

        CustomSpinnerAdapter aa = new CustomSpinnerAdapter(
                AdSCreCla.this, android.R.layout.simple_dropdown_item_1line, Arrays.asList(Semana));

        dia1.setAdapter(aa);
        dia1.setOnItemSelectedListener(this);
        dia2.setAdapter(aa);
        dia2.setOnItemSelectedListener(this);
        dia3.setAdapter(aa);
        dia3.setOnItemSelectedListener(this);

        ListView listViewInstructor = findViewById(R.id.listViewInstructorinCreCla);
        List<Instructor> instructorList = new ArrayList<>();
        InstructorAdapter instructorAdapter = new InstructorAdapter(this, instructorList);
        listViewInstructor.setAdapter(instructorAdapter);

        hour1.setOnClickListener(new View.OnClickListener() {
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
                        hour1.setText(horaSeleccionada);
                    }
                }, horaActual, minutoActual, false); // El último argumento es "false" para utilizar el formato de 12 horas
                timePickerDialog.show();
            }
        });
        hour2.setOnClickListener(new View.OnClickListener() {
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
                        hour2.setText(horaSeleccionada);
                    }
                }, horaActual, minutoActual, false); // El último argumento es "false" para utilizar el formato de 12 horas
                timePickerDialog.show();
            }
        });
        hour3.setOnClickListener(new View.OnClickListener() {
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
                        hour3.setText(horaSeleccionada);
                    }
                }, horaActual, minutoActual, false); // El último argumento es "false" para utilizar el formato de 12 horas
                timePickerDialog.show();
            }
        });
        // Agrega un TextWatcher al EditText para realizar la búsqueda al escribir
        searchInstructor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No es necesario implementar aquí
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Realiza la búsqueda al cambiar el texto
                buscarInstructores(charSequence.toString(), instructorAdapter, instructorList);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No es necesario implementar aquí
            }
        });
        listViewInstructor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtén el instructor seleccionado
                Instructor instructorSeleccionado = instructorList.get(position);

                // Establece el correo del instructor seleccionado en el EditText
                searchInstructor.setText(instructorSeleccionado.getTemail());
                nameInstructor.setText(instructorSeleccionado.getTname());
                idInstructor = instructorSeleccionado.getTid();
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
                horario1 = obtenerHorario(dia1, hour1);
                horario2 = obtenerHorario(dia2, hour2);
                horario3 = obtenerHorario(dia3, hour3);

                ValidarDatos();

            }
        });





    }
    private String obtenerHorario(Spinner spinner, Button button) {
        String selectedDay = spinner.getSelectedItem().toString();
        String selectedTime = button.getText().toString();
        return selectedDay + " " + selectedTime;
    }

    private void buscarInstructores(String searchText, InstructorAdapter adapter, List<Instructor> instructorList) {
        // Realiza la búsqueda en tu colección de instructores, similar a AdPInstructor
        // Utiliza el método filtrarInstructores de AdPInstructor
        // (Asegúrate de adaptar el código según la estructura de tu colección "trainer")

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference instructorRef = db.collection("trainer");

        // Filtra los instructores por el campo 'temail'
        Query query = instructorRef.whereGreaterThanOrEqualTo("temail", searchText)
                .whereLessThanOrEqualTo("temail", searchText + "\uf8ff");

        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                try {
                    Log.d("AdSCreCla", "Número de documentos recuperados: " + queryDocumentSnapshots.size());
                    instructorList.clear();

                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Instructor instructor = documentSnapshot.toObject(Instructor.class);
                        if (instructor != null) {
                            Log.d("AdSCreCla", "Instructor recuperado: " + instructor.getTname());
                            instructorList.add(instructor);
                        }
                    }
                    // Notifica al adaptador que los datos han cambiado
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    Log.e("AdSCreCla", "Error al procesar documentos", e);
                }
            }
        });
    }



    private void ValidarDatos() {
        nameC = nomCla.getText().toString().trim();
        desC = desCla.getText().toString().trim();
        emailI = searchInstructor.getText().toString().trim();
        nameI = nameInstructor.getText().toString().trim();
        limU = LimCli.getText().toString().trim();
        idIns = idInstructor;
        hor1 = horario1;
        hor2 = horario2;
        hor3 = horario3;

        // Verifica si los campos obligatorios están vacíos
        if (TextUtils.isEmpty(nameC)) {
            Toast.makeText(this, "Ingresa un nombre para la clase", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(desC)) {
            Toast.makeText(this, "Ingrese una descripción", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(emailI)) {
            Toast.makeText(this, "Ingrese el nombre del instructor", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(limU)) {
            Toast.makeText(this, "Ingrese el límite de clientes", Toast.LENGTH_SHORT).show();
        } else if (esSeleccionInvalida(dia1) || esSeleccionInvalida(dia2) || esSeleccionInvalida(dia3) ||
                esSeleccionInvalida(hour1) || esSeleccionInvalida(hour2) || esSeleccionInvalida(hour3)) {
            // Muestra un mensaje si algún Spinner o botón de hora tiene una selección inválida
            Toast.makeText(this, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            CrearClase();
        }
    }

    // Método para verificar si la selección es "Seleccionar día" o "Seleccionar hora"
    private boolean esSeleccionInvalida(Spinner spinner) {
        return spinner.getSelectedItem().toString().equals("Seleccionar día");
    }

    private boolean esSeleccionInvalida(Button button) {
        return button.getText().toString().equals("Seleccionar hora");
    }



    private void CrearClase() {

        String id_clase = UUID.randomUUID().toString(); // Utilizando UUID para obtener un ID único

        Map<String, Object> clase = new HashMap<>();
        clase.put("id_clase", id_clase);
        clase.put("nombreClase", nameC);
        clase.put("descripcion", desC);
        clase.put("correoInstructor", emailI);
        clase.put("nombreInstructor", nameI);
        clase.put("limCli", limU);
        clase.put("iDInstructor", idIns);
        clase.put("hor1", hor1);
        clase.put("hor2", hor2);
        clase.put("hor3", hor3);


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("clases").document(id_clase)
                .set(clase)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AdSCreCla.this, "Clase creada con éxito", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AdSCreCla.this, AdPClases.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AdSCreCla.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.day1) {
            String selectedDay = parent.getItemAtPosition(position).toString();
            String selectedTime = hour1.getText().toString();
            horario1 = selectedDay + " " + selectedTime;
        } else if (parent.getId() == R.id.day2) {
            String selectedDay = parent.getItemAtPosition(position).toString();
            String selectedTime = hour2.getText().toString();
            horario2 = selectedDay + " " + selectedTime;
        } else if (parent.getId() == R.id.day3) {
            String selectedDay = parent.getItemAtPosition(position).toString();
            String selectedTime = hour3.getText().toString();
            horario3 = selectedDay + " " + selectedTime;
        }
    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}