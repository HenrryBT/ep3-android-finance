package com.henrrybeltran.ep3.ui.fragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.henrrybeltran.ep3.R;
import com.henrrybeltran.ep3.adapters.BillsAdapter;
import com.henrrybeltran.ep3.adapters.MovementAdapter;
import com.henrrybeltran.ep3.model.SQLiteRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class BillsFragment extends Fragment {
    private BillsAdapter billsAdapter;
    private RecyclerView rvBills;
    private final ArrayList<HashMap<String, String>> billList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        billsAdapter = new BillsAdapter();
        return inflater.inflate(R.layout.fragment_bills, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvBills = view.findViewById(R.id.rv_bills);
        readData();
    }

    private void readData() {
        SQLiteRepository liteRepository = new SQLiteRepository(getActivity());
        Cursor cursor = liteRepository.movementSelect(liteRepository);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> map = new HashMap<>();
                    String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
                    String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                    String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                    String amount = cursor.getString(cursor.getColumnIndexOrThrow("amount"));
                    String movement = cursor.getString(cursor.getColumnIndexOrThrow("movement"));

                    map.put("id", id);
                    map.put("date", date);
                    map.put("description", description);
                    map.put("amount", amount);
                    map.put("movement", movement);

                    int sign = Integer.parseInt(Objects.requireNonNull(map.get("movement")));

                    if (sign > 0) continue;

                    billList.add(map);
                } while (cursor.moveToNext());
            }
        }

        billsAdapter.setList(billList);
        rvBills.setAdapter(billsAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvBills.setLayoutManager(linearLayoutManager);
    }
}