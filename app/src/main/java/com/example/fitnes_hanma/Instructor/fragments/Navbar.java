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
        return inflater.inflate(R.layout.b_cla_navbar_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView Usuario = view.findViewById(R.id.iconoUsuario);
        ImageView Calendario = view.findViewById(R.id.iconoCalendario);
        ImageView Social = view.findViewById(R.id.iconoSocial);

        Usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Home.class);
                    startActivity(intent);
            }
        });

        Calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(getActivity(), Calendario.class);
                    startActivity(intent);
            }
        });

        Social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Grupo.class);
                    startActivity(intent);
            }
        });
    }
}