package com.henrrybeltran.ep3.adapters;

import android.annotation.SuppressLint;
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

public class BillsAdapter extends RecyclerView.Adapter<BillsAdapter.ViewHolder> {
    ArrayList<HashMap<String, String>> arrayList;

    public void setList(ArrayList<HashMap<String, String>> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public BillsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bills, parent, false);

        return new BillsAdapter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BillsAdapter.ViewHolder holder, int position) {
        HashMap<String, String> map = arrayList.get(position);

        int sign = Integer.parseInt(Objects.requireNonNull(map.get("movement")));
        double amount = Double.parseDouble(Objects.requireNonNull(map.get("amount")));

        double result = sign * amount;

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

            tvDate = itemView.findViewById(R.id.tv_date_bills);
            tvDescription = itemView.findViewById(R.id.tv_description_bills);
            tvAmount = itemView.findViewById(R.id.tv_amount_bills);
        }
    }
}
