package com.example.futurebankgrupo1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SubSplashAcitivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_splash_acitivty);

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SubSplashAcitivty.this, SubSplashActivity2.class));
                finish();
            }
        },2000);*/
    }
}