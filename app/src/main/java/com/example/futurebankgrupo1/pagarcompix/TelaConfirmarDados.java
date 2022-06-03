package com.example.futurebankgrupo1.pagarcompix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.futurebankgrupo1.R;
import com.example.futurebankgrupo1.databinding.ActivityTelaConfirmarDadosBinding;

public class TelaConfirmarDados extends AppCompatActivity {

    private ActivityTelaConfirmarDadosBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTelaConfirmarDadosBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icBack.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TelaPixCopiaCola.class);
            startActivity(intent);
        });

        /*binding.btnConfirmarTransferencia.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),)
        });*/
    }
}