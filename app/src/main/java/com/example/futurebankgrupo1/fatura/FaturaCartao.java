package com.example.futurebankgrupo1.fatura;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.ExtractedText;
import android.widget.TextView;

import com.example.futurebankgrupo1.HomeActivity;
import com.example.futurebankgrupo1.MyViewModel;
import com.example.futurebankgrupo1.databinding.ActivityFaturaCartaoBinding;
import com.example.futurebankgrupo1.fatura.pagarfatura.PagarFatura;

public class FaturaCartao extends AppCompatActivity {

    private ActivityFaturaCartaoBinding binding;
    private MyViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFaturaCartaoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.icClear.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        binding.tvPagarFaturaAtual.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PagarFatura.class);
            startActivity(intent);
        });

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);


        /*binding.btnEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvValorAtual.setText(String.valueOf(viewModel.exibirValorFatura()));

                //viewModel.exibirValorFatura();
            }
        });*/

        binding.tvValorAtual.setText(String.valueOf(viewModel.exibirValorFatura()));
        binding.tvGetLimite.setText(String.valueOf(viewModel.exibirLimite()));




    }
}