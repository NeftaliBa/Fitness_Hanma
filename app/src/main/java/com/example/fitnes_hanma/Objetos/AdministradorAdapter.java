package com.example.fitnes_hanma.Objetos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fitnes_hanma.R;

import java.util.List;

public class AdministradorAdapter extends ArrayAdapter<Administrador> {

    public AdministradorAdapter(Context context, List<Administrador> adminList) {
        super(context, 0, adminList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtén el objeto Clases para esta posición
        Administrador admin = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.a_ad_list_item_admin, parent, false);
        }

        // Encuentra las vistas en el diseño personalizado
        TextView nombreAdminTextView = convertView.findViewById(R.id.nombreAdminTextView);
        TextView correoAdminTextView = convertView.findViewById(R.id.correoAdminTextView);

        // Configura las vistas con los datos de la Clases
        assert admin != null;
        nombreAdminTextView.setText(admin.getAname());
        correoAdminTextView.setText(admin.getAemail());

        return convertView;
    }
    public void setData(List<Administrador> dataList) {
        clear(); // Limpia la lista actual del adaptador
        if (dataList != null) {
            addAll(dataList); // Agrega todos los elementos de la nueva lista
        }
    }
}



