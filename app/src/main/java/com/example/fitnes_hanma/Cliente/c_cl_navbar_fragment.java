package com.example.fitnes_hanma.Cliente;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.fitnes_hanma.Admin.AdPreferenceManager;
import com.example.fitnes_hanma.Admin.Principal.AdPClases;
import com.example.fitnes_hanma.Admin.Principal.AdPCliente;
import com.example.fitnes_hanma.Admin.Principal.AdPInstructor;
import com.example.fitnes_hanma.R;

public class c_cl_navbar_fragment extends Fragment {

    public c_cl_navbar_fragment() {
        // Required empty public constructor
    }

    public static c_cl_navbar_fragment newInstance(String param1, String param2) {
        c_cl_navbar_fragment fragment = new c_cl_navbar_fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.c_cl_navbar_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView servicios = view.findViewById(R.id.servicios);
        ImageView horarios = view.findViewById(R.id.horar);
        ImageView asis = view.findViewById(R.id.asis);
        ImageView configuracion = view.findViewById(R.id.config);

        final ImageView[] selectedImageView = new ImageView[1];
        selectedImageView[0] = servicios;

        // Cargar la vista seleccionada desde SharedPreferences
        String selectedView = AdPreferenceManager.getSelectedView(requireContext());

        if (selectedView.equals("servicios")) {
            servicios.setColorFilter(ContextCompat.getColor(requireContext(), R.color.encendido));
            horarios.setColorFilter(ContextCompat.getColor(requireContext(), R.color.apagado));
            asis.setColorFilter(ContextCompat.getColor(requireContext(), R.color.apagado));
            configuracion.setColorFilter(ContextCompat.getColor(requireContext(), R.color.apagado));
        } else if (selectedView.equals("horar")) {
            horarios.setColorFilter(ContextCompat.getColor(requireContext(), R.color.encendido));
            servicios.setColorFilter(ContextCompat.getColor(requireContext(), R.color.apagado));
            asis.setColorFilter(ContextCompat.getColor(requireContext(), R.color.apagado));
            configuracion.setColorFilter(ContextCompat.getColor(requireContext(), R.color.apagado));
            selectedImageView[0] = horarios;
        } else if (selectedView.equals("asis")) {
            asis.setColorFilter(ContextCompat.getColor(requireContext(), R.color.encendido));
            servicios.setColorFilter(ContextCompat.getColor(requireContext(), R.color.apagado));
            horarios.setColorFilter(ContextCompat.getColor(requireContext(), R.color.apagado));
            configuracion.setColorFilter(ContextCompat.getColor(requireContext(), R.color.apagado));
            selectedImageView[0] = asis;
        }   else if (selectedView.equals("configuracion")) {
            configuracion.setColorFilter(ContextCompat.getColor(requireContext(), R.color.encendido));
            servicios.setColorFilter(ContextCompat.getColor(requireContext(), R.color.apagado));
            horarios.setColorFilter(ContextCompat.getColor(requireContext(), R.color.apagado));
            asis.setColorFilter(ContextCompat.getColor(requireContext(), R.color.apagado));
            selectedImageView[0] = configuracion;
        }

        servicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedImageView[0].setColorFilter(ContextCompat.getColor(requireContext(), R.color.apagado));
                servicios.setColorFilter(ContextCompat.getColor(requireContext(), R.color.encendido));
                selectedImageView[0] = servicios;

                // Guardar la vista seleccionada en SharedPreferences
                AdPreferenceManager.setSelectedView(requireContext(), "servicios");

                // Luego, inicia la actividad relacionada
                Intent intent = new Intent(getActivity(), servicios.class);
                startActivity(intent);
            }
        });

        horarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedImageView[0].setColorFilter(ContextCompat.getColor(requireContext(), R.color.apagado));
                horarios.setColorFilter(ContextCompat.getColor(requireContext(), R.color.encendido));
                selectedImageView[0] = horarios;

                // Guardar la vista seleccionada en SharedPreferences
                AdPreferenceManager.setSelectedView(requireContext(), "horarios");

                // Luego, inicia la actividad relacionada
                Intent intent = new Intent(getActivity(), AdPInstructor.class);
                startActivity(intent);
            }
        });

        asis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedImageView[0].setColorFilter(ContextCompat.getColor(requireContext(), R.color.apagado));
                asis.setColorFilter(ContextCompat.getColor(requireContext(), R.color.encendido));
                selectedImageView[0] = asis;

                // Guardar la vista seleccionada en SharedPreferences
                AdPreferenceManager.setSelectedView(requireContext(), "asis");

                // Luego, inicia la actividad relacionada
                Intent intent = new Intent(getActivity(), AdPCliente.class);
                startActivity(intent);
            }
        });
        configuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedImageView[0].setColorFilter(ContextCompat.getColor(requireContext(), R.color.apagado));
                configuracion.setColorFilter(ContextCompat.getColor(requireContext(), R.color.encendido));
                selectedImageView[0] = configuracion;

                // Guardar la vista seleccionada en SharedPreferences
                AdPreferenceManager.setSelectedView(requireContext(), "configuracion");

                // Luego, inicia la actividad relacionada
                Intent intent = new Intent(getActivity(), AdPCliente.class);
                startActivity(intent);
            }
        });
    }
}
