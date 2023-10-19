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
import com.example.fitnes_hanma.Admin.Secundarias.AdModSe;
import com.example.fitnes_hanma.R;

public class AdServicios extends AppCompatActivity {
    Intent i;
    EditText searchClient;
    TextView name, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_servicios);

        searchClient = (EditText) findViewById(R.id.seCli);
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.mail);
        ImageView editar = findViewById(R.id.edi);
        ImageView buscar = findViewById(R.id.buscar);
        RelativeLayout regre = findViewById(R.id.regre);

        regre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(AdServicios.this, SeeViews.class);
                startActivity(i);
            }
        });
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(AdServicios.this, AdModSe.class);
                startActivity(i);
            }
        });

        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        //    getWindow().setNavigationBarColor(getResources().getColor(R.color.verde1));
        //}
    }

}