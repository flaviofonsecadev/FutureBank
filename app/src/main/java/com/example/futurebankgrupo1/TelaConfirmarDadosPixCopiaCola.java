package com.example.futurebankgrupo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.futurebankgrupo1.databinding.ActivityTelaConfirmarDadosPixCopiaColaBinding;
import com.example.futurebankgrupo1.pagarcompix.TelaPixCopiaCola;

public class TelaConfirmarDadosPixCopiaCola extends AppCompatActivity {

    private ActivityTelaConfirmarDadosPixCopiaColaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTelaConfirmarDadosPixCopiaColaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icBack.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TelaPixCopiaCola.class);
            startActivity(intent);
        });

        /*binding.tvReagendar.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ReagendarPagamentosBinding.class);
            startActivity(intent);
        });*/


        binding.btnConfirmarTransferencia.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PixComprovanteCopiaCola.class);
            startActivity(intent);
        });



    }


}