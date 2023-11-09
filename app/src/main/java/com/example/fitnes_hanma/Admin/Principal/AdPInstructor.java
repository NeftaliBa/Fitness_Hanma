package com.example.fitnes_hanma.Admin.Principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fitnes_hanma.Admin.Secundarias.AdSModIns;
import com.example.fitnes_hanma.Admin.SeeViews;
import com.example.fitnes_hanma.R;

public class AdPInstructor extends AppCompatActivity {
    Intent i;
    EditText searchClient;
    TextView name, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AdminStatusBar);
        setContentView(R.layout.activity_ad_p_instructor);
        searchClient = (EditText) findViewById(R.id.seCli);
        ImageView buscar = findViewById(R.id.buscar);
        ImageView regre = findViewById(R.id.regre);
        ImageView plus = findViewById(R.id.plus);

        regre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(AdPInstructor.this, SeeViews.class);
                startActivity(i);
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(AdPInstructor.this, AdPCliente.class);
                startActivity(i);
            }
        });


    }

}
