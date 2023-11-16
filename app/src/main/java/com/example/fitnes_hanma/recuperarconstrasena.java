package com.example.fitnes_hanma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class recuperarconstrasena extends AppCompatActivity {

    private EditText mEditTextEmail;
    private Button mButtonResetPassword;

    private String email = "";

    private FirebaseAuth mAuth;

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_forgetpass);

        // Configurar el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configurar el botón de retroceso
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Al presionar el botón de retroceso, ir al activity principal
                Intent intent = new Intent(recuperarconstrasena.this, login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Configurar el título de la barra de herramientas
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Recupera tu contraseña");

        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);

        mEditTextEmail = (EditText) findViewById(R.id.editTextEmail);
        mButtonResetPassword = (Button) findViewById(R.id.aceptar);

        mButtonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mEditTextEmail.getText().toString();
                if (!email.isEmpty()) {
                    mDialog.setMessage("Espere un momento...");
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show(); // Mostrar el diálogo antes de llamar a resetPassword
                    resetPassword();
                } else {
                    Toast.makeText(recuperarconstrasena.this, "Ingresa un correo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void resetPassword() {
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(recuperarconstrasena.this, "Se han enviado un correo para reestablecer", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(recuperarconstrasena.this, "No se pudo enviar el correo de recuperación", Toast.LENGTH_SHORT).show();
                }
                mDialog.dismiss(); // Ocultar el diálogo después de completar el proceso
            }
        });
    }
}