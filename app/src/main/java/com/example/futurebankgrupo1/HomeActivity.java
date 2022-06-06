package com.example.futurebankgrupo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.futurebankgrupo1.databinding.ActivityHomeBinding;
import com.example.futurebankgrupo1.pagarcompix.TelaPagar;
import com.example.futurebankgrupo1.pagarfatura.FaturaCartao;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        //botão home
        binding.icMenuHome.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TelaConfiguracoesActivity.class);
            startActivity(intent);
        });


        //botões pix
        binding.ivPix.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AreaPixActivity.class);
            startActivity(intent);
        });

        binding.tvPix.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AreaPixActivity.class);
            startActivity(intent);
        });

        binding.constraintPix.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AreaPixActivity.class);
            startActivity(intent);
        });


        //botões pagar
        binding.ivPagar.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TelaPagar.class);
            startActivity(intent);
        });

        binding.tvPagar.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TelaPagar.class);
            startActivity(intent);
        });

        binding.constraintPagar.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TelaPagar.class);
            startActivity(intent);
        });


        //botões transferir
        binding.ivTransferirHome.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PixTransferirActivity.class);
            startActivity(intent);
        });

        binding.tvTransferirHome.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PixTransferirActivity.class);
            startActivity(intent);
        });

        binding.constraintTransferir.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PixTransferirActivity.class);
            startActivity(intent);
        });


        //botões cobrar
        binding.ivCobrarHome.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CobrarPix.class);
            startActivity(intent);
        });

        binding.tvCobrarHome.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CobrarPix.class);
            startActivity(intent);
        });

        binding.constraintCobrar.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CobrarPix.class);
            startActivity(intent);
        });


        //botões cartões
        binding.ivCartoesHome.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CartaoComum.class);
            startActivity(intent);
        });

        binding.tvCartoesHome.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CartaoComum.class);
            startActivity(intent);
        });

        binding.constraintCartoes.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CartaoComum.class);
            startActivity(intent);
        });


        //botoes contas
        binding.ivContas.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TiposContasActivity.class);
            startActivity(intent);
        });

        binding.tvContas.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TiposContasActivity.class);
            startActivity(intent);
        });

        binding.constraintContas.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TiposContasActivity.class);
            startActivity(intent);
        });

        //fatura cartão de credito
        binding.tvCartaoCredito.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FaturaCartao.class);
            startActivity(intent);
        });

        binding.tvFaturaAtual.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FaturaCartao.class);
            startActivity(intent);
        });

        binding.tvLimiteDisponivel.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FaturaCartao.class);
            startActivity(intent);
        });

        binding.tvGetValorLimiteDisponivel.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FaturaCartao.class);
            startActivity(intent);
        });

        binding.tvGetValorFaturaAtual.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FaturaCartao.class);
            startActivity(intent);
        });

        binding.ivArrowForward1.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FaturaCartao.class);
            startActivity(intent);
        });


        //Seguro cartão
        binding.ivSeguroCartao.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SeguroCartaoActivity.class);
            startActivity(intent);
        });

        binding.tvSeguroCartao.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SeguroCartaoActivity.class);
            startActivity(intent);
        });

        binding.constraintSeguroCatao.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SeguroCartaoActivity.class);
            startActivity(intent);
        });


        //seguro de vida
        binding.constraintSeguroVida.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SeguroVida.class);
            startActivity(intent);
        });

        binding.ivSeguroVida.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SeguroVida.class);
            startActivity(intent);
        });

        binding.tvSeguroVida.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SeguroVida.class);
            startActivity(intent);
        });


        //acessar extrato
        binding.tvOlaCliente.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ContaCorrenteActivity.class);
            startActivity(intent);
        });

        binding.tvContaCorrente.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ContaCorrenteActivity.class);
            startActivity(intent);
        });

        binding.tvSaldoDisponivel.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ContaCorrenteActivity.class);
            startActivity(intent);
        });

        binding.ivArrowForward1.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ContaCorrenteActivity.class);
            startActivity(intent);
        });

    }
}















































































































































































































































































