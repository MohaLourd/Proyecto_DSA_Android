package com.example.restclientservweb;

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

        Toast.makeText(UnityPlayerGameActivity2.this, "en principio ha cargado bien el str" + str, Toast.LENGTH_LONG).show();


    }
}


