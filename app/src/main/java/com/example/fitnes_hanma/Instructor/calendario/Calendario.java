package com.example.fitnes_hanma.Instructor.calendario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.example.fitnes_hanma.R;

public class Calendario extends AppCompatActivity {

    private List<TextView> dayTextViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_cla_calendario);

        // Obtén referencias a los TextViews
        dayTextViews = new ArrayList<>();
        dayTextViews.add(findViewById(R.id.dom));
        dayTextViews.add(findViewById(R.id.lun));
        dayTextViews.add(findViewById(R.id.mar));
        dayTextViews.add(findViewById(R.id.mie));
        dayTextViews.add(findViewById(R.id.jue));
        dayTextViews.add(findViewById(R.id.vie));
        dayTextViews.add(findViewById(R.id.sab));

        // Agrega OnClickListener a cada TextView
        for (TextView textView : dayTextViews) {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Restaura el color de fondo de todos los TextViews
                    for (TextView tv : dayTextViews) {
                        tv.setBackgroundResource(R.drawable.b_cla_fondo_days);
                    }

                    // Cambia el color de fondo del TextView seleccionado
                    v.setBackgroundResource(R.drawable.b_cla_fondo_days_colored);
                }
            });
        }

    }
}


