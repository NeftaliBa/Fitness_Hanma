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

import com.example.fitnes_hanma.Admin.Principal.AdPCliente;
import com.example.fitnes_hanma.Objetos.Administrador;
import com.example.fitnes_hanma.Objetos.Instructor;
import com.example.fitnes_hanma.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdSModCli extends AppCompatActivity {
    EditText nombre, email;
    Button cancelar, guardar;
    String userId;
    SwitchCompat trainer, admin;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_ad_s_mod_cli);
        nombre = (EditText) findViewById(R.id.Name);
        email = (EditText) findViewById(R.id.mail);
        trainer = (SwitchCompat) findViewById(R.id.trainer);
        admin = (SwitchCompat) findViewById(R.id.admin);
        cancelar = (Button) findViewById(R.id.cancel);
        guardar = (Button) findViewById(R.id.save);
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String correo = intent.getStringExtra("email");
            String SuserRole = intent.getStringExtra("role");
            userId = intent.getStringExtra("id");


            // Obtén una referencia a la colección "clases" en Firestore
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            CollectionReference clieRef = db.collection("user");

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
            guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Actualizar el rol y los datos solo cuando se presiona el botón "Guardar"
                    updateDataAndRoleInFirestore();
                    i = new Intent(AdSModCli.this, AdPCliente.class);
                    startActivity(i);
                }
            });

            cancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i = new Intent(AdSModCli.this, AdPCliente.class);
                    startActivity(i);
                }
            });
        }
    }

    private void updateDataAndRoleInFirestore() {
        // Obtener una referencia al documento del usuario en Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userRef = db.collection("user").document(userId);

        // Actualizar el campo de nombre y correo en Firestore
        userRef.update("name", nombre.getText().toString(), "email", email.getText().toString())
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
            userRef.update("role", "3")
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
            userRef.update("role", "5")
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
            userRef.update("role", "1")
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
        DocumentReference userRef = db.collection("user").document(userId);

        // Obtener los datos del usuario
        userRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        // Verificar si el documento existe
                        if (documentSnapshot.exists()) {
                            // Obtener los datos del usuario
                            String userEmail = documentSnapshot.getString("email");
                            String userName = documentSnapshot.getString("name");
                            String userPassword = documentSnapshot.getString("password");

                            // Crear una referencia para el nuevo documento en la colección "trainer"
                            DocumentReference trainerRef = db.collection("trainer").document(userId);

                            // Guardar los datos en la colección "trainer"
                            trainerRef.set(new Instructor(userEmail, userId, userName, userPassword, "3", "supuesta imagen"))
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
                            String userEmail = documentSnapshot.getString("email");
                            String userName = documentSnapshot.getString("name");
                            String userPassword = documentSnapshot.getString("password");

                            // Crear una referencia para el nuevo documento en la colección "admin"
                            DocumentReference adminRef = db.collection("admin").document(userId);

                            // Guardar los datos en la colección "admin"
                            adminRef.set(new Administrador(userEmail, userId, userName, userPassword, "5"))
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
        DocumentReference userRef = db.collection("user").document(userId);

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


