package com.example.futurebankgrupo1.pagarfatura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.futurebankgrupo1.R;
import com.example.futurebankgrupo1.databinding.ActivityPagarFaturaBinding;
import com.example.futurebankgrupo1.pagarcompix.TelaPagar;

import java.util.zip.Inflater;

public class PagarFatura extends AppCompatActivity {

    private ActivityPagarFaturaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPagarFaturaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icBack.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TelaPagar.class);
            startActivity(intent);
        });

        binding.ivArrowForward.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PagarFaturaConfirmarValor.class);
            startActivity(intent);
        });
    }
}