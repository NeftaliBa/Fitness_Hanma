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
        TextView horarios = convertView.findViewById(R.id.horarios);
        TextView hora = convertView.findViewById(R.id.hora);
        ImageView imageTextView = convertView.findViewById(R.id.imageTextView);

        nombreClaseTextView.setText(clase.getNombreClase());
        nombreInstructorTextView.setText(clase.getNombreInstructor());
        descrip.setText(clase.getDescripcion());
        hora.setText(clase.getHoraClase());

        Instructor instructor = clase.getInstructor();
        if (instructor != null) {
            String urlImagenInstructor = instructor.getProfileImageUrl();

            // Agregar un log para imprimir la URL de la imagen recuperada
            Log.d("URL_IMAGEN", "URL de la imagen del instructor: " + imageTextView);

            // Se adapta la lógica para cargar la imagen con Glide si la URL es válida
            if (urlImagenInstructor != null && !urlImagenInstructor.isEmpty()) {
                Glide.with(getContext())
                        .load(urlImagenInstructor)
                        .placeholder(R.drawable.default_image)
                        .into(imageTextView);
            } else {
                // Dejar el ImageView sin ninguna imagen si no hay URL válida
                imageTextView.setImageDrawable(null);
            }
        }

        return convertView;
    }
}