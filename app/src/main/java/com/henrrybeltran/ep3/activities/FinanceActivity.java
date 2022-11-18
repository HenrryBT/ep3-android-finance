package com.henrrybeltran.ep3.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.henrrybeltran.ep3.R;
import com.henrrybeltran.ep3.ui.main.SectionsPagerAdapter;
import com.henrrybeltran.ep3.databinding.ActivityFinanceBinding;
import com.henrrybeltran.ep3.utils.DataPreference;

import java.util.Objects;

public class FinanceActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityFinanceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFinanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = binding.fab;

        binding.tvUserName.setText(getNameOfSession());

        fab.setOnClickListener(this);
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