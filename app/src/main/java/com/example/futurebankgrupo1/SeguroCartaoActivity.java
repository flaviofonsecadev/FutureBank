package com.example.futurebankgrupo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.futurebankgrupo1.databinding.ActivitySeguroCartaoBinding;
import com.example.futurebankgrupo1.databinding.ActivitySeguroVidaBinding;
import com.example.futurebankgrupo1.pagarcompix.TelaPagar;

public class SeguroCartaoActivity extends AppCompatActivity {

    private ActivitySeguroCartaoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySeguroCartaoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icClear.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        binding.cbStandard.setOnClickListener(v -> {
            binding.cbPlus.setChecked(false);
            binding.cbPremium.setChecked(false);
        });

        binding.cbPlus.setOnClickListener(v -> {
            binding.cbStandard.setChecked(false);
            binding.cbPremium.setChecked(false);
        });

        binding.cbPremium.setOnClickListener(v-> {
            binding.cbStandard.setChecked(false);
            binding.cbPlus.setChecked(false);
        });


        binding.btnContratarSeguroCartao.setOnClickListener(v -> {
            if(binding.cbStandard.isChecked() || binding.cbPlus.isChecked() || binding.cbPremium.isChecked()) {
                Toast.makeText(this, "Plano contratado!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Nenhum plano selecionado!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}