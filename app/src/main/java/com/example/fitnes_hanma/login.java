package com.example.fitnes_hanma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnes_hanma.Cliente.principal;
import com.example.fitnes_hanma.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.security.auth.login.LoginException;

public class login extends AppCompatActivity {

    Button btn_login;
    EditText email, password;
    FirebaseAuth mAuth;
    private ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);

        email = findViewById(R.id.correo);
        password = findViewById(R.id.contrasena);
        btn_login = findViewById(R.id.btn_loguear);

        TextView textViewButton = findViewById(R.id.crearCuenta);
        textViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí define la lógica para redirigir a otro apartado de la aplicación
                // Puedes usar un Intent para iniciar otra actividad, por ejemplo:
                Intent intent = new Intent(login.this, recuperarconstrasena.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailUser = email.getText().toString().trim();
                String passUser = password.getText().toString().trim();

                if(emailUser.isEmpty() && passUser.isEmpty()){
                    Toast.makeText(login.this, "Ingresar los datos", Toast.LENGTH_SHORT).show();
                } else {
                    mDialog.setMessage("Iniciando sesión...");
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.show();
                    loginUser(emailUser, passUser);
                }
            }
        });
    }

    private void loginUser(String emailUser, String passUser) {
        mAuth.signInWithEmailAndPassword(emailUser,passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mDialog.dismiss();
                if (task.isSuccessful()){
                    finish();
                    startActivity(new Intent(login.this, principal.class));
                    Toast.makeText(login.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(login.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                mDialog.dismiss();
                Toast.makeText(login.this, "Error al iniciar sesion", Toast.LENGTH_SHORT).show();
            }
        });
    }
}