package com.example.fitnes_hanma.Objetos;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.fitnes_hanma.R;

import java.util.List;

public class ClasesAdapterInstructor extends ArrayAdapter<Clases> {

    public ClasesAdapterInstructor(Context context, List<Clases> clasesInsList) {
        super(context, 0, clasesInsList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Obtén el objeto Clases para esta posición
        Clases claseIns = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.b_ins_clases, parent, false);
        }

        // Encuentra las vistas en el diseño personalizado
        TextView nombreClaIns = convertView.findViewById(R.id.nombreClaIns);
        TextView descripcionClaIns = convertView.findViewById(R.id.descripcionClaIns);
        TextView dia1 = convertView.findViewById(R.id.dia1);
        TextView dia2 = convertView.findViewById(R.id.dia2);
        TextView dia3 = convertView.findViewById(R.id.dia3);


        // Configura las vistas con los datos de la Clases
        assert claseIns != null;
        nombreClaIns.setText(claseIns.getNombreClase());
        descripcionClaIns.setText(claseIns.getDescripcion());
        dia1.setText(claseIns.getHor1());
        dia2.setText(claseIns.getHor2());
        dia3.setText(claseIns.getHor3());



        return convertView;
    }
}
