package com.example.fitnes_hanma.Objetos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fitnes_hanma.R;

import java.util.List;



public class InstructorAdapter extends ArrayAdapter<Instructor> {

    public InstructorAdapter(Context context, List<Instructor> intructList) {
        super(context, 0, intructList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtén el objeto Clases para esta posición
        Instructor instruc = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.a_ad_list_item_instruc, parent, false);
        }

        // Encuentra las vistas en el diseño personalizado
        TextView nombreInstTextView = convertView.findViewById(R.id.nombreInstTextView);
        TextView correoInstTextView = convertView.findViewById(R.id.correoInstTextView);

        // Configura las vistas con los datos de la Clases
        nombreInstTextView.setText(instruc.getNombreInstructor());
        correoInstTextView.setText(instruc.getEmail());

        return convertView;
    }
}

