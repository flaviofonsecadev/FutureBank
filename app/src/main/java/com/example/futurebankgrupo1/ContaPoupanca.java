package com.example.futurebankgrupo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.futurebankgrupo1.databinding.ActivityContaCorrenteBinding;
import com.example.futurebankgrupo1.databinding.ActivityContaPoupancaBinding;

public class ContaPoupanca extends AppCompatActivity {

    private ActivityContaPoupancaBinding binding;
    private MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContaPoupancaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        binding.tvValorGuardado.setText(String.valueOf(viewModel.exibirSaldoContaPoupança()));
        binding.tvGetValorContaCorrente.setText(String.valueOf(viewModel.exibirSaldoContaCorrente()));


    }
}