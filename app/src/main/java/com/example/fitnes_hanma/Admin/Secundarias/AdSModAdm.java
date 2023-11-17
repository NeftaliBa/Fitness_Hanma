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
import com.example.fitnes_hanma.Objetos.Administrador;
import com.example.fitnes_hanma.Objetos.Instructor;
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
    String userId; // Move the userId declaration here

    SwitchCompat trainer, admin;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_ad_s_mod_adm);
        nombre = (EditText) findViewById(R.id.anameModAdm);
        email = (EditText) findViewById(R.id.aemailModAdm);
        trainer = (SwitchCompat) findViewById(R.id.trainerModAdm);
        admin = (SwitchCompat) findViewById(R.id.adminModAdm);
        cancelar = (Button) findViewById(R.id.cancelModAdm);
        guardar = (Button) findViewById(R.id.saveModAdm);
        Intent intent = getIntent();
        if (intent != null) {
            String aname = intent.getStringExtra("aname");
            String aemail = intent.getStringExtra("aemail");
            String SuserRole = intent.getStringExtra("arole");
            userId = intent.getStringExtra("aid");


            // Obtén una referencia a la colección "clases" en Firestore
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            CollectionReference clieRef = db.collection("admin");

            // Configura los campos con los datos
            nombre.setText(aname);
            email.setText(aemail);

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
            guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Actualizar el rol y los datos solo cuando se presiona el botón "Guardar"
                    updateDataAndRoleInFirestore();
                    i = new Intent(AdSModAdm.this, AdPAdmin.class);
                    startActivity(i);
                }
            });

            cancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i = new Intent(AdSModAdm.this, AdPAdmin.class);
                    startActivity(i);
                }
            });
        }
    }

    private void updateDataAndRoleInFirestore() {
        // Obtener una referencia al documento del usuario en Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userRef = db.collection("admin").document(userId);

        // Actualizar el campo de nombre y correo en Firestore
        userRef.update("aname", nombre.getText().toString(), "aemail", email.getText().toString())
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
            userRef.update("arole", "3")
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Lógica si la actualización de rol es exitosa
                            moveUserToTrainerCollection();
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
            userRef.update("arole", "5")
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Lógica si la actualización de rol es exitosa
                            moveUserToAdminCollection();
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
            userRef.update("arole", "1")
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
        }
    }

    private void moveUserToTrainerCollection() {
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
                            String adminPassword = documentSnapshot.getString("apassword");

                            // Crear una referencia para el nuevo documento en la colección "trainer"
                            DocumentReference trainerRef = db.collection("trainer").document(userId);

                            // Guardar los datos en la colección "trainer"
                            trainerRef.set(new Instructor(adminEmail, userId, adminName, adminPassword, "3"))
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            // Lógica si la transferencia de datos es exitosa
                                            deleteUserFromUserCollection();
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

    private void moveUserToAdminCollection() {
        // Obtener una referencia al documento del usuario en Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userRef = db.collection("user").document(userId);

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
                            String adminPassword = documentSnapshot.getString("apassword");

                            // Crear una referencia para el nuevo documento en la colección "user"
                            DocumentReference adminRef = db.collection("user").document(userId);

                            // Guardar los datos en la colección "admin"
                            adminRef.set(new Administrador(adminEmail, userId, adminName, adminPassword, "1"))
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            // Lógica si la transferencia de datos es exitosa
                                            deleteUserFromUserCollection();
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

    private void deleteUserFromUserCollection() {
        // Obtener una referencia al documento del usuario en Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userRef = db.collection("admin").document(userId);

        // Eliminar el usuario de la colección "user"
        userRef.delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Lógica si la eliminación es exitosa
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

