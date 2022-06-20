package com.example.futurebankgrupo1.seguros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.futurebankgrupo1.HomeActivity;
import com.example.futurebankgrupo1.databinding.ActivityHomeBinding;
import com.example.futurebankgrupo1.databinding.ActivitySeguroVidaBinding;

public class SeguroVida extends AppCompatActivity {

    private ActivitySeguroVidaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySeguroVidaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icClear.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        binding.cbIndividual.setOnClickListener(v -> {
            binding.cbPessoas.setChecked(false);
            binding.cbFamilia.setChecked(false);
        });

        binding.cbPessoas.setOnClickListener(v -> {
            binding.cbIndividual.setChecked(false);
            binding.cbFamilia.setChecked(false);
        });

        binding.cbFamilia.setOnClickListener(v-> {
            binding.cbIndividual.setChecked(false);
            binding.cbPessoas.setChecked(false);
        });


        binding.btnContratarSeguroVida.setOnClickListener(v -> {
            if(binding.cbIndividual.isChecked() || binding.cbPessoas.isChecked() || binding.cbFamilia.isChecked()) {
                Toast.makeText(this, "Plano contratado!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Nenhum plano selecionado!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}