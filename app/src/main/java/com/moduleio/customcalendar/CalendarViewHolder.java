package com.moduleio.customcalendar;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public final TextView dayOfMonth;
    private final View backgroundCircle;
    private final CalendarAdapter.OnItemListener onItemListener;
    private static View previousView = null;

    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener)
    {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        backgroundCircle = itemView.findViewById(R.id.backgroundCircle);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if(previousView != null){
            previousView.findViewById(R.id.backgroundCircle).setVisibility(View.INVISIBLE);
            ((TextView) previousView.findViewById(R.id.cellDayText)).setTextColor(Color.BLACK);
        }
        backgroundCircle.setVisibility(View.VISIBLE);
        dayOfMonth.setTextColor(Color.WHITE);
        onItemListener.onItemClick(getAdapterPosition(), (String) dayOfMonth.getText());
        previousView = view;
    }
}