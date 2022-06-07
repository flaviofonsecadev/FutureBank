package com.example.futurebankgrupo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.futurebankgrupo1.databinding.ActivityContaCorrenteBinding;

public class ContaCorrenteActivity extends AppCompatActivity {

    private ActivityContaCorrenteBinding binding;
    private MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContaCorrenteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.ivArrowForward1.setOnClickListener(v -> {
            Intent intent  = new Intent(getApplicationContext(), ContaPoupanca.class);
            startActivity(intent);
        });

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        binding.tvSaldoDisponivelCc.setText(String.valueOf(viewModel.exibirSaldoContaCorrente()));
        binding.tvGetValorGuardadoCorrente.setText(String.valueOf(viewModel.exibirSaldoContaPoupança()));

    }
}