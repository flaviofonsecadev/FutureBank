package com.example.futurebankgrupo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.futurebankgrupo1.databinding.ActivityTelaConfiguracoesBinding;
import com.google.firebase.auth.FirebaseAuth;

public class TelaConfiguracoesActivity extends AppCompatActivity {

    private ActivityTelaConfiguracoesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTelaConfiguracoesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icConfigApp.setOnClickListener(view12 -> {
            Intent intent = new Intent(getApplicationContext(), ConfiguracoesApp.class);
            startActivity(intent);
        });

        binding.tvConfigApp.setOnClickListener(view12 -> {
            Intent intent = new Intent(getApplicationContext(), ConfiguracoesApp.class);
            startActivity(intent);
        });

        binding.ivArrowConfigApp.setOnClickListener(view12 -> {
            Intent intent = new Intent(getApplicationContext(), ConfiguracoesApp.class);
            startActivity(intent);
        });

        binding.tvPerfilUsuario.setOnClickListener(view123 -> {
            Intent intent = new Intent(getApplicationContext(), DadosConta.class);
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
