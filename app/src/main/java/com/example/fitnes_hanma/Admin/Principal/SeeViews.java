package com.example.fitnes_hanma.Admin.Principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.fitnes_hanma.Cliente.principal;
import com.example.fitnes_hanma.Instructor.home.Home;
import com.example.fitnes_hanma.R;
import com.example.fitnes_hanma.register;

public class SeeViews extends AppCompatActivity {
    Button ViAd, ViCl, ViEn;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_views);
        ViAd = (Button) findViewById(R.id.viAd);
        ViCl = (Button) findViewById(R.id.viCl);
        ViEn = (Button) findViewById(R.id.viEn);


        ViAd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                i = new Intent(SeeViews.this, AdInstructor.class);
                startActivity(i);
            }
        });
        ViCl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                i = new Intent(SeeViews.this, register.class);
                startActivity(i);
            }
        });
        ViEn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                i = new Intent(SeeViews.this, Home.class);
                startActivity(i);
            }
        });
    }
}