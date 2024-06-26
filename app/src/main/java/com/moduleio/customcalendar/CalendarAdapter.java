package com.moduleio.customcalendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder>
{
    private final ArrayList<String> daysOfMonth;
    private final OnItemListener onItemListener;

    public CalendarAdapter(ArrayList<String> daysOfMonth, OnItemListener onItemListener)
    {
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666666);

        return new CalendarViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position)
    {
        String dayText = daysOfMonth.get(position);
        holder.dayOfMonth.setText(dayText);

        if (dayText.isEmpty()) {
            holder.itemView.setClickable(false);
            holder.greenDot.setVisibility(View.INVISIBLE);
            holder.blueDot.setVisibility(View.INVISIBLE);
            holder.yellowDot.setVisibility(View.INVISIBLE);
        } else {
            holder.itemView.setClickable(true);

            if (position % 7 == 1) { // Monday
                holder.greenDot.setVisibility(View.VISIBLE);
            } else {
                holder.greenDot.setVisibility(View.INVISIBLE);
            }
            if (position % 7 == 3) { // Thursday
                holder.blueDot.setVisibility(View.VISIBLE);
                holder.yellowDot.setVisibility(View.VISIBLE);
            } else {
                holder.blueDot.setVisibility(View.INVISIBLE);
                holder.yellowDot.setVisibility(View.INVISIBLE);
            }

//             Check if the position corresponds to the current day
//            if (LocalDate.now().getDayOfMonth() == Integer.parseInt(dayText)) {
//                holder.currentDay();
//            }


        }
    }

    @Override
    public int getItemCount()
    {
        return daysOfMonth.size();
    }

    public interface  OnItemListener
    {
        void onItemClick(int position, String dayText);
    }
}