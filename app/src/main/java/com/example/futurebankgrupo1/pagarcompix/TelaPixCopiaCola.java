package com.example.futurebankgrupo1.pagarcompix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.futurebankgrupo1.AreaPixActivity;
import com.example.futurebankgrupo1.HomeActivity;
import com.example.futurebankgrupo1.databinding.ActivityTelaPixCopiaColaBinding;

public class TelaPixCopiaCola extends AppCompatActivity {

    private ActivityTelaPixCopiaColaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTelaPixCopiaColaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icClearCopiaCola.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

//        binding.btnProsseguir.setOnClickListener(v -> {
//            Intent intent = new Intent(getApplicationContext(), TelaConfirmarDadosCopiaCola.class);
//            startActivity(intent);
//        });

        //ola
    }
}