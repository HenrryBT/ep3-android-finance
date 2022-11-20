package com.henrrybeltran.ep3.fragments;

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
import android.widget.Toast;

import com.henrrybeltran.ep3.R;
import com.henrrybeltran.ep3.adapters.MovementAdapter;
import com.henrrybeltran.ep3.model.SQLiteRepository;

import java.util.ArrayList;
import java.util.HashMap;

public class MovementFragment extends Fragment {
    private static boolean isMovementReady = false;

    private MovementAdapter movementAdapter;
    private RecyclerView rvMovement;
    private SQLiteRepository liteRepository;

    private final ArrayList<HashMap<String, String>> movesList = new ArrayList<>();

    public static boolean isMovementReady() {
        return isMovementReady;
    }

    public MovementFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movement, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        isMovementReady = true;
        rvMovement = view.findViewById(R.id.rv_movement);
        liteRepository = new SQLiteRepository(getActivity());
        movementAdapter = new MovementAdapter();

        readData();
    }

    public void readData() {
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

                    movesList.add(map);
                } while (cursor.moveToNext());
            }
        }
        movementAdapter.setList(movesList);
        rvMovement.setAdapter(movementAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvMovement.setLayoutManager(linearLayoutManager);
    }

    public void updateList() {
        Cursor cursor = liteRepository.movementSelect(liteRepository);
        movesList.clear();
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

                    movesList.add(map);
                } while (cursor.moveToNext());
            }
        }

        movementAdapter.setList(movesList);
        rvMovement.setAdapter(movementAdapter);
    }
}