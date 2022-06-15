package com.example.futurebankgrupo1.fatura.pagarfatura;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.futurebankgrupo1.MyViewModel;
import com.example.futurebankgrupo1.databinding.ActivityPagarFaturaConfirmarValorBinding;
import com.example.futurebankgrupo1.databinding.ReagendarPagamentosBinding;

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

        binding.tvReagendarFatura.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ReagendarPagamentosBinding.class);
            startActivity(intent);
        });

        binding.btnPagarFatura.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ComprovanteFatura.class);
            startActivity(intent);
        });

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        binding.tvGetValorFatura.setText(String.valueOf(viewModel.exibirValorFatura()));

        int day;
        int month;
        int year;
        SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);
        day = preferences.getInt("chaveDay",0);
        month = preferences.getInt("chaveMonth",0);
        year= preferences.getInt("chaveYear", 0);
        binding.tvAgora.setText(day + "/" +month+"/"+year);


    }
}