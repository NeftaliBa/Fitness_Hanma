package com.example.fitnes_hanma.Instructor.grupo;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnes_hanma.Objetos.Message;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import com.example.fitnes_hanma.Instructor.configuraciones.ConfiguracionIns;
import com.example.fitnes_hanma.Instructor.home.Home;
import com.example.fitnes_hanma.Objetos.ChatAdapter;
import com.example.fitnes_hanma.R;
import com.example.fitnes_hanma.menuRL;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Comunidad extends AppCompatActivity {
    String nameUser, userId;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    Intent i;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

     RecyclerView recyclerView;
     EditText messageEditText;
    LinearLayout sendButton;

     ChatAdapter chatAdapter;
     List<Message> messageList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_cla_grupo);

        // Configurar el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Configurar el título de la barra de herramientas
        TextView toolbarTitle = findViewById(R.id.toolbarComunityTitle);
        toolbarTitle.setText("Comunidad");

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();


        recyclerView = (RecyclerView) findViewById(R.id.messages);
        messageEditText = (EditText) findViewById(R.id.messageEditText);
        sendButton = (LinearLayout) findViewById(R.id.sendButton);

        // Configuración del RecyclerView y el adaptador
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        chatAdapter = new ChatAdapter(messageList);
        recyclerView.setAdapter(chatAdapter);


        CollectionReference messagesRef = db.collection("chatMessages");

        // Agrega un Listener para escuchar los cambios en la colección
        messagesRef.orderBy("timestamp", Query.Direction.ASCENDING)

                .addSnapshotListener((value, error) -> {
                    if (error != null) {
                        // Maneja el error si ocurre
                        return;
                    }

                    // Limpia la lista actual de mensajes
                    messageList.clear();

                    // Recorre los documentos en la colección y agrega los mensajes a messageList
                    for (QueryDocumentSnapshot document : value) {
                        Message message = document.toObject(Message.class);
                        messageList.add(message);
                    }

                    // Notifica al adaptador que los datos han cambiado
                    chatAdapter.notifyDataSetChanged();
                });





        userId = user.getUid();
        FirebaseFirestore.getInstance().collection("user").document(userId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            nameUser = documentSnapshot.getString("name");

                            // Verificar si el nombre del usuario se obtuvo correctamente
                            if (nameUser != null && !nameUser.isEmpty()) {
                                // Configurar el título de la barra de herramientas con el nombre del usuario
                                TextView toolbarTitle = findViewById(R.id.toolbarComunityTitle);
                                toolbarTitle.setText("Comunidad - " + nameUser);

                                // Agregar log para verificar el nombre del usuario
                                Log.d("Comunidad", "Nombre del usuario: " + nameUser);
                            } else {
                                // Agregar log si el nombre del usuario está vacío o nulo
                                Log.e("Comunidad", "Error: Nombre de usuario vacío o nulo");
                            }
                        } else {
                            // Agregar log si el documento no existe
                            Log.e("Comunidad", "Error: Documento de usuario no encontrado");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Agregar log en caso de error en la consulta
                        Log.e("Comunidad", "Error al obtener datos de usuario", e);
                    }
                });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });



        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });



    }

    private void sendMessage() {
        String messageContent = messageEditText.getText().toString().trim();

        if (!TextUtils.isEmpty(messageContent)) {
            if (user != null) {
                // El usuario está autenticado, puedes obtener su información
                CollectionReference messagesRef = db.collection("chatMessages");

                Map<String, Object> newMessage = new HashMap<>();
                newMessage.put("senderId", user.getUid());
                newMessage.put("senderName", nameUser);
                newMessage.put("content", messageContent);
                newMessage.put("timestamp", FieldValue.serverTimestamp());

                messagesRef.add(newMessage);

                messageEditText.setText("");
            } else {
                // El usuario no está autenticado, maneja esto según tus requisitos
                Toast.makeText(this, "Usuario no autenticado", Toast.LENGTH_SHORT).show();
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.c_cli_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.m1) {
            i = new Intent(Comunidad.this, ConfiguracionIns.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.m2) {
            firebaseAuth.signOut();
            startActivity(new Intent(Comunidad.this, menuRL.class));
            Toast.makeText(this, "Cerraste sesion", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}