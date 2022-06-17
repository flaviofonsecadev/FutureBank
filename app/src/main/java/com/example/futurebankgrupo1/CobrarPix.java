package com.example.futurebankgrupo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.futurebankgrupo1.databinding.ActivityCobrarPixBinding;
import com.example.futurebankgrupo1.pagarcompix.TelaConfirmarDadosPix;
//import com.example.futurebankgrupo1.pagarcompix.TelaConfirmarDadosCopiaCola;

public class CobrarPix extends AppCompatActivity {

    private ActivityCobrarPixBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCobrarPixBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        /*binding.btnProximaTela.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TelaConfirmarDadosCobrarPix.class);
            startActivity(intent);
        });*/

        binding.icClear.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        binding.btnProximaTela.setOnClickListener(v -> {
                SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("chaveValorCobrarPix", binding.edtValorPix.getText().toString());
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), TelaConfirmarDadosCobrarPix.class);
                startActivity(intent);
        });


    }
}