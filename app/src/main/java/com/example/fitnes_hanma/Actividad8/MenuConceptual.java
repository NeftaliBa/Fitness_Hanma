package com.example.fitnes_hanma.Actividad8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitnes_hanma.R;

public class MenuConceptual extends AppCompatActivity {

    EditText e1, e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AdminStatusBar);
        setTheme(R.style.actividad8);
        setContentView(R.layout.activity_menu_conceptual);
    }
    //1. menu principal
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.m1) {
            Toast.makeText(this, "opcion configuracion", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.m2) {
            Toast.makeText(this, "opcion microfono", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.m3) {
            Toast.makeText(this, "opcion salir", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}