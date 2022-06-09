package com.example.futurebankgrupo1.pagarcompix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.futurebankgrupo1.databinding.ActivityTelaConfirmarDadosCopiaColaBinding;

public class TelaConfirmarDadosCopiaCola extends AppCompatActivity {

    private ActivityTelaConfirmarDadosCopiaColaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTelaConfirmarDadosCopiaColaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icBack.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TelaPixCopiaCola.class);
            startActivity(intent);
        });

        binding.btnConfirmarTransferencia.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PixComprovanteCopiaCola.class);
            startActivity(intent);
        });

        /*binding.btnConfirmarTransferencia.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),)
        });*/
    }
}