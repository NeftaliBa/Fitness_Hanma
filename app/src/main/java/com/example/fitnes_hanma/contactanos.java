package com.example.fitnes_hanma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnes_hanma.Instructor.configuraciones.ConfiguracionIns;

public class contactanos extends AppCompatActivity {

    EditText comentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactanos);

        // Configurar el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView toolbarTitle = findViewById(R.id.toolbarTitle);
        toolbarTitle.setText("Contactanos");

        // Configurar el botón de retroceso
        ImageView backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Al presionar el botón de retroceso, ir al activity principal
                Intent intent = new Intent(contactanos.this, ConfiguracionIns.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        comentario = findViewById(R.id.comentario);

        // Obtener el texto del EditText cuando se haga clic en el botón de enviar
        findViewById(R.id.btnEnviar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoComentario = comentario.getText().toString();

                // Verificar si el campo de comentario está vacío
                if (textoComentario.isEmpty()) {
                    // Si está vacío, mostrar un Toast pidiendo agregar un comentario
                    Toast.makeText(contactanos.this, "Agrega un comentario", Toast.LENGTH_SHORT).show();
                } else {
                    // Si hay texto, enviar el correo con el comentario
                    enviarCorreo(textoComentario);
                }
            }
        });
    }

    // Función para enviar el correo
    private void enviarCorreo(String comentario) {
        // Aquí puedes implementar la lógica real para enviar un correo electrónico
        // Por ejemplo, podrías utilizar la API de JavaMail o la funcionalidad de envío de Android

        // En este ejemplo, se muestra un Toast simulando el envío del correo
        Toast.makeText(contactanos.this, "Enviando correo con comentario: " + comentario, Toast.LENGTH_SHORT).show();
        // También puedes agregar tu lógica real para enviar el correo electrónico aquí
        // utilizando JavaMail u otras formas de envío de correo en Android
    }
}
