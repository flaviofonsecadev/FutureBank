package com.example.futurebankgrupo1.transacoes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;

import com.example.futurebankgrupo1.R;
import com.example.futurebankgrupo1.databinding.ActivityTelaConfirmarDadosPixBinding;
import com.example.futurebankgrupo1.databinding.ActivityTelaConfirmarDadosTransferirContaBinding;

public class TelaConfirmarDadosTransferirConta extends AppCompatActivity {

    //datepicker
    DatePickerDialog.OnDateSetListener onDateSetListener;

    private ActivityTelaConfirmarDadosTransferirContaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTelaConfirmarDadosTransferirContaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}