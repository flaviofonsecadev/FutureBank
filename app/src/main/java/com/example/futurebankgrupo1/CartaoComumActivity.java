package com.example.futurebankgrupo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.futurebankgrupo1.databinding.ActivityCartaoComumBinding;
import com.example.futurebankgrupo1.fatura.FaturaCartao;

public class CartaoComumActivity extends AppCompatActivity {

    private ActivityCartaoComumBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartaoComumBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icBack.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MeusCartoesActivity.class);
            startActivity(intent);
        });

        binding.ivArrowForward2.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FaturaCartao.class);
            startActivity(intent);
        });
    }
}