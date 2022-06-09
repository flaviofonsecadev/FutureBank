package com.example.futurebankgrupo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.futurebankgrupo1.databinding.ActivityCobrarPixBinding;
import com.example.futurebankgrupo1.pagarcompix.TelaConfirmarDadosCopiaCola;

public class CobrarPix extends AppCompatActivity {

    private ActivityCobrarPixBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCobrarPixBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnProximaTela.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TelaConfirmarDadosCobrarPix.class);
            startActivity(intent);
        });

        binding.icClear.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AreaPixActivity.class);
            startActivity(intent);
        });
    }
}