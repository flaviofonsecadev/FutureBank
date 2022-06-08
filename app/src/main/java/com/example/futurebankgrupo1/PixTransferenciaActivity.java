package com.example.futurebankgrupo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.futurebankgrupo1.databinding.ActivityPixTransferenciaBinding;
import com.example.futurebankgrupo1.databinding.ActivityPixTransferirBinding;

public class PixTransferenciaActivity extends AppCompatActivity {

    private ActivityPixTransferenciaBinding binding;
    private MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pix_transferencia);

        binding =  ActivityPixTransferenciaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //botão voltar
        binding.icClear.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),PixTransferirActivity.class);
            startActivity(intent);
        });
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        binding.tvAgRecebedor.setText(String.valueOf(viewModel.exibirSaldoContaCorrente()));
    }
}