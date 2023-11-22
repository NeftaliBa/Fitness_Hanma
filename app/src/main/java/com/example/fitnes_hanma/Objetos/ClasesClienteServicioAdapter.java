package com.example.fitnes_hanma.Objetos;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fitnes_hanma.R;

import java.util.List;

public class ClasesClienteServicioAdapter extends ArrayAdapter<Clases> {

    public ClasesClienteServicioAdapter(Context context, List<Clases> clasesList) {
        super(context, 0, clasesList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Clases clase = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.c_cl_clase_servicios, parent, false);
        }

        TextView nombreClaseTextView = convertView.findViewById(R.id.nombreClaseTextView);
        TextView nombreInstructorTextView = convertView.findViewById(R.id.nombreInstructorTextView);
        TextView descrip = convertView.findViewById(R.id.descripcionTextView);

        TextView dia1 = convertView.findViewById(R.id.dia1CS);
        TextView dia2 = convertView.findViewById(R.id.dia2CS);
        TextView dia3 = convertView.findViewById(R.id.dia3CS);


        assert clase != null;
        nombreClaseTextView.setText(clase.getNombreClase());
        nombreInstructorTextView.setText(clase.getNombreInstructor());
        descrip.setText(clase.getDescripcion());
        dia1.setText(clase.getHor1());
        dia2.setText(clase.getHor2());
        dia3.setText(clase.getHor3());



        return convertView;
    }
}