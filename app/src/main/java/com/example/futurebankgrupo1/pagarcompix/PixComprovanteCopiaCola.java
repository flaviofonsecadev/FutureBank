package com.example.futurebankgrupo1.pagarcompix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.futurebankgrupo1.HomeActivity;
import com.example.futurebankgrupo1.MyViewModel;
import com.example.futurebankgrupo1.databinding.ActivityPixComprovanteCopiaColaBinding;
import com.example.futurebankgrupo1.databinding.ActivityTelaPixCopiaColaBinding;

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
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

    }
}