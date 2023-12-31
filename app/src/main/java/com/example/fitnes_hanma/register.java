package com.example.fitnes_hanma;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.fitnes_hanma.Security;
import com.example.fitnes_hanma.Cliente.Configuracion;
import com.example.fitnes_hanma.Cliente.principal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class register extends AppCompatActivity {
    EditText nombre, correo, contrasena, confirmContra;
    LinearLayout btn_registro, iniciarSesion;
    Intent i;

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    //
    String name = "", email = "", password = "", confirmpassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_register);

        // Configurar el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configurar el botón de retroceso
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Al presionar el botón de retroceso, ir al activity principal
                Intent intent = new Intent(register.this, menuRL.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Configurar el título de la barra de herramientas
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Registrate");



        nombre = (EditText) findViewById(R.id.nombreR);
        correo = (EditText) findViewById(R.id.correoR);
        contrasena = (EditText) findViewById(R.id.contrasenaR);
        confirmContra = (EditText) findViewById(R.id.confirmContraR);
        btn_registro = (LinearLayout) findViewById(R.id.btn_registro);
        iniciarSesion = (LinearLayout) findViewById(R.id.iniciarSesion);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(register.this);
        progressDialog.setTitle("Espere porfavor");
        progressDialog.setCanceledOnTouchOutside(false);

        btn_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidarDatos();

            }
        });
        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(register.this, login.class));
            }
        });
    }

    private void ValidarDatos() {
        name = nombre.getText().toString().trim();
        email = correo.getText().toString().trim();
        password = contrasena.getText().toString().trim();
        confirmpassword = confirmContra.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Ingresa un nombre", Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Ingrese un correo válido", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Ingrese una contraseña", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(confirmpassword)) {
            Toast.makeText(this, "Confirme la contraseña", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(confirmpassword)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        } else {
            CrearCuenta();
        }
    }

    private void CrearCuenta() {
        progressDialog.setMessage("Creando cuenta...");
        progressDialog.show();

        try {
            // Hashear la contraseña antes de crear la cuenta
            final String hashedPassword = Security.encrypt(password);

            // Crear usuario en firebase
            firebaseAuth.createUserWithEmailAndPassword(email, hashedPassword)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            // Guardar información con la contraseña hasheada
                            GuardarInformacion(hashedPassword);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(register.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            progressDialog.dismiss();
            Toast.makeText(register.this, "Error al hashear la contraseña", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void GuardarInformacion(final String hashedPassword) {
        progressDialog.setMessage("Guardando información");
        progressDialog.dismiss();

        // Obtener la identificación de usuario actual
        String id = firebaseAuth.getUid();
        String role = "1";
        Map<String, Object> userData = new HashMap<>();
        userData.put("id", id);
        userData.put("email", email);
        userData.put("name", name);
        userData.put("password", hashedPassword);
        userData.put("role", role);

        // Obtener una referencia a la colección "user" en Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("user").document(id)  // Modificar la colección según tu estructura
                .set(userData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        Toast.makeText(register.this, "Cuenta creada con éxito", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(register.this, principal.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(register.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}