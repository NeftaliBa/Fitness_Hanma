package com.example.fitnes_hanma.Instructor.home;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.fitnes_hanma.Instructor.calendario.Calendario;
import com.example.fitnes_hanma.Instructor.calendario.Calendario_semanal;
import com.example.fitnes_hanma.R;

public class Home extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView QR;
        QR = (ImageView) findViewById(R.id.qr);

        QR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Home_QR.class);
                startActivity(intent);
            }
        });



    }
}