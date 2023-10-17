package com.example.fitnes_hanma.Instructor.fragments;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fitnes_hanma.Instructor.calendario.Calendario;
import com.example.fitnes_hanma.Instructor.configuraciones.Configuraciones;
import com.example.fitnes_hanma.Instructor.grupo.Grupo;
import com.example.fitnes_hanma.Instructor.home.Home;
import com.example.fitnes_hanma.R;

public class Navbar extends Fragment {

    public Navbar() {
        // Required empty public constructor
    }

    public static Navbar newInstance(String param1, String param2) {
        Navbar fragment = new Navbar();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navbar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView iconoUsuario = view.findViewById(R.id.iconoUsuario);
        ImageView iconoCalendario = view.findViewById(R.id.iconoCalendario);
        ImageView iconoSocial = view.findViewById(R.id.iconoSocial);
        ImageView iconoAjustes = view.findViewById(R.id.iconoAjustes);

        iconoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la página MainActivity
                Intent intent = new Intent(getActivity(), Home.class);
                startActivity(intent);
            }
        });

        iconoCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la página Calendario
                Intent intent = new Intent(getActivity(), Calendario.class);
                startActivity(intent);
            }
        });

        iconoSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la página Grupo
                Intent intent = new Intent(getActivity(), Grupo.class);
                startActivity(intent);
            }
        });

        iconoAjustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navegar a la página Configuraciones
                Intent intent = new Intent(getActivity(), Configuraciones.class);
                startActivity(intent);
            }
        });
    }
}