package com.example.futurebankgrupo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.futurebankgrupo1.databinding.ActivityConfiguracoesAppBinding;
import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracoesApp extends AppCompatActivity {
    ActivityConfiguracoesAppBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfiguracoesAppBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //botão voltar
        binding.icBack.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TelaConfiguracoesActivity.class);
            startActivity(intent);
        });

        //botão Contas
        binding.ivArrowForward.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TiposContasActivity.class);
            startActivity(intent);
        });

        binding.tvContas.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TiposContasActivity.class);
            startActivity(intent);
        });

        binding.ivConfigConta.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TiposContasActivity.class);
            startActivity(intent);
        });



        binding.icConfigSair.setOnClickListener(view1 -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
            startActivity(intent);
        });

        binding.tvConfigSair.setOnClickListener(view1 -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
            startActivity(intent);
        });


    }
}