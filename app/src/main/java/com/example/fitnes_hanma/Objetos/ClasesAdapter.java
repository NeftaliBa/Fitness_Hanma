package com.example.fitnes_hanma.Objetos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fitnes_hanma.R;

import java.util.List;

public class ClasesAdapter extends ArrayAdapter<Clases> {

    public ClasesAdapter(Context context, List<Clases> clasesList) {
        super(context, 0, clasesList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtén el objeto Clases para esta posición
        Clases clase = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.a_ad_list_item_clase, parent, false);
        }

        // Encuentra las vistas en el diseño personalizado
        TextView nombreClaseTextView = convertView.findViewById(R.id.nombreClaseTextView);
        TextView descripcionTextView = convertView.findViewById(R.id.descripcionTextView);
        TextView nombreInstructorTextView = convertView.findViewById(R.id.nombreInstructorTextView);

        // Configura las vistas con los datos de la Clases
        nombreClaseTextView.setText(clase.getNombreClase());
        descripcionTextView.setText(clase.getDescripcion());
        nombreInstructorTextView.setText(clase.getNombreInstructor());

        return convertView;
    }
}