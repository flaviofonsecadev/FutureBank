package com.example.futurebankgrupo1.fatura.pagarfatura;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.futurebankgrupo1.MyViewModel;
import com.example.futurebankgrupo1.databinding.ActivityPagarFaturaConfirmarValorBinding;

public class PagarFaturaConfirmarValor extends AppCompatActivity {

    private ActivityPagarFaturaConfirmarValorBinding binding;
    private MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPagarFaturaConfirmarValorBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icBack.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PagarFatura.class);
            startActivity(intent);
        });

        binding.btnPagarFatura.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ComprovanteFatura.class);
            startActivity(intent);
        });

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        binding.tvGetValorFatura.setText(String.valueOf(viewModel.exibirValorFatura()));
    }
}