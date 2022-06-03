package com.example.futurebankgrupo1.pagarfatura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.futurebankgrupo1.HomeActivity;
import com.example.futurebankgrupo1.R;
import com.example.futurebankgrupo1.databinding.ActivityComprovanteFaturaBinding;

public class ComprovanteFatura extends AppCompatActivity {

    private ActivityComprovanteFaturaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityComprovanteFaturaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icClear.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });
    }
}