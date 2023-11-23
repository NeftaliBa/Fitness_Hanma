package com.example.fitnes_hanma.Admin.Secundarias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.fitnes_hanma.Admin.Principal.AdPInstructor;
import com.example.fitnes_hanma.Objetos.Instructor;
import com.example.fitnes_hanma.Objetos.InstructorAdapter;
import com.example.fitnes_hanma.Objetos.SpinnerColor.CustomSpinnerAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnFailureListener;

import com.example.fitnes_hanma.Admin.Principal.AdPClases;
import com.example.fitnes_hanma.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AdSModCla extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    FirebaseAuth firebaseAuth;

    String[] Semana = {"Seleccionar dia", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};

    EditText nomCla, desCla, emaIns, LimCli;
    Button cancelar, actualizar, eliminar, hour1, hour2, hour3;
    Spinner dia1, dia2, dia3;
    String horario1, horario2, horario3;
    TextView nameInstructor;
    String classId;

    Intent i;

    String nameC = "", desC = "", emailI = "", limU = "", hor1 = "", hor2 = "", hor3 = "", nameI= "", idIns = "", idInstructor = "";
    int limlim = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_ad_s_mod_cla);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        nomCla = (EditText) findViewById(R.id.claNa);
        desCla = (EditText) findViewById(R.id.desCla);
        emaIns = (EditText) findViewById(R.id.naInst);
        LimCli = (EditText) findViewById(R.id.limCliM);
        cancelar = (Button) findViewById(R.id.cancelModCla);
        actualizar = (Button) findViewById(R.id.save);
        eliminar = (Button) findViewById(R.id.eliminarClase);
        hour1 = (Button) findViewById(R.id.hour1M);
        hour2 = (Button) findViewById(R.id.hour2M);
        hour3 = (Button) findViewById(R.id.hour3M);

        nameInstructor = (TextView) findViewById(R.id.nameInstructor);


        dia1 = (Spinner) findViewById(R.id.day1M);
        dia2 = (Spinner) findViewById(R.id.day2M);
        dia3 = (Spinner) findViewById(R.id.day3M);

        CustomSpinnerAdapter aa = new CustomSpinnerAdapter(
                AdSModCla.this, android.R.layout.simple_dropdown_item_1line, Arrays.asList(Semana));

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


        Intent intent = getIntent();
        if (intent != null) {
            String nombreClase = intent.getStringExtra("nombreClase");
            String descripcion = intent.getStringExtra("descripcion");
            String correoInstructor = intent.getStringExtra("correoInstructor");
            String nombreInstructor = intent.getStringExtra("nombreInstructor");
            String iDInstructor = intent.getStringExtra("iDInstructor");
            String LimCliM = intent.getStringExtra("limCli");
            classId = intent.getStringExtra("id_clase");
            String hor1 = intent.getStringExtra("hor1");
            String hor2 = intent.getStringExtra("hor2");
            String hor3 = intent.getStringExtra("hor3");


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
            emaIns.setText(correoInstructor);
            nameInstructor.setText(nombreInstructor);
            LimCli.setText(LimCliM);
            idInstructor = iDInstructor;


            assert hor1 != null;
            if (hor1.equals("")) {
                // Tengo hambre
            } else {
                String[] H1partes = hor1.split(" ", 2);
                String di1 = H1partes[0];
                String ho1 = H1partes[1];
                dia1.setSelection(Arrays.asList(Semana).indexOf(di1));
                hour1.setText(ho1);
            }

            assert hor2 != null;
            if (hor2.equals("")) {
                // Mucha Hambre
            } else {
                String[] H2partes = hor2.split(" ", 2);
                String di2 = H2partes[0];
                String ho2 = H2partes[1];
                dia2.setSelection(Arrays.asList(Semana).indexOf(di2));
                hour2.setText(ho2);
            }

            assert hor3 != null;
            if (hor3.equals("")) {
                // Demasiada hambre
            } else {
                String[] H3partes = hor3.split(" ", 2);
                String di3 = H3partes[0];
                String ho3 = H3partes[1];
                dia3.setSelection(Arrays.asList(Semana).indexOf(di3));
                hour3.setText(ho3);
            }

// ...



            hour1.setOnClickListener(new View.OnClickListener() {
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
                            hour3.setText(horaSeleccionada);
                        }
                    }, horaActual, minutoActual, false); // El último argumento es "false" para utilizar el formato de 12 horas
                    timePickerDialog.show();
                }
            });
            // Agrega un TextWatcher al EditText para realizar la búsqueda al escribir
            emaIns.addTextChangedListener(new TextWatcher() {
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
                    emaIns.setText(instructorSeleccionado.getTemail());
                    nameInstructor.setText(instructorSeleccionado.getTname());
                    idInstructor = instructorSeleccionado.getTid();
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
                    horario1 = obtenerHorario(dia1, hour1);
                    horario2 = obtenerHorario(dia2, hour2);
                    horario3 = obtenerHorario(dia3, hour3);

                    if (validarCondiciones()) {
                        ValidarDatos();
                    } else {
                        // Muestra un mensaje o realiza acciones adicionales si la validación falla
                        Toast.makeText(AdSModCla.this, "No se cumplen las condiciones para crear la clase", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Llama al método para eliminar la clase
                    eliminarClase();
                }
            });

        }
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
        emailI = emaIns.getText().toString().trim();
        nameI = nameInstructor.getText().toString().trim();
        limU = LimCli.getText().toString().trim();
        idIns = idInstructor;
        if(horario1.equals("Seleccionar dia Seleccionar hora")){
            hor1 = "";
        }else {
            hor1 = horario1;
        }
        if(horario2.equals("Seleccionar dia Seleccionar hora")){
            hor2 = "";
        }else {
            hor2 = horario2;
        }
        if(horario3.equals("Seleccionar dia Seleccionar hora")){
            hor3 = "";
        }else {
            hor3 = horario3;
        }
        if (TextUtils.isEmpty(limU)) {
            Toast.makeText(this, "Ingrese el límite de clientes", Toast.LENGTH_SHORT).show();
        }else {
            limlim = Integer.parseInt(limU);
        }
        if (TextUtils.isEmpty(nameC)) {
            Toast.makeText(this, "Ingresa un nombre para la clase", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(desC)) {
            Toast.makeText(this, "Ingrese una descripción", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(emailI)) {
            Toast.makeText(this, "Ingrese el nombre del instructor", Toast.LENGTH_SHORT).show();
        } else if (limlim >45){
            Toast.makeText(this, "Ingrese un numero menor a 45", Toast.LENGTH_SHORT).show();
        }else {
            limU = Integer.toString(limlim);
            updateClass();
        }
    }


    private boolean validarCondiciones() {
        // Obtén valores de días y horas
        String dia1 = ((Spinner) findViewById(R.id.day1M)).getSelectedItem().toString();
        String dia2 = ((Spinner) findViewById(R.id.day2M)).getSelectedItem().toString();
        String dia3 = ((Spinner) findViewById(R.id.day3M)).getSelectedItem().toString();
        String hour1 = ((Button) findViewById(R.id.hour1M)).getText().toString();
        String hour2 = ((Button) findViewById(R.id.hour2M)).getText().toString();
        String hour3 = ((Button) findViewById(R.id.hour3M)).getText().toString();

        // Verificar las condiciones
        boolean condicion1 = diaEsValido(dia1) && horaEsValida(hour1) && !diaEsValido(dia2) && !horaEsValida(hour2) && !diaEsValido(dia3) && !horaEsValida(hour3);
        boolean condicion2 = !diaEsValido(dia1) && !horaEsValida(hour1) && diaEsValido(dia2) && horaEsValida(hour2) && !diaEsValido(dia3) && !horaEsValida(hour3);
        boolean condicion3 = !diaEsValido(dia1) && !horaEsValida(hour1) && !diaEsValido(dia2) && !horaEsValida(hour2) && diaEsValido(dia3) && horaEsValida(hour3);
        boolean condicion4 = diaEsValido(dia1) && horaEsValida(hour1) && diaEsValido(dia2) && horaEsValida(hour2) && !diaEsValido(dia3) && !horaEsValida(hour3);
        boolean condicion5 = !diaEsValido(dia1) && !horaEsValida(hour1) && diaEsValido(dia2) && horaEsValida(hour2) && diaEsValido(dia3) && horaEsValida(hour3);
        boolean condicion6 = diaEsValido(dia1) && horaEsValida(hour1) && !diaEsValido(dia2) && !horaEsValida(hour2) && diaEsValido(dia3) && horaEsValida(hour3);
        boolean condicion7 = diaEsValido(dia1) && horaEsValida(hour1) && diaEsValido(dia2) && horaEsValida(hour2) && diaEsValido(dia3) && horaEsValida(hour3);

        // Comprobar si alguna de las condiciones se cumple
        return condicion1 || condicion2 || condicion3 || condicion4 || condicion5 || condicion6 || condicion7;
    }

    private boolean diaEsValido(String dia) {
        return !dia.equals("Seleccionar dia");
    }

    private boolean horaEsValida(String hora) {
        return !hora.equals("Seleccionar hora");
    }

    private void updateClass() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference clasRef = db.collection("clases").document(classId);

        // Actualizar el campo de nombre y correo en Firestore
        clasRef.update("nombreClase", nameC,
                        "descripcion", desC,
                        "correoInstructor", emailI,
                        "nombreInstructor", nameI,
                        "limCli", limU,
                        "iDInstructor", idIns,
                        "hor1", hor1,
                        "hor2", hor2,
                        "hor3", hor3)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AdSModCla.this, "Clase actualizada exitosamente", Toast.LENGTH_SHORT).show();
                        i = new Intent(AdSModCla.this, AdPClases.class);
                        startActivity(i);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AdSModCla.this, "Error al actualizar la clase", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void eliminarClase() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference clasRef = db.collection("clases").document(classId);

        // Elimina el documento de la colección "clases" en Firestore
        clasRef.delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AdSModCla.this, "Clase eliminada exitosamente", Toast.LENGTH_SHORT).show();
                        // Redirige a la actividad principal de clases después de eliminar la clase
                        Intent intent = new Intent(AdSModCla.this, AdPClases.class);
                        startActivity(intent);
                        finish(); // Finaliza la actividad actual para que no pueda volver atrás
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AdSModCla.this, "Error al eliminar la clase", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}