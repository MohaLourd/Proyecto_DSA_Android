package com.example.restclientservweb;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.unity3d.player.UnityPlayerGameActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ApiService apiService;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Base_Theme_Restclientservweb);
        // Clear the splash screen background
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(null);
        setContentView(R.layout.activity_main);

        Button buttonLogin = findViewById(R.id.button_login);
        Button buttonRegister = findViewById(R.id.button_register);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://147.83.7.203:80/dsaApp/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
        sharedPreferences = getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);
        checkLoginStatus();

        buttonLogin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        buttonRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

    }

    private void checkLoginStatus() {
        String username = sharedPreferences.getString("username", null);
        String idUser = sharedPreferences.getString("idUser", null);
        if (username != null) {
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("idUser", idUser);
            startActivity(intent);
            finish();
        }
    }


}