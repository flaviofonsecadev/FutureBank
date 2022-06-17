package com.example.futurebankgrupo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.futurebankgrupo1.databinding.ActivityTelaConfirmarDadosPixBinding;
import com.example.futurebankgrupo1.pagarcompix.TelaPixCopiaCola;

public class TelaConfirmarDadosPix extends AppCompatActivity {

    private ActivityTelaConfirmarDadosPixBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTelaConfirmarDadosPixBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icBack.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PixTransferirActivity.class);
            startActivity(intent);
        });

        binding.btnConfirmarTransferencia.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TelaConfirmarDadosPix.class);
            startActivity(intent);
        });
    }
}