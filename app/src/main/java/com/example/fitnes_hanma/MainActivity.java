package com.example.fitnes_hanma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fitnes_hanma.Admin.AdInstructor;

public class MainActivity extends AppCompatActivity {
    Intent i;
    Button ViAdmin, ViCliente, ViEntrenador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViAdmin = (Button)findViewById(R.id.viAd);
        ViCliente =(Button) findViewById(R.id.viCl);
        ViEntrenador=(Button) findViewById(R.id.viEn);
        ViAdmin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                i = new Intent(MainActivity.this, AdInstructor.class);
                startActivity(i);
            }
        });
    }
}