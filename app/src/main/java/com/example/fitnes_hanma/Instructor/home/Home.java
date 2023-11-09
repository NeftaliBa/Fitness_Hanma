package com.example.fitnes_hanma.Instructor.home;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.fitnes_hanma.R;

public class Home extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_cla_home);

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