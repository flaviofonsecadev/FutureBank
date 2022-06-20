package com.example.futurebankgrupo1.transacoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.futurebankgrupo1.databinding.ActivityTelaConfirmarDadosPixCopiaColaBinding;

public class TelaConfirmarDadosPixCopiaCola extends AppCompatActivity {

    private ActivityTelaConfirmarDadosPixCopiaColaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTelaConfirmarDadosPixCopiaColaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icBack.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TelaPixCopiaCola.class);
            startActivity(intent);
        });

        /*binding.tvReagendar.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ReagendarPagamentosBinding.class);
            startActivity(intent);
        });*/


        binding.btnConfirmarTransferencia.setOnClickListener(v -> {
            SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("chaveMensagemPixCopiaCola", binding.edtMensagem.getText().toString());
            editor.commit();
            Intent intent = new Intent(getApplicationContext(), PixComprovanteCopiaCola.class);
            startActivity(intent);
        });



    }


}