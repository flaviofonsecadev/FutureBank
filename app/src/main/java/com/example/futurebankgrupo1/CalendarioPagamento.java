package com.example.futurebankgrupo1;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.DatePicker;
        import android.widget.EditText;

import com.example.futurebankgrupo1.databinding.ReagendarPagamentosBinding;
import com.example.futurebankgrupo1.fatura.pagarfatura.PagarFaturaConfirmarValor;
import com.example.futurebankgrupo1.pagarcompix.TelaConfirmarDadosPix;


public class CalendarioPagamento extends AppCompatActivity {

    //binding
    ReagendarPagamentosBinding binding;


    DatePicker reagendar_data;
    Button reagendar_button;
    EditText reagendar_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        //binding
        binding = ReagendarPagamentosBinding.inflate(getLayoutInflater());

        binding.icClear.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PagarFaturaConfirmarValor.class);
            startActivity(intent);
        });



        reagendar_data = findViewById(R.id.reagendar_data);
        reagendar_button = findViewById(R.id.reagendar_button);
        reagendar_text = findViewById(R.id.reagendar_text);

        reagendar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day=reagendar_data.getDayOfMonth();
                int month=(reagendar_data.getMonth() + 1);
                int year=reagendar_data.getYear();

                //reagendar_text.setText("Date :"+day+"/"+month+"/"+year);

                //sharedPreferences
                SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("chaveDay",day);
                editor.putInt("chaveMonth",month);
                editor.putInt("chaveYear",year);
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), PagarFaturaConfirmarValor.class);
                startActivity(intent);
            }
        });
    }
}
