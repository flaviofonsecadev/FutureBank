package com.example.futurebankgrupo1.viacep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.futurebankgrupo1.R;
import com.example.futurebankgrupo1.TelaConfirmarDadosPix;
import com.example.futurebankgrupo1.databinding.ActivityPixAgendamentoBinding;
import com.example.futurebankgrupo1.fatura.pagarfatura.PagarFaturaConfirmarValor;

public class PixAgendamento extends AppCompatActivity {

    //binding
    ActivityPixAgendamentoBinding binding;


    DatePicker reagendar_data;
    Button reagendar_button;
    EditText reagendar_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        //binding
        binding = ActivityPixAgendamentoBinding.inflate(getLayoutInflater());

        binding.btnAgendarPix.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TelaConfirmarDadosPix.class);
            startActivity(intent);
        });



        reagendar_data = findViewById(R.id.reagendar_data);
        reagendar_button = findViewById(R.id.reagendar_button);
        reagendar_text = findViewById(R.id.reagendar_text);

        reagendar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int dayPix=reagendar_data.getDayOfMonth();
                int monthPix=(reagendar_data.getMonth());
                int yearPix=reagendar_data.getYear();

                //reagendar_text.setText("Date :"+day+"/"+month+"/"+year);

                //sharedPreferences
                SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("chaveDayPix",dayPix);
                editor.putInt("chaveMonthPix",monthPix);
                editor.putInt("chaveYearPix",yearPix);
                editor.commit();
                binding.reagendarTextPix.setText(dayPix + "/" +monthPix+"/"+yearPix);
                Intent intent = new Intent(getApplicationContext(), PagarFaturaConfirmarValor.class);
                startActivity(intent);
            }
        });
    }
}
