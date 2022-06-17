package com.example.futurebankgrupo1.pagarcompix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.futurebankgrupo1.PixTransferirActivity;
import com.example.futurebankgrupo1.databinding.ActivityTelaConfirmarDadosPixBinding;

public class TelaConfirmarDadosPix extends AppCompatActivity {

    private ActivityTelaConfirmarDadosPixBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTelaConfirmarDadosPixBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icBack.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PixTransferirActivity.class);
            startActivity(intent);
        });

        /*binding.tvReagendar.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ReagendarPagamentosBinding.class);
            startActivity(intent);
        });*/


        String valorPix;
        SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);
        valorPix = preferences.getString("chaveValorPix", "");
        binding.tvValor.setText("R$" + valorPix);






        binding.btnConfirmarTransferencia.setOnClickListener(v -> {
            SharedPreferences preferences1 = getSharedPreferences("chaveGeral", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences1.edit();
            editor.putString("chaveMensagemPix", binding.edtMensagem.getText().toString());
            editor.commit();
            Intent intent = new Intent(getApplicationContext(),PixComprovanteActivity.class);
            startActivity(intent);
        });



    }


}