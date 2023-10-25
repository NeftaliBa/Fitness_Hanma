package com.example.fitnes_hanma.Admin.Principal;

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
        return inflater.inflate(R.layout.fragment_adnavbar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView instructor = view.findViewById(R.id.instructor);
        ImageView clases = view.findViewById(R.id.clases);
        ImageView cliente = view.findViewById(R.id.cliente);
        ImageView personal = view.findViewById(R.id.personal);

        clases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), AdClases.class);
                    startActivity(intent);
                }
        });
        instructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AdInstructor.class);
                startActivity(intent);
            }
        });
        cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), AdCliente.class);
                    startActivity(intent);
                }
        });

        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), AdPersonal.class);
                    startActivity(intent);
                }
        });
    }
}
