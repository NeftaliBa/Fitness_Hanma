package com.example.fitnes_hanma.Instructor.fragments;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
        return inflater.inflate(R.layout.b_cla_navbar_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView Usuario = view.findViewById(R.id.iconoUsuario);
        ImageView Calendario = view.findViewById(R.id.iconoCalendario);
        ImageView Social = view.findViewById(R.id.iconoSocial);
        ImageView Ajustes = view.findViewById(R.id.iconoAjustes);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("button_selection", Context.MODE_PRIVATE);
        final int[] selectedButton = {sharedPreferences.getInt("selected_button", R.id.iconoUsuario)};

        Usuario.setBackgroundResource(selectedButton[0] == R.id.iconoUsuario ? R.drawable.testbackfull : R.drawable.testbacknull);
        Calendario.setBackgroundResource(selectedButton[0] == R.id.iconoCalendario ? R.drawable.testbackfull : R.drawable.testbacknull);
        Social.setBackgroundResource(selectedButton[0] == R.id.iconoSocial ? R.drawable.testbackfull : R.drawable.testbacknull);
        Ajustes.setBackgroundResource(selectedButton[0] == R.id.iconoAjustes ? R.drawable.testbackfull : R.drawable.testbacknull);
        Usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedButton[0] != R.id.iconoUsuario) {
                    Usuario.setBackgroundResource(R.drawable.testbackfull);
                    Calendario.setBackgroundResource(R.drawable.testbacknull);
                    Social.setBackgroundResource(R.drawable.testbacknull);
                    Ajustes.setBackgroundResource(R.drawable.testbacknull);

                    // Guardar el estado de selección en SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("selected_button", R.id.iconoUsuario);
                    editor.apply();

                    selectedButton[0] = R.id.iconoUsuario;

                    Intent intent = new Intent(getActivity(), Home.class);
                    startActivity(intent);
                }
            }
        });

        Calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedButton[0] != R.id.iconoCalendario) {
                    Usuario.setBackgroundResource(R.drawable.testbackfull);
                    Calendario.setBackgroundResource(R.drawable.testbacknull);
                    Social.setBackgroundResource(R.drawable.testbacknull);
                    Ajustes.setBackgroundResource(R.drawable.testbacknull);

                    // Guardar el estado de selección en SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("selected_button", R.id.iconoCalendario);
                    editor.apply();

                    selectedButton[0] = R.id.iconoCalendario;

                    Intent intent = new Intent(getActivity(), Calendario.class);
                    startActivity(intent);
                }
            }
        });

        Social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedButton[0] != R.id.iconoSocial) {
                    Usuario.setBackgroundResource(R.drawable.testbackfull);
                    Calendario.setBackgroundResource(R.drawable.testbacknull);
                    Social.setBackgroundResource(R.drawable.testbacknull);
                    Ajustes.setBackgroundResource(R.drawable.testbacknull);

                    // Guardar el estado de selección en SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("selected_button", R.id.iconoSocial);
                    editor.apply();

                    selectedButton[0] = R.id.iconoSocial;

                    Intent intent = new Intent(getActivity(), Grupo.class);
                    startActivity(intent);
                }
            }
        });

        Ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedButton[0] != R.id.iconoAjustes) {
                    Usuario.setBackgroundResource(R.drawable.testbackfull);
                    Calendario.setBackgroundResource(R.drawable.testbacknull);
                    Social.setBackgroundResource(R.drawable.testbacknull);
                    Ajustes.setBackgroundResource(R.drawable.testbacknull);

                    // Guardar el estado de selección en SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("selected_button", R.id.iconoAjustes);
                    editor.apply();

                    selectedButton[0] = R.id.iconoAjustes;

                    Intent intent = new Intent(getActivity(), Configuraciones.class);
                    startActivity(intent);
                }
            }
        });
    }
}