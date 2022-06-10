package com.example.futurebankgrupo1.recycler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

import com.example.futurebankgrupo1.HomeActivity;
import com.example.futurebankgrupo1.databinding.ActivityRecargaCelularBinding;
import com.example.futurebankgrupo1.databinding.ActivityTelaPagarComPixBinding;
import com.example.futurebankgrupo1.pagarcompix.TelaPagar;

public class RecargaTela extends AppCompatActivity {
    private ActivityRecargaCelularBinding Binding;

    ActivityRecargaCelularBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecargaCelularBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //voltar
        binding.icBackMenu.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });
       }
    }