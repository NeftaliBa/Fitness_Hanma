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
import com.example.fitnes_hanma.Objetos.Clases;
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
        ImageView imageView = convertView.findViewById(R.id.imageTextView);

        assert clase != null;
        nombreClaseTextView.setText(clase.getNombreClase());
        nombreInstructorTextView.setText(clase.getNombreInstructor());
        descrip.setText(clase.getDescripcion());

        // Agregar logs para verificar la URL de la imagen
        String imageUrl = clase.getImagenUrl();
        Log.d("URL_DEBUG", "URL de la imagen: " + imageUrl);

        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(getContext())
                    .load(imageUrl)
                    .placeholder(R.drawable.default_image)
                    .into(imageView);
        } else {
            imageView.setImageResource(R.drawable.default_image);
        }

        return convertView;
    }
}
