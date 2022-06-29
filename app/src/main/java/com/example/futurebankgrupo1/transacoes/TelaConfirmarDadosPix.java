package com.example.futurebankgrupo1.transacoes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.futurebankgrupo1.fatura.ReagendarPagamentosActivity;
import com.example.futurebankgrupo1.databinding.ActivityTelaConfirmarDadosPixBinding;
import com.example.futurebankgrupo1.usuario.LoginActivity;

import java.util.Calendar;
import java.util.concurrent.Executor;

public class TelaConfirmarDadosPix extends AppCompatActivity {

    DatePickerDialog.OnDateSetListener onDateSetListener;
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

        binding.tvReagendar.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ReagendarPagamentosActivity.class);
            startActivity(intent);
        });


        String valorPix;
        String nomeRecebedor;
        SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);
        valorPix = preferences.getString("chaveValorPix", "");
        binding.tvValor.setText("R$" + valorPix);
        nomeRecebedor = preferences.getString("chaveNomeRecebedor", "");
        binding.tvNomeRecebedor.setText(nomeRecebedor);

        /*binding.btnConfirmarTransferencia.setOnClickListener(v -> {
            SharedPreferences preferences1 = getSharedPreferences("chaveGeral", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences1.edit();
            editor.putString("chaveMensagemPix", binding.edtMensagem.getText().toString());
            editor.commit();
            Intent intent = new Intent(getApplicationContext(), SenhaConfirmacaoPix.class);
            startActivity(intent);
        });*/

        //data calendar
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        binding.tvReagendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        TelaConfirmarDadosPix.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener,year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        onDateSetListener =  new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                binding.tvAgora.setText(date);
                SharedPreferences preferences3 = getSharedPreferences("chaveGeral", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences3.edit();
                editor.putString("chaveDate", date);
                editor.commit();
            }
        };

        //Biometria
        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt biometricPrompt = new BiometricPrompt(TelaConfirmarDadosPix.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(TelaConfirmarDadosPix.this, "Digital com erro ou não cadastrada em seu dispositivo! Tente outra digital.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(), "Transação realizada com sucesso!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), PixComprovanteActivity.class));
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(TelaConfirmarDadosPix.this, "Este dispositivo não suporta autenticação por biometria.", Toast.LENGTH_SHORT).show();
            }
        });

        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Confirmar Transação")
                .setDescription("Use sua digital para confirmar esta transação.")
                .setNegativeButtonText("Cancelar")
                .build();

        binding.btnConfirmarTransferencia.setOnClickListener(v -> {
            //int month2 = month+1;
            //String date = day+"/"+ (month + 1) +"/"+year;
            biometricPrompt.authenticate(promptInfo);
            SharedPreferences preferences1 = getSharedPreferences("chaveGeral", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences1.edit();
            //editor.putString("chaveDate", date);
            editor.putString("chaveMensagemPix", binding.edtMensagem.getText().toString());
            editor.commit();
        });

    }


}