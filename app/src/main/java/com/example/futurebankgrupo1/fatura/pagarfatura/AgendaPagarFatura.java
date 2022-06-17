package com.example.futurebankgrupo1.fatura.pagarfatura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.futurebankgrupo1.R;

import com.example.futurebankgrupo1.databinding.ActivityAgendaPagarFaturaBinding;
import com.example.futurebankgrupo1.databinding.ActivityPagarFaturaConfirmarValorBinding;

public class AgendaPagarFatura extends AppCompatActivity {

    ActivityAgendaPagarFaturaBinding binding;
    DatePicker reagendar_data;
    Button reagendar_button;
    EditText reagendar_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAgendaPagarFaturaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Binding
        binding.icClearFatura.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ActivityPagarFaturaConfirmarValorBinding.class);
            startActivity(intent);
        });

        //sharedPreference
        reagendar_data = findViewById(R.id.reagendar_data);
        reagendar_button = findViewById(R.id.reagendar_button);
        reagendar_text = findViewById(R.id.reagendar_text);

        reagendar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int dayFatura=reagendar_data.getDayOfMonth();
                int monthFatura=(reagendar_data.getMonth());
                int yearFatura=reagendar_data.getYear();

                //reagendar_text.setText("Date :"+day+"/"+month+"/"+year);

                //sharedPreferences
                SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("chaveDayFatura",dayFatura);
                editor.putInt("chaveMonthFatura",monthFatura);
                editor.putInt("chaveYearFatura",yearFatura);
                editor.commit();
                binding.btnAgendarFatura.setText(dayFatura + "/" +monthFatura+"/"+yearFatura);
                Intent intent = new Intent(getApplicationContext(),ActivityPagarFaturaConfirmarValorBinding.class);
                startActivity(intent);
            }
        });
    }
}