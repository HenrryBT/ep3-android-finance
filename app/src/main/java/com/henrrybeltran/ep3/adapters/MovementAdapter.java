package com.henrrybeltran.ep3.adapters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.henrrybeltran.ep3.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MovementAdapter extends RecyclerView.Adapter<MovementAdapter.ViewHolder> {
    ArrayList<HashMap<String, String>> arrayList;

    public void setList(ArrayList<HashMap<String, String>> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MovementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movement, parent, false);

        return new MovementAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MovementAdapter.ViewHolder holder, int position) {
        HashMap<String, String> map = arrayList.get(position);

        int sign = Integer.parseInt(Objects.requireNonNull(map.get("movement")));
        double amount = Double.parseDouble(Objects.requireNonNull(map.get("amount")));

        double result = sign * amount;

        if (sign < 0)
            holder.tvAmount.setTextColor(Color.parseColor("#FFCD4F40"));

        holder.tvDate.setText(map.get("date"));
        holder.tvDescription.setText(map.get("description"));
        holder.tvAmount.setText("$ " + result);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate;
        TextView tvDescription;
        TextView tvAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tv_date_mov);
            tvDescription = itemView.findViewById(R.id.tv_description_mov);
            tvAmount = itemView.findViewById(R.id.tv_amount_mov);
        }
    }
}
