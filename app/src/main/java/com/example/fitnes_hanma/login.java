package com.example.fitnes_hanma;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnes_hanma.Security;
import com.example.fitnes_hanma.Admin.SeeOtherViews;
import com.example.fitnes_hanma.Cliente.principal;
import com.example.fitnes_hanma.Instructor.home.Home;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    EditText email, password;
    LinearLayout textViewCrearCuenta, loGoogle, btn_login;
    GoogleSignInClient signInClient;
    private final int REQUEST_CODE = 100;

    FirebaseAuth mAuth;
    private ProgressDialog mDialog;
    FirebaseAuth firebaseAuth;
    String userId, nameP;

    private boolean isPasswordVisible = false;

    private TextInputLayout passwordTextInputLayout;
    private TextInputEditText passwordEditText;
    private Button showHidePasswordButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_login);

        // Configurar el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configurar el botón de retroceso
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Al presionar el botón de retroceso, ir al activity principal
                Intent intent = new Intent(login.this, menuRL.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Configurar el título de la barra de herramientas
        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Inicia sesión");

        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);

        email = findViewById(R.id.correo);
        password = findViewById(R.id.contrasena);
        btn_login = findViewById(R.id.btn_loguear);
        loGoogle = (LinearLayout) findViewById(R.id.login_google);
        TextView textViewRecuperar = findViewById(R.id.recuperar);

        textViewRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para redirigir a la actividad de recuperación de contraseña
                Intent intent = new Intent(login.this, recuperarconstrasena.class);
                startActivity(intent);
            }
        });

        textViewCrearCuenta = findViewById(R.id.crearCuenta);
        textViewCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para redirigir a la actividad de registro
                Intent intent = new Intent(login.this, register.class);
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
                    try {
                        loginUser(emailUser, passUser);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        loGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithGoogle();

            }
        });
    }

    // Método para la autenticación con Google
    private void signInWithGoogle() {
        Log.d("LoginActivity", "Iniciando autenticación con Google");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("597709671741-158thjtmnq65k5e0pa681tndvjn4cvj5.apps.googleusercontent.com")
                .requestEmail()
                .build();


        signInClient = GoogleSignIn.getClient(this, gso);

        Intent signInIntent = signInClient.getSignInIntent();
        startActivityForResult(signInIntent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                // Autenticación exitosa con Google, ahora puedes guardar la información en Firebase
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Toast.makeText(this, "Error al autenticar con Google", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Autenticación exitosa con Google
                            Log.d("LoginActivity", "Autenticación exitosa con Google");
                            saveGoogleUserToFirestore(acct);
                        } else {
                            Log.e("LoginActivity", "Error al autenticar con Firebase", task.getException());
                            Toast.makeText(login.this, "Error al autenticar con Firebase", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // Método para guardar la información del usuario de Google en Firestore
    private void saveGoogleUserToFirestore(GoogleSignInAccount account) {
        String userId = mAuth.getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> userData = new HashMap<>();
        userData.put("id", userId);
        userData.put("email", account.getEmail());
        userData.put("name", account.getDisplayName());
        userData.put("role", "1"); // Establece el role por defecto

        db.collection("user").document(userId)
                .set(userData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        // Escritura exitosa en Firestore
                        Log.d("LoginActivity", "Escritura exitosa en Firestore");
                        Toast.makeText(login.this, "Inicio de sesión exitoso con Google", Toast.LENGTH_SHORT).show();
                        // Aquí puedes redirigir a la actividad correspondiente según el rol del usuario
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("LoginActivity", "Error al guardar la información en Firestore", e);
                        Toast.makeText(login.this, "Error al guardar la información en Firestore", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Método para iniciar sesión con correo y contraseña
    private void loginUser(String emailUser, String passUser) throws Exception {
        mAuth.signInWithEmailAndPassword(emailUser, Security.encrypt(passUser))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mDialog.dismiss();
                        if (task.isSuccessful()) {
                            Log.d("LoginActivity", "Autenticación exitosa con correo y contraseña");
                            firebaseAuth = FirebaseAuth.getInstance();
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            userId = firebaseUser.getUid();
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            // Verificar el rol y redirigir según sea necesario
                            checkUserRoles();
                        } else {
                            Log.e("LoginActivity", "Error al iniciar sesión con correo y contraseña", task.getException());
                            Toast.makeText(login.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mDialog.dismiss();
                        Log.e("LoginActivity", "Error al iniciar sesión con correo y contraseña", e);
                        Toast.makeText(login.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Método para verificar el rol del usuario y redirigir según sea necesario
    private void checkUserRoles() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Verificar en la colección "admin"
        db.collection("admin").document(userId).get().addOnCompleteListener(task0 -> {
            if (task0.isSuccessful() && task0.getResult().exists()) {
                // Usuario es un administrador
                startActivityAccordingToRole(SeeOtherViews.class);
            } else {
                // Verificar en la colección "trainer"
                db.collection("trainer").document(userId).get().addOnCompleteListener(task1 -> {
                    if (task1.isSuccessful() && task1.getResult().exists()) {
                        // Usuario es un entrenador
                        startActivityAccordingToRole(Home.class);
                    } else {
                        // Si no hay documento en "trainer", asumimos "user"
                        startActivityAccordingToRole(principal.class);
                    }
                });
            }
        });
    }

    // Método para redirigir a la actividad correspondiente según el rol del usuario
    private void startActivityAccordingToRole(Class<?> cls) {
        Intent intent = new Intent(login.this, cls);
        finish();
        startActivity(intent);
    }
}
