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

        ImageView instructor = view.findViewById(R.id.instruc);
        ImageView servicio = view.findViewById(R.id.serv);
        ImageView cliente = view.findViewById(R.id.clien);
        ImageView personal = view.findViewById(R.id.perso);

        // Recuperar el estado de selección desde SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("button_selection", Context.MODE_PRIVATE);
        final int[] selectedButton = {sharedPreferences.getInt("selected_button", R.id.instruc)};

// Establecer los fondos según el estado guardado
        instructor.setBackgroundResource(selectedButton[0] == R.id.instruc ? R.drawable.testbacknull : R.drawable.adminbackfull);
        servicio.setBackgroundResource(selectedButton[0] == R.id.serv ? R.drawable.testbacknull : R.drawable.adminbackfull);
        cliente.setBackgroundResource(selectedButton[0] == R.id.clien ? R.drawable.testbacknull : R.drawable.adminbackfull);
        personal.setBackgroundResource(selectedButton[0] == R.id.perso ? R.drawable.testbacknull : R.drawable.adminbackfull);

        instructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedButton[0] != R.id.instruc) {
                    instructor.setBackgroundResource(R.drawable.testbacknull);
                    servicio.setBackgroundResource(R.drawable.adminbackfull);
                    cliente.setBackgroundResource(R.drawable.adminbackfull);
                    personal.setBackgroundResource(R.drawable.adminbackfull);

                    // Guardar el estado de selección en SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("selected_button", R.id.instruc);
                    editor.apply();

                    selectedButton[0] = R.id.instruc;
                    Intent intent = new Intent(getActivity(), AdInstructor.class);
                    startActivity(intent);
                }
            }
        });

        servicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedButton[0] != R.id.serv) {
                    servicio.setBackgroundResource(R.drawable.testbacknull);
                    instructor.setBackgroundResource(R.drawable.adminbackfull);
                    cliente.setBackgroundResource(R.drawable.adminbackfull);
                    personal.setBackgroundResource(R.drawable.adminbackfull);

                    // Guardar el estado de selección en SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("selected_button", R.id.serv);
                    editor.apply();

                    selectedButton[0] = R.id.serv;
                    Intent intent = new Intent(getActivity(), AdClases.class);
                    startActivity(intent);
                }
            }
        });

        cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedButton[0] != R.id.clien) {
                    cliente.setBackgroundResource(R.drawable.testbacknull);
                    servicio.setBackgroundResource(R.drawable.adminbackfull);
                    instructor.setBackgroundResource(R.drawable.adminbackfull);
                    personal.setBackgroundResource(R.drawable.adminbackfull);

                    // Guardar el estado de selección en SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("selected_button", R.id.clien);
                    editor.apply();

                    selectedButton[0] = R.id.clien;
                    Intent intent = new Intent(getActivity(), AdCliente.class);
                    startActivity(intent);
                }
            }
        });

        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedButton[0] != R.id.perso) {
                    personal.setBackgroundResource(R.drawable.testbacknull);
                    servicio.setBackgroundResource(R.drawable.adminbackfull);
                    cliente.setBackgroundResource(R.drawable.adminbackfull);
                    instructor.setBackgroundResource(R.drawable.adminbackfull);

                    // Guardar el estado de selección en SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("selected_button", R.id.perso);
                    editor.apply();

                    selectedButton[0] = R.id.perso;
                    Intent intent = new Intent(getActivity(), AdPersonal.class);
                    startActivity(intent);
                }
            }
        });

    }
}
