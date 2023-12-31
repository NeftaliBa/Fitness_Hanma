package com.example.fitnes_hanma.Objetos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fitnes_hanma.R;

import java.util.List;

public class ClasesClienteInicioAdapter extends ArrayAdapter<Clases> {

    public ClasesClienteInicioAdapter(Context context, List<Clases> clasesList) {
        super(context, 0, clasesList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtén el objeto Clases para esta posición
        Clases clase = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.c_cl_clase_principal, parent, false);
        }

        // Encuentra las vistas en el diseño personalizado
        TextView nombreClaseTextView = convertView.findViewById(R.id.nombreClaseTextView);
        TextView nombreInstructorTextView = convertView.findViewById(R.id.correoInsClPrinci);
        TextView descrip = convertView.findViewById(R.id.descripcionTextView);
        TextView hora = convertView.findViewById(R.id.hora);



        // Configura las vistas con los datos de la Clases
        nombreClaseTextView.setText(clase.getNombreClase());
        nombreInstructorTextView.setText(clase.getNombreInstructor());
        descrip.setText(clase.getDescripcion());

        return convertView;
    }
}
