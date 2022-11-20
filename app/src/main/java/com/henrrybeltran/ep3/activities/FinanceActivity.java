package com.henrrybeltran.ep3.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.google.android.material.tabs.TabLayoutMediator;
import com.henrrybeltran.ep3.adapters.ViewPagerAdapter;
import com.henrrybeltran.ep3.databinding.ActivityFinanceBinding;
import com.henrrybeltran.ep3.utils.DataPreference;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FinanceActivity extends AppCompatActivity implements View.OnClickListener, TabLayoutMediator.TabConfigurationStrategy {
    private ActivityFinanceBinding binding;
    List<String> tabName = Arrays.asList("Register", "Movements", "Bills", "Gifts");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFinanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        binding.viewPager.setAdapter(adapter);

        FloatingActionButton fab = binding.fab;
        
        fab.setOnClickListener(this);

        new TabLayoutMediator(binding.tabs, binding.viewPager, this).attach();
        
        binding.tvUserName.setText(getNameOfSession());
    }

    @Override
    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
        tab.setText(tabName.get(position));
    }

    @Override
    public void onClick(View view) {
        getSharedPreferences(DataPreference.SAVED_DATA, 0).edit().clear().apply();
        startActivity(new Intent(this, LoginActivity.class));
    }

    private String getNameOfSession() {
        SharedPreferences sharedPreferences = getSharedPreferences(DataPreference.SAVED_DATA, Context.MODE_PRIVATE);
        String data = sharedPreferences.getString(DataPreference.NAME, "nn");

        if (!Objects.equals(data, "nn")) {
            return data;
        } else {
            return "";
        }
    }
}