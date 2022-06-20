package com.example.futurebankgrupo1.transacoes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.futurebankgrupo1.HomeActivity;
import com.example.futurebankgrupo1.MyViewModel;
import com.example.futurebankgrupo1.databinding.ActivityPixComprovanteCopiaColaBinding;

public class PixComprovanteCopiaCola extends AppCompatActivity {

    private ActivityPixComprovanteCopiaColaBinding binding;
    private MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPixComprovanteCopiaColaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icClear.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        });

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        String mensagemPixCopiaCola;
        SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);
        mensagemPixCopiaCola = preferences.getString("chaveMensagemPix", "");
        binding.tvTipoPgto.setText(mensagemPixCopiaCola);

    }
}