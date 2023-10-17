package com.example.fitnes_hanma.Instructor.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fitnes_hanma.Admin.AdInstructor;

<<<<<<< HEAD:app/src/main/java/com/example/fitnes_hanma/MainActivity.java
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
=======
import com.example.fitnes_hanma.R;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
>>>>>>> f3454e509a6c1ac47124b17319c6e715ac558aff:app/src/main/java/com/example/fitnes_hanma/Instructor/home/Home.java
    }
}