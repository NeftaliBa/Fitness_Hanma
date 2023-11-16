package com.example.fitnes_hanma.Admin.Secundarias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.fitnes_hanma.Admin.Principal.AdPAdmin;
import com.example.fitnes_hanma.Admin.Principal.AdPInstructor;
import com.example.fitnes_hanma.Objetos.Administrador;
import com.example.fitnes_hanma.Objetos.Instructor;
import com.example.fitnes_hanma.Objetos.Usuarios;
import com.example.fitnes_hanma.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdSModAdm extends AppCompatActivity {
    EditText nombre, email;
    Button cancelar, guardar;
    String userId;
    SwitchCompat trainer, admin;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_ad_s_mod_adm);
        nombre = (EditText) findViewById(R.id.anameModAdm);
        email =(EditText) findViewById(R.id.aemailModAdm);
        admin = (SwitchCompat) findViewById(R.id.adminModAdm);
        trainer = (SwitchCompat) findViewById(R.id.trainerModAdm);
        cancelar = (Button) findViewById(R.id.cancelModAdm);
        guardar = (Button) findViewById(R.id.saveModAdm);

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("aname");
            String correo = intent.getStringExtra("aemail");
            String SuserRole = intent.getStringExtra("arole");
            userId = intent.getStringExtra("aid");

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            CollectionReference insRef = db.collection("trainer");

            nombre.setText(name);
            email.setText(correo);

            int userRole = Integer.parseInt(SuserRole);

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

            trainer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        // Si trainer se activa, desactiva admin
                        admin.setChecked(false);
                    }
                }
            });
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
                    i = new Intent(AdSModAdm.this, AdPInstructor.class);
                    startActivity(i);
                }
            });
            guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateDataAndRoleInFirestore();
                    i = new Intent(AdSModAdm.this, AdPInstructor.class);
                    startActivity(i);
                }
            });
        }
    }

    private void updateDataAndRoleInFirestore() {
        // Obtener una referencia al documento del entrenador en Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference insRef = db.collection("admin").document(userId);

        // Actualizar el campo de nombre y correo en Firestore
        insRef.update("aname", nombre.getText().toString(), "aemail", email.getText().toString())
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
                            moveAdminToTrainerCollection();                        }
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
                            MoveAdminToUserCollection();
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

    private void moveAdminToTrainerCollection() {
        // Obtener una referencia al documento del usuario en Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userRef = db.collection("admin").document(userId);

        // Obtener los datos del usuario
        userRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        // Verificar si el documento existe
                        if (documentSnapshot.exists()) {
                            // Obtener los datos del usuario
                            String adminEmail = documentSnapshot.getString("aemail");
                            String adminName = documentSnapshot.getString("aname");
                            String adminPass = documentSnapshot.getString("apassword");

                            // Crear una referencia para el nuevo documento en la colección "trainer"
                            DocumentReference trainerRef = db.collection("trainer").document(userId);

                            // Guardar los datos en la colección "trainer"
                            trainerRef.set(new Instructor(adminEmail, userId, adminName, adminPass, "3"))
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            // Lógica si la transferencia de datos es exitosa
                                            deleteAdminFromAdminCollection();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            // Lógica si la transferencia de datos falla
                                        }
                                    });
                        }
                    }
                });
    }

    private void MoveAdminToUserCollection(){
        FirebaseFirestore db  = FirebaseFirestore.getInstance();
        DocumentReference insRef = db.collection("admin").document(userId);

        insRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    String adminEmail = documentSnapshot.getString("aemail");
                    String adminName = documentSnapshot.getString("aname");
                    String adminPass = documentSnapshot.getString("apassword");

                    DocumentReference userRef = db.collection("user").document(userId);
                    userRef.set(new Usuarios(userId, adminName, adminEmail, adminPass, "1"))

                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    deleteAdminFromAdminCollection();
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

    private void deleteAdminFromAdminCollection() {
        // Obtener una referencia al documento del usuario en Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference insRef = db.collection("admin").document(userId);

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


