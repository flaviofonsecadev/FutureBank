package com.example.futurebankgrupo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.futurebankgrupo1.databinding.ActivityTelaConfirmarDadosCobrarPixBinding;
import com.example.futurebankgrupo1.databinding.ActivityTelaConfirmarDadosCopiaColaBinding;
import com.example.futurebankgrupo1.pagarcompix.PixComprovanteCopiaCola;
import com.example.futurebankgrupo1.pagarcompix.TelaPixCopiaCola;

public class TelaConfirmarDadosCobrarPix extends AppCompatActivity {

    private ActivityTelaConfirmarDadosCobrarPixBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTelaConfirmarDadosCobrarPixBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icBack.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CobrarPix.class);
            startActivity(intent);
        });

        binding.btnGerarQrCode.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CobrarQrCodeActivity.class);
            startActivity(intent);
        });

        /*binding.btnConfirmarTransferencia.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),)
        });*/
    }
}