package com.example.fitnes_hanma.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.fitnes_hanma.Admin.Principal.AdPClases;
import com.example.fitnes_hanma.Admin.Principal.AdPCliente;
import com.example.fitnes_hanma.Admin.Principal.AdPInstructor;
import com.example.fitnes_hanma.R;

public class Adnavbar extends Fragment {

    public Adnavbar() {
        // Required empty public constructor
    }

    public static Adnavbar newInstance(String param1, String param2) {
        Adnavbar fragment = new Adnavbar();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.a_ad_fragment_adnavbar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView instructor = view.findViewById(R.id.instructor);
        ImageView clases = view.findViewById(R.id.clases);
        ImageView cliente = view.findViewById(R.id.cliente);

        final ImageView[] selectedImageView = new ImageView[1];
        selectedImageView[0] = clases;

        // Cargar la vista seleccionada desde SharedPreferences
        String selectedView = AdPreferenceManager.getSelectedView(requireContext());

        if (selectedView.equals("clases")) {
            clases.setColorFilter(ContextCompat.getColor(requireContext(), R.color.encendido));
            instructor.setColorFilter(ContextCompat.getColor(requireContext(), R.color.apagado));
            cliente.setColorFilter(ContextCompat.getColor(requireContext(), R.color.apagado));
        } else if (selectedView.equals("instructor")) {
            instructor.setColorFilter(ContextCompat.getColor(requireContext(), R.color.encendido));
            clases.setColorFilter(ContextCompat.getColor(requireContext(), R.color.apagado));
            cliente.setColorFilter(ContextCompat.getColor(requireContext(), R.color.apagado));
            selectedImageView[0] = instructor;
        } else if (selectedView.equals("cliente")) {
            cliente.setColorFilter(ContextCompat.getColor(requireContext(), R.color.encendido));
            clases.setColorFilter(ContextCompat.getColor(requireContext(), R.color.apagado));
            instructor.setColorFilter(ContextCompat.getColor(requireContext(), R.color.apagado));
            selectedImageView[0] = cliente;
        }

        clases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedImageView[0].setColorFilter(ContextCompat.getColor(requireContext(), R.color.apagado));
                clases.setColorFilter(ContextCompat.getColor(requireContext(), R.color.encendido));
                selectedImageView[0] = clases;

                // Guardar la vista seleccionada en SharedPreferences
                AdPreferenceManager.setSelectedView(requireContext(), "clases");

                // Luego, inicia la actividad relacionada
                Intent intent = new Intent(getActivity(), AdPClases.class);
                startActivity(intent);
            }
        });

        instructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedImageView[0].setColorFilter(ContextCompat.getColor(requireContext(), R.color.apagado));
                instructor.setColorFilter(ContextCompat.getColor(requireContext(), R.color.encendido));
                selectedImageView[0] = instructor;

                // Guardar la vista seleccionada en SharedPreferences
                AdPreferenceManager.setSelectedView(requireContext(), "instructor");

                // Luego, inicia la actividad relacionada
                Intent intent = new Intent(getActivity(), AdPInstructor.class);
                startActivity(intent);
            }
        });

        cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedImageView[0].setColorFilter(ContextCompat.getColor(requireContext(), R.color.apagado));
                cliente.setColorFilter(ContextCompat.getColor(requireContext(), R.color.encendido));
                selectedImageView[0] = cliente;

                // Guardar la vista seleccionada en SharedPreferences
                AdPreferenceManager.setSelectedView(requireContext(), "cliente");

                // Luego, inicia la actividad relacionada
                Intent intent = new Intent(getActivity(), AdPCliente.class);
                startActivity(intent);
            }
        });
    }
}
