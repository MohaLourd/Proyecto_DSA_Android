package com.example.restclientservweb;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

public class UnityPlayerGameActivity2 extends com.unity3d.player.UnityPlayerGameActivity {
//    public void receiveDataFromUnity(String data) {
//        // Manejar los datos recibidos desde Unity
//        android.util.Log.d("UnityPlayerGameActivity", "Dato recibido desde Unity: " + data);
//
//        Toast.makeText(UnityPlayerGameActivity2.this, data+  "data", Toast.LENGTH_LONG).show();
//        System.out.println("Dato recibido desde Unity: " + data);
//
//        Log.println(Log.DEBUG, "UnityPlayerGameActivity", "Dato recibido desde Unity: " + data);
//    }

    public void receiveFromUnity(String str) {

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override public void run() {
        Intent intent = new Intent(UnityPlayerGameActivity2.this, MainActivity.class);
        startActivity(intent);
        }
        }, 3000); // se espera 3 segundos antes de volver a la actividad principal
    }
}


