package com.henrrybeltran.ep3.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.henrrybeltran.ep3.activities.FinanceActivity;
import com.henrrybeltran.ep3.databinding.ActivityLoginBinding;
import com.henrrybeltran.ep3.utils.DataPreference;
import com.henrrybeltran.ep3.utils.Route;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Objects;

public class LoginController {
    private HashMap<String, String> resMap = new HashMap<>();
    private int count = 0;

    public void loginUserRequest(String email, String password, Activity context, ActivityLoginBinding binding) {
        if (Objects.equals(email, "") && Objects.equals(password, "")) {
            Toast.makeText(context, "Inserta tus datos", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = Route.path + "login?email=" + email + "&password=" + password;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("DATA", response);
                        writeResult(response, context, binding);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR_DATA", error.toString());
            }
        });

        queue.add(stringRequest);
    }

    private void writeResult(String response, Activity context, ActivityLoginBinding binding) {
        try {
            JSONObject object = new JSONObject(response);

            String message = object.getString("message");
            String name = (message.equals("0")) ? object.getString("name") : "";

            loginAuthentication(message, name, context, binding);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loginAuthentication(String message, String name, Activity context, ActivityLoginBinding binding) {
        exitAppAfterThreeAttempts(message, context);
        validateAttempts(message, name, context, binding);
    }

    private void exitAppAfterThreeAttempts(String message, Activity context) {
        int attempts = Objects.equals(message, "0") ? count : count++;
        if (attempts > 2) context.finish();
    }

    private void validateAttempts(String message, String name, Activity context, ActivityLoginBinding binding) {
        if (Objects.equals(message, "0")) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(DataPreference.SAVED_DATA, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(DataPreference.NAME, name);
            editor.apply();

            Toast.makeText(context, "Bienvenido " + name, Toast.LENGTH_LONG).show();
            verifySaveSession(message, context, binding);
            context.startActivity(new Intent(context, FinanceActivity.class));
        } else {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    private void verifySaveSession(String message, Activity context, ActivityLoginBinding binding) {
        if (binding.swKeepLogin.isChecked()) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(DataPreference.SAVED_DATA, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(DataPreference.MESSAGE, message);
            editor.apply();
        }
    }
}
