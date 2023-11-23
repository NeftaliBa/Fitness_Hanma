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
        ImageView principal = view.findViewById(R.id.principal);
        ImageView messages = view.findViewById(R.id.messages);



        principal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Luego, inicia la actividad relacionada
                Intent intent = new Intent(getActivity(), principal.class);
                startActivity(intent);
            }
        });
        servicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Luego, inicia la actividad relacionada
                Intent intent = new Intent(getActivity(), servicios.class);
                startActivity(intent);
            }
        });

        horarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Luego, inicia la actividad relacionada
                Intent intent = new Intent(getActivity(), cl_horarios.class);
                startActivity(intent);
            }
        });

        asis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Luego, inicia la actividad relacionada
                Intent intent = new Intent(getActivity(), asistenciaQR.class);
                startActivity(intent);
            }
        });
        messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Luego, inicia la actividad relacionada
                Intent intent = new Intent(getActivity(), messagesCl.class);
                startActivity(intent);
            }
        });
    }
}
