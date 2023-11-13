package com.example.fitnes_hanma.Objetos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fitnes_hanma.R;

import java.util.List;

public class UsuarioAdapter extends ArrayAdapter<Usuarios> {

    public UsuarioAdapter(Context context, List<Usuarios> clientList) {
        super(context, 0, clientList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Usuarios usuario = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.a_ad_list_item_user, parent, false);
        }

        // Encuentra las vistas en el diseño personalizado
        TextView nombreTextView = convertView.findViewById(R.id.nombreTextView);
        TextView correoTextView = convertView.findViewById(R.id.correoTextView);

        // Configura las vistas con los datos de la Clases
        nombreTextView.setText(usuario.getName());
        correoTextView.setText(usuario.getEmail());

        return convertView;
    }
}
