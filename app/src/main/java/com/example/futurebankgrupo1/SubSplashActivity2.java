package com.example.futurebankgrupo1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SubSplashActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_splash2);

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SubSplashActivity2.this, LoginActivity.class));
                finish();
            }
        },2000);*/
    }
}