package com.example.fitnes_hanma.Admin.Secundarias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fitnes_hanma.Admin.Principal.AdPInstructor;
import com.example.fitnes_hanma.Objetos.Administrador;
import com.example.fitnes_hanma.Objetos.Usuarios;
import com.example.fitnes_hanma.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdSModIns extends AppCompatActivity {
    EditText nombre, email;
    Button cancelar, guardar;
    String userId;
    SwitchCompat trainer, admin;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_ad_s_mod_ins);
        // Configurar el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Configurar el título de la barra de herramientas
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Editar Instructor");


        nombre = (EditText) findViewById(R.id.tnameModIns);
        email =(EditText) findViewById(R.id.temailModIns);
        cancelar = (Button) findViewById(R.id.cancelModIns);
        guardar = (Button) findViewById(R.id.saveModIns);
        admin = (SwitchCompat) findViewById(R.id.adminModIns);
        trainer = (SwitchCompat) findViewById(R.id.trainerModIns);

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("tname");
            String correo = intent.getStringExtra("temail");
            String SuserRole = intent.getStringExtra("trole");
            userId = intent.getStringExtra("tid");

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            CollectionReference insRef = db.collection("trainer");

            // Configura los campos con los datos
            nombre.setText(name);
            email.setText(correo);

            int userRole = Integer.parseInt(SuserRole);

            // Configura el estado inicial de los SwitchCompat según el rol del usuario
            if (userRole == 3) {
                trainer.setChecked(true);
                admin.setChecked(false);
            } else if (userRole == 5) {
                trainer.setChecked(false);
                admin.setChecked(true);
            } else {
                trainer.setChecked(false);
                admin.setChecked(false);
            }

            // Agregar listener para el SwitchCompat "trainer"
            trainer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        // Si trainer se activa, desactiva admin
                        admin.setChecked(false);
                    }
                }
            });

            // Agregar listener para el SwitchCompat "admin"
            admin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        // Si admin se activa, desactiva trainer
                        trainer.setChecked(false);
                    }
                }
            });

            cancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i = new Intent(AdSModIns.this, AdPInstructor.class);
                    startActivity(i);
                }
            });
            guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateDataAndRoleInFirestore();
                    i = new Intent(AdSModIns.this, AdPInstructor.class);
                    startActivity(i);
                }
            });
        }
    }
    //Actualizar data y role en firestore
    private void updateDataAndRoleInFirestore() {
        // Obtener una referencia al documento del entrenador en Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference insRef = db.collection("trainer").document(userId);

        // Actualizar el campo de nombre y correo en Firestore
        insRef.update("tname", nombre.getText().toString(), "temail", email.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Lógica si la actualización de datos es exitosa
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Lógica si la actualización de datos falla
                    }
                });

        // Obtener el estado actual de los Switch "trainer" y "admin"
        boolean isTrainerChecked = trainer.isChecked();
        boolean isAdminChecked = admin.isChecked();

        // Actualizar el campo de rol según el estado de los Switch "trainer" y "admin"
        if (isTrainerChecked && !isAdminChecked) {
            // Si "trainer" está activado y "admin" no está activado, cambia el rol a 3
            insRef.update("role", "3")
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Lógica si la actualización de rol es exitosa
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Lógica si la actualización de rol falla
                        }
                    });
        } else if (isAdminChecked && !isTrainerChecked) {
            // Si "admin" está activado y "trainer" no está activado, cambia el rol a 5
            insRef.update("role", "5")
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Lógica si la actualización de rol es exitosa
                            moveTrainerToAdminCollection();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Lógica si la actualización de rol falla
                        }
                    });
        } else {
            // Si ninguno está activado, cambia el rol a 1
            insRef.update("role", "1")
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            MoveTrainerToUserCollection();
                            // Lógica si la actualización de rol es exitosa
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Lógica si la actualización de rol falla
                        }
                    });
        }
    }


    private void MoveTrainerToUserCollection(){
        FirebaseFirestore db  = FirebaseFirestore.getInstance();
        DocumentReference insRef = db.collection("trainer").document(userId);

        insRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    String trainerName = documentSnapshot.getString("tname");
                    String trainerEmail = documentSnapshot.getString("temail");
                    String trainerPass = documentSnapshot.getString("tpassword");

                    DocumentReference userRef = db.collection("user").document(userId);
                    userRef.set(new Usuarios(userId, trainerName, trainerEmail, trainerPass, "1"))
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    deleteTrainerFromTrainerCollection();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    //mete un toast o algo asi xD
                                }
                            });
                }
            }
        });
    }


    private void moveTrainerToAdminCollection() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference insRef = db.collection("trainer").document(userId);

        // Obtener los datos del usuario
        insRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String trainerEmail = documentSnapshot.getString("temail");
                            String trainerName = documentSnapshot.getString("tname");
                            String trainerPass = documentSnapshot.getString("tpassword");

                            DocumentReference adminRef = db.collection("admin").document(userId);
                            adminRef.set(new Administrador(trainerEmail, userId, trainerName, trainerPass, "5"))
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            deleteTrainerFromTrainerCollection();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                        }
                                    });
                        }
                    }
                });
    }

    private void deleteTrainerFromTrainerCollection() {
        // Obtener una referencia al documento del usuario en Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference insRef = db.collection("trainer").document(userId);

        insRef.delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Puedes mostrar un mensaje o realizar cualquier otra acción
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Lógica si la eliminación falla
                    }
                });
    }
}


