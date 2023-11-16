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
import com.example.fitnes_hanma.Admin.Principal.AdPInstructor;
import com.example.fitnes_hanma.Objetos.Administrador;
import com.example.fitnes_hanma.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdSModIns extends AppCompatActivity {
    EditText nombre, email;
    Button cancelar, guardar;
    SwitchCompat admin;
    Intent i;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_ad_s_mod_ins);

        nombre = findViewById(R.id.tnameModIns);
        email = findViewById(R.id.temailModIns);
        admin = findViewById(R.id.adminModIns);
        cancelar = findViewById(R.id.cancelModIns);
        guardar = findViewById(R.id.saveModIns);

        Intent intent = getIntent();
        if (intent != null) {
            String tname = intent.getStringExtra("tname");
            String temail = intent.getStringExtra("temail");
            userId = intent.getStringExtra("tid");

            // Configura los campos con los datos
            nombre.setText(tname);
            email.setText(temail);

            // Configura el estado inicial del SwitchCompat según el rol del usuario
            admin.setChecked(false);

            // Agregar listener para el SwitchCompat "admin"
            admin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        // Si admin se activa, desactiva trainer
                        admin.setChecked(false);
                    }
                }
            });

            guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Actualizar el rol y los datos solo cuando se presiona el botón "Guardar"
                    updateDataAndRoleInFirestore();
                    i = new Intent(AdSModIns.this, AdPInstructor.class);
                    startActivity(i);
                }
            });

            cancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    i = new Intent(AdSModIns.this, AdPInstructor.class);
                    startActivity(i);
                }
            });
        }
    }

    private void updateDataAndRoleInFirestore() {
        // Obtener una referencia al documento del usuario en Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference trainerRef = db.collection("trainer").document(userId);

        // Actualizar el campo de nombre y correo en Firestore
        trainerRef.update("tname", nombre.getText().toString(), "temail", email.getText().toString())
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

        // Obtener el estado actual del Switch "admin"
        boolean isAdminChecked = admin.isChecked();

        // Actualizar el campo de rol según el estado del Switch "admin"
        if (isAdminChecked) {
            // Si "admin" está activado, cambia el rol a 5
            trainerRef.update("trole", "5")
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
            // Si ninguno está activado, cambia el rol a 3 (ya que el rol por defecto es 3)
            trainerRef.update("trole", "3")
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

    private void moveUserToAdminCollection() {
        // Obtener una referencia al documento del usuario en Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference trainerRef = db.collection("trainer").document(userId);

        // Obtener los datos del usuario
        trainerRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        // Verificar si el documento existe
                        if (documentSnapshot.exists()) {
                            // Obtener los datos del usuario
                            String tuserEmail = documentSnapshot.getString("temail");
                            String tuserName = documentSnapshot.getString("tname");
                            String tuserPassword = documentSnapshot.getString("tpassword");

                            // Crear una referencia para el nuevo documento en la colección "admin"
                            DocumentReference adminRef = db.collection("admin").document(userId);

                            // Guardar los datos en la colección "admin"
                            adminRef.set(new Administrador(tuserEmail, userId, tuserName, tuserPassword, "5"))
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            // Lógica si la transferencia de datos es exitosa
                                            deleteUserFromTrainerCollection();
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

    private void deleteUserFromTrainerCollection() {
        // Obtener una referencia al documento del usuario en Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference trainerRef = db.collection("trainer").document(userId);

        // Eliminar el usuario de la colección "trainer"
        trainerRef.delete()
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
