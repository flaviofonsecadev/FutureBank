package com.example.futurebankgrupo1.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.futurebankgrupo1.R;
import com.example.futurebankgrupo1.configuracoes.TelaConfiguracoesActivity;
import com.example.futurebankgrupo1.databinding.ActivitySecurityBinding;

public class Security extends AppCompatActivity {

    private ActivitySecurityBinding binding;
    Switch switchCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecurityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        switchCompat = findViewById(R.id.switch_biometria);

        try {
            SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
            switchCompat.setChecked(sharedPreferences.getBoolean("value", false));
        } catch (Exception e) {
            e.printStackTrace();
        }

        switchCompat.setOnClickListener(v -> {
            if (switchCompat.isChecked()) {
                SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                editor.putBoolean("value", true);
                editor.apply();
                switchCompat.setChecked(true);
                Toast.makeText(getApplicationContext(), "Autenticação com biometria ativada!", Toast.LENGTH_SHORT).show();
            }else {
                SharedPreferences.Editor editor = getSharedPreferences("save", MODE_PRIVATE).edit();
                editor.putBoolean("value", false);
                editor.apply();
                switchCompat.setChecked(false);
                Toast.makeText(getApplicationContext(), "Autenticação com biometria desativada!", Toast.LENGTH_SHORT).show();
            }
        });

        binding.icBack.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TelaConfiguracoesActivity.class);
            startActivity(intent);
        });

        binding.ivArrowForward.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ResetarSenha.class));
        });
    }
}