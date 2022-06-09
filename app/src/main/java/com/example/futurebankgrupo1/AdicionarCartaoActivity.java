package com.example.futurebankgrupo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.futurebankgrupo1.databinding.ActivityAdicionarCartaoBinding;
import com.example.futurebankgrupo1.fatura.pagarfatura.PagarFatura;
import com.example.futurebankgrupo1.fatura.pagarfatura.PagarFaturaConfirmarValor;

import java.util.Random;

public class AdicionarCartaoActivity extends AppCompatActivity {

    private ActivityAdicionarCartaoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdicionarCartaoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icBack.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MeusCartoesActivity.class);
            startActivity(intent);
        });

        binding.ivCardRosa.setOnClickListener(v -> {
            gerarNumeroCartao();
            gerarDataValidade();
            gerarCvv();
        });
    }

    public void gerarNumeroCartao() {
        Random rand = new Random();
        String numeroCartao = "";

        for (int i = 0; i < 4; i++) {
            int value = rand.nextInt(9999);
            numeroCartao += String.valueOf(value) + " ";
        }
        binding.edtNumeroCartao.setText(numeroCartao);
    }

    public void gerarDataValidade() {
        Random rand = new Random();
        String dataValidade = "";

        int mes = rand.nextInt(11) + 1;
        int ano = rand.nextInt(10) + 2022;
        dataValidade = String.valueOf(mes) + "/" + String.valueOf(ano);
        binding.edtValidade.setText(dataValidade);
    }

    public void gerarCvv() {
        Random rand = new Random();
        String cvv = "";

        int codigo = rand.nextInt(100) + 100;
        cvv = String.valueOf(codigo);
        binding.edtCvv.setText(cvv);
    }
}