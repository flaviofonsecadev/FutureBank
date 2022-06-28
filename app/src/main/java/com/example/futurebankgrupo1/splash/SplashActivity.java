package com.example.futurebankgrupo1.splash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.futurebankgrupo1.R;
import com.example.futurebankgrupo1.usuario.LoginActivity;
import com.example.futurebankgrupo1.usuario.Security;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

//    public boolean habilitar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            new Handler().postDelayed(() -> {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }, 2000);
        } else {
            new Handler().postDelayed(() -> {

                startActivity(new Intent(SplashActivity.this, SubSplashActivity2.class));
                finish();
            }, 2000);
        }

//        new Handler().postDelayed(() -> {
//
//            startActivity(new Intent(SplashActivity.this, SubSplashActivity2.class));
//            finish();
//        },2000);

        /*Security security = new Security();
//        habilitar = security.habilitarBiometria(true);
        SharedPreferences preferences = getSharedPreferences("save", MODE_PRIVATE);
        preferences.getBoolean("value", false);

        if (security.habilitarBiometria(true)) {
            new Handler().postDelayed(() -> {
                *//*SharedPreferences preferences = getSharedPreferences("save", MODE_PRIVATE);
                preferences.getBoolean("value", true);*//*
                Toast.makeText(getApplicationContext(), "true", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            },2000);
        }else *//*(security.desabilitarBiometria(true))*//*{
            new Handler().postDelayed(() -> {
                *//*SharedPreferences preferences = getSharedPreferences("save", MODE_PRIVATE);
                preferences.getBoolean("value", false);*//*
                Toast.makeText(getApplicationContext(), "false", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SplashActivity.this, SubSplashActivity2.class));
                finish();
            },2000);
        }*/

    }
}
