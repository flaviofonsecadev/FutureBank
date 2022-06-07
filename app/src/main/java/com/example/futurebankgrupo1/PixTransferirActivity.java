package com.example.futurebankgrupo1;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import com.example.futurebankgrupo1.databinding.ActivityPixTransferirBinding;

import java.util.Scanner;

public class PixTransferirActivity extends AppCompatActivity {

    private ActivityPixTransferirBinding binding;

    private MyViewModel viewModel;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pix_transferir);
        binding = ActivityPixTransferirBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //botão voltar
        binding.icClear.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),PixTransferirActivity.class);
            startActivity(intent);
        });
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        binding.tvSaldoDisponivelPix.setText(String.valueOf(viewModel.exibirSaldoContaCorrente()));
    }
}