package com.example.fitnes_hanma.Instructor.calendario;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnes_hanma.R;

import java.time.LocalDate;
import java.util.ArrayList;
public class Calendario_Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final ArrayList<LocalDate> days;
    public final View parentView;
    public final TextView dayOfMonth;
    private final Calendario_adaptador.OnItemListener onItemListener;
    public Calendario_Holder(View itemView, Calendario_adaptador.OnItemListener onItemListener, ArrayList<LocalDate> days){
        super(itemView);
        parentView = itemView.findViewById(R.id.parentView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
        this.days = days;
    }

    @Override
    public void onClick(View view)
    {
        onItemListener.onItemClick(getAdapterPosition(), days.get(getAdapterPosition()));
    }
}
