package com.example.fitnes_hanma.Admin.Principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fitnes_hanma.Admin.Secundarias.AdModCli;
import com.example.fitnes_hanma.Admin.Secundarias.AdModIns;
import com.example.fitnes_hanma.R;

public class AdInstructor extends AppCompatActivity {
    Intent i;
    EditText searchClient;
    TextView name, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AdminStatusBar);
        setContentView(R.layout.activity_ad_instructor);
        searchClient = (EditText) findViewById(R.id.seCli);
        ImageView editar = findViewById(R.id.edi);
        ImageView buscar = findViewById(R.id.buscar);
        ImageView regre = findViewById(R.id.regre);
        ImageView plus = findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(AdInstructor.this, AdCliente.class);
                startActivity(i);
            }
        });
        regre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(AdInstructor.this, SeeViews.class);
                startActivity(i);
            }
        });
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(AdInstructor.this, AdModIns.class);
                startActivity(i);
            }
        });

        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        //    getWindow().setNavigationBarColor(getResources().getColor(R.color.verde1));
        //}
    }

}
