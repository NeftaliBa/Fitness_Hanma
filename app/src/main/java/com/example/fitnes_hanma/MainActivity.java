package com.example.fitnes_hanma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fitnes_hanma.Admin.AdInstructor;
import com.example.fitnes_hanma.Cliente.principal;
import com.example.fitnes_hanma.Instructor.home.Home;

public class MainActivity extends AppCompatActivity {
    Button ViAd, ViCl, ViEn;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ViAd = (Button) findViewById(R.id.viAd);
        ViCl = (Button) findViewById(R.id.viCl);
        ViEn = (Button) findViewById(R.id.viEn);
        ViAd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                i = new Intent(MainActivity.this, AdInstructor.class);
                startActivity(i);
            }
        });
        ViCl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                i = new Intent(MainActivity.this, principal.class);
                startActivity(i);
            }
        });
        ViEn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                i = new Intent(MainActivity.this, Home.class);
                startActivity(i);
            }
        });
    }
}