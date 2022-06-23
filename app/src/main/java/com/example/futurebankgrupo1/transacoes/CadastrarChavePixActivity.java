package com.example.futurebankgrupo1.transacoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.futurebankgrupo1.HomeActivity;
import com.example.futurebankgrupo1.databinding.ActivityCadastrarChavesPixBinding;

import java.util.UUID;

public class CadastrarChavePixActivity extends AppCompatActivity {

    private ActivityCadastrarChavesPixBinding binding;
    String chaveCpf;
    String chaveCelular;
    String chaveEmail;
    String chaveAleatoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastrarChavesPixBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icClearChavesPix.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        binding.cbTodas.setOnClickListener(v -> {
            binding.cbCpf.setChecked(true);
            binding.cbCelular.setChecked(true);
            binding.cbEmail.setChecked(true);
            binding.cbChaveAleatoria.setChecked(true);
        });

        binding.btnCadastrarChavePix.setOnClickListener(v -> {

            SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();

            if(binding.cbCpf.isChecked()) {
                editor.putBoolean("chaveCbCpf", true);
                chaveCpf = binding.edtGetCpf.getText().toString();
            }
            if(binding.cbCelular.isChecked()) {
                editor.putBoolean("chaveCbCelular", true);
                chaveCelular = binding.edtGetCelular.getText().toString();
            }
            if(binding.cbEmail.isChecked()) {
                editor.putBoolean("chaveCbEmail", true);
                chaveEmail = binding.edtGetEmail.getText().toString();
            }
            if(binding.cbChaveAleatoria.isChecked()) {
                editor.putBoolean("chaveCbChaveAleatoria", true);
            gerarChaveAleatoria();
            }

            editor.putString("chaveCpf", binding.edtGetCpf.getText().toString());
            editor.putString("chaveCelular", binding.edtGetCelular.getText().toString());
            editor.putString("chaveEmail", binding.edtGetEmail.getText().toString());
            editor.putString("chaveChaveAleatoria", binding.cbChaveAleatoria.getText().toString());
            editor.commit();

            Intent intent = new Intent(getApplicationContext(), ConsultarChavePixActivity.class);
            startActivity(intent);

        });
    }

    private void gerarChaveAleatoria() {
        chaveAleatoria = UUID.randomUUID().toString().replace("-", "");
    }

    /*private String gerarChaveAleatoria(){
        final String chaveAleatoria = UUID.randomUUID().toString().replace("-", "");
        return chaveAleatoria;
    }*/

}