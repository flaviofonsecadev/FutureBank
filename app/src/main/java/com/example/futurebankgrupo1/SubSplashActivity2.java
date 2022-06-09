package com.example.futurebankgrupo1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SubSplashActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_splash2);

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SubSplashActivity2.this, CadastroActivity.class));
            finish();
        },2000);
    }
}