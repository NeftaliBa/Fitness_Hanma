package com.example.fitnes_hanma.Cliente;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.fitnes_hanma.R;
import com.example.fitnes_hanma.Instructor.contactanos;
import com.example.fitnes_hanma.Instructor.politica_privacidad;
import com.example.fitnes_hanma.Instructor.sobre_nosotros;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Configuracion extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    EditText cambiarNombreC;
    Button BtnCambiarNombreC, cambiarFotoC;
    TextView textViewNombreUsuarioC;
    DocumentReference ClienteDocRef;
    ListenerRegistration ClienteListener;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;
    ImageView imageViewProfileC, imageProfileC;

    LinearLayout IrAPoliticas, IrANosotros, IrAContactanos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_cla_configuraciones);

        // Configurar el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Configuración");

        // Configurar el botón de retroceso
        ImageView backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Al presionar el botón de retroceso, ir al activity principal
                Intent intent = new Intent(Configuracion.this, principal.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });


        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        IrAPoliticas = (LinearLayout) findViewById(R.id.politicas);
        IrANosotros = (LinearLayout) findViewById(R.id.nosotros);
        IrAContactanos = (LinearLayout) findViewById(R.id.contactanos);

        cambiarNombreC = findViewById(R.id.cambiarNombre);
        BtnCambiarNombreC = findViewById(R.id.BtnCambiarNombre);
        textViewNombreUsuarioC = findViewById(R.id.textViewNombreUsuario);
        cambiarFotoC = findViewById(R.id.cambiarFoto);
        imageViewProfileC = findViewById(R.id.imageViewProfile);
        imageProfileC = (ImageView) findViewById(R.id.imageProfile);

        IrAPoliticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para abrir la actividad "politica_privacidad.java"
                Intent intent = new Intent(Configuracion.this, politica_privacidad_cliente.class);
                startActivity(intent);
            }
        });

        IrANosotros.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Configuracion.this, sobre_nosotros_cliente.class);
                startActivity(intent);
            }
        });

        IrAContactanos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Configuracion.this, contactanos_cliente.class);
                startActivity(intent);
            }
        });




        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ClienteDocRef = db.collection("user").document(user.getUid());

        ClienteListener = ClienteDocRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    return;
                }

                if (documentSnapshot != null && documentSnapshot.exists()) {
                    String nombre = documentSnapshot.getString("name");
                    textViewNombreUsuarioC.setText(nombre);
                    cambiarNombreC.setText(nombre);
                }
            }
        });

        BtnCambiarNombreC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuevoNombre = cambiarNombreC.getText().toString().trim();
                if (!TextUtils.isEmpty(nuevoNombre)) {
                    ClienteDocRef.update("name", nuevoNombre)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    textViewNombreUsuarioC.setText(nuevoNombre);
                                    Toast.makeText(Configuracion.this, "Nombre actualizado correctamente", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Configuracion.this, "Error al actualizar el nombre", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(Configuracion.this, "Ingrese un nuevo nombre", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cambiarFotoC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        loadProfileImageFromFirestore();
    }

    private void loadProfileImageFromFirestore() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userRef = db.collection("user").document(user.getUid());
        userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot != null && documentSnapshot.exists()) {
                    String imageUrl = documentSnapshot.getString("profileImageUrl");
                    if (imageUrl != null && !imageUrl.isEmpty()) {
                        Glide.with(Configuracion.this)
                                .load(imageUrl)
                                .placeholder(R.drawable.default_image)
                                .into(imageViewProfileC);

                        Glide.with(Configuracion.this)
                                .load(imageUrl)
                                .placeholder(R.drawable.default_image)
                                .into(imageProfileC);
                    } else {
                        // Si la URL de la imagen es nula o vacía, carga una imagen por defecto
                        imageViewProfileC.setImageResource(R.drawable.default_image);
                        imageProfileC.setImageResource(R.drawable.default_image);
                    }
                } else {
                    // Si no hay datos o el documento no existe, carga una imagen por defecto
                    imageViewProfileC.setImageResource(R.drawable.default_image);
                    imageProfileC.setImageResource(R.drawable.default_image);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Manejo de fallos al obtener datos de Firestore
                Log.e("FirestoreLoad", "Error al cargar datos desde Firestore: " + e.getMessage());
            }
        });
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            uploadImageToFirebase();
        }
    }

    private void uploadImageToFirebase() {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference("profile_images/" + user.getUid() + "/profile.jpg");

        storageRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> downloadUrl = storageRef.getDownloadUrl();
                        downloadUrl.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String imageUrl = uri.toString();
                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                DocumentReference userRef = db.collection("user").document(user.getUid());
                                userRef.update("profileImageUrl", imageUrl)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                loadProfileImageFromFirestore(); // Recargar la imagen después de la actualización
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.e("FirestoreUpdate", "Error al actualizar imagen en Firestore: " + e.getMessage());
                                            }
                                        });
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("StorageUpload", "Error al subir imagen a Firebase Storage: " + e.getMessage());
                    }
                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (ClienteListener != null) {
            ClienteListener.remove();
        }
    }
}
