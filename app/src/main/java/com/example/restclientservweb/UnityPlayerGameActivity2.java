package com.example.restclientservweb;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UnityPlayerGameActivity2 extends com.unity3d.player.UnityPlayerGameActivity {

    private ApiService apiService;

    public void receiveFromUnity(String str) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/dsaApp/") // Cambiado a 10.0.2.2 para el emulador
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);


        String[] split = str.split(",");
        int dinero = Integer.parseInt(split[0]);
        int puntos = Integer.parseInt(split[1]);

        String username = getIntent().getStringExtra("username");
        SendDatatoServer(username, dinero, puntos);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override public void run() {
        Intent intent = new Intent(UnityPlayerGameActivity2.this, MenuActivity.class);
       // intent.putExtra("dinero", dinero);
        //intent.putExtra("puntos", puntos);
        startActivity(intent);
        }
        }, 3000); // se espera 3 segundos antes de volver a la actividad principal
    }

 private  void SendDatatoServer(String username, int dinero, int puntos)
     {
         userPruebaUnity userPruebaUnity = new userPruebaUnity(username, puntos, dinero);
         Call<userPruebaUnity> call = apiService.registerPartida(userPruebaUnity);

         call.enqueue(new Callback<userPruebaUnity>() {
             @Override
             public void onResponse(Call<userPruebaUnity> call, Response<userPruebaUnity> response) {
                 if (response.isSuccessful()) {
                     Toast.makeText(UnityPlayerGameActivity2.this, "is succesful", Toast.LENGTH_SHORT).show();

                 } else {

                     Toast.makeText(UnityPlayerGameActivity2.this, "else +" + response.body(), Toast.LENGTH_SHORT).show();
                 }
             }

             @Override
             public void onFailure(Call<userPruebaUnity> call, Throwable t) {
                 Toast.makeText(UnityPlayerGameActivity2.this, "on faiulure", Toast.LENGTH_SHORT).show();
             }
         });
     }



}


