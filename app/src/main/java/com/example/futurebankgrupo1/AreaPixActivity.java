package com.example.futurebankgrupo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.futurebankgrupo1.databinding.ActivityAreaPixBinding;
import com.example.futurebankgrupo1.databinding.ActivityAreaPixBinding;
import com.example.futurebankgrupo1.databinding.ActivityTelaPixCopiaColaBinding;
import com.example.futurebankgrupo1.pagarcompix.TelaPixCopiaCola;

public class AreaPixActivity extends AppCompatActivity {

    private ActivityAreaPixBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_pix);

        binding = ActivityAreaPixBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //botão voltar
        binding.ivBack.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        //botão voltar/pix copia e cola
        binding.ivBack.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AreaPixActivity.class);
            startActivity(intent);
        });


        //transferir
        binding.ivTransferir.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PixTransferirActivity.class);
            startActivity(intent);
        });

        //copia e cola
        binding.ivCopiaCola.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TelaPixCopiaCola.class);
            startActivity(intent);
        });



    }
}