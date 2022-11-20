package com.henrrybeltran.ep3.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.henrrybeltran.ep3.controllers.LoginController;
import com.henrrybeltran.ep3.databinding.ActivityLoginBinding;
import com.henrrybeltran.ep3.utils.DataPreference;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityLoginBinding binding;
    private LoginController login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        checkSavedSession();
        login = new LoginController();

        binding.btnAuthenticateLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String email = binding.etEmailForm.getText().toString();
        String password = binding.etPasswordForm.getText().toString();

        login.loginUserRequest(email, password, this, binding);
    }

    private void checkSavedSession() {
        SharedPreferences sharedPreferences = getSharedPreferences(DataPreference.SAVED_DATA, Context.MODE_PRIVATE);
        String data = sharedPreferences.getString(DataPreference.MESSAGE, "nn");

        if (!Objects.equals(data, "nn")) {
            startActivity(new Intent(this, FinanceActivity.class));
        }
    }
}