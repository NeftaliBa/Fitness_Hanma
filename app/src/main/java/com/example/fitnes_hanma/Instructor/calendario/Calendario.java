package com.example.fitnes_hanma.Instructor.calendario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.example.fitnes_hanma.R;

public class Calendario extends AppCompatActivity {

    private RecyclerView calendarRecyclerView;
    private TextView monthYearTV;
    private ImageView anteriormes, siguientemes;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_cla_calendario);

        calendar = Calendar.getInstance();
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearTV = findViewById(R.id.monthYearTV);
        anteriormes = findViewById(R.id.anteriormes);
        siguientemes = findViewById(R.id.siguientemes);


        anteriormes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, -1);
                updateCalendar();
            }
        });

        siguientemes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, 1);
                updateCalendar();
            }
        });
    }

    private void updateCalendar() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy", Locale.getDefault());
        monthYearTV.setText(sdf.format(calendar.getTime()));

    }
}
