package com.example.futurebankgrupo1.pagarfatura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.futurebankgrupo1.R;
import com.example.futurebankgrupo1.databinding.ActivityPagarFaturaConfirmarValorBinding;

public class PagarFaturaConfirmarValor extends AppCompatActivity {

    private ActivityPagarFaturaConfirmarValorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPagarFaturaConfirmarValorBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icBack.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PagarFatura.class);
            startActivity(intent);
        });

        binding.btnPagarFatura.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ComprovanteFatura.class);
            startActivity(intent);
        });
    }
}