package com.example.fitnes_hanma.Objetos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fitnes_hanma.R;

import java.util.Arrays;
import java.util.List;

public class ClassesCalendario extends ArrayAdapter<Clases> {

    public ClassesCalendario(Context context, List<Clases> clasesCalendarList) {
        super(context, 0, clasesCalendarList);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Clases clase = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.b_cla_clases_per_day, parent, false);
        }

        TextView className = convertView.findViewById(R.id.className);
        TextView whatTime1 = convertView.findViewById(R.id.whatTime1);
        TextView whatTime2 = convertView.findViewById(R.id.whatTime2);
        TextView whatTime3 = convertView.findViewById(R.id.whatTime3);

        // Asegúrate de que las variables hor1, hor2, hor3 estén definidas en Clases
        String hor1 = clase.getHor1();
        String hor2 = clase.getHor2();
        String hor3 = clase.getHor3();

        if (hor1.equals("")) {
            // No hay datos para el primer horario
        } else {
            String[] H1partes = hor1.split(" ", 2);
            String di1 = H1partes[0];
            String ho1 = H1partes[1];
            whatTime1.setText(ho1);
        }

        if (hor2.equals("")) {
            // No hay datos para el segundo horario
        } else {
            String[] H2partes = hor2.split(" ", 2);
            String di2 = H2partes[0];
            String ho2 = H2partes[1];
            whatTime2.setText(ho2);
        }

        if (hor3.equals("")) {
            // No hay datos para el tercer horario
        } else {
            String[] H3partes = hor3.split(" ", 2);
            String di3 = H3partes[0];
            String ho3 = H3partes[1];
            whatTime3.setText(ho3);
        }

        // Establecer otros datos de clase
        assert clase != null;
        className.setText(clase.getNombreClase());

        return convertView;
    }
}
