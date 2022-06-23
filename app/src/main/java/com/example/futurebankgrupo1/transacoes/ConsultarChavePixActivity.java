package com.example.futurebankgrupo1.transacoes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.futurebankgrupo1.HomeActivity;
import com.example.futurebankgrupo1.R;
import com.example.futurebankgrupo1.databinding.ActivityConsultarChavePixBinding;
import com.example.futurebankgrupo1.recycler.AdapterChavePix;
import com.example.futurebankgrupo1.recycler.RecyclerChavePix;

import java.util.ArrayList;
import java.util.List;

public class ConsultarChavePixActivity extends AppCompatActivity {

    private ActivityConsultarChavePixBinding binding;
    private RecyclerView recyclerView;
    private List<RecyclerChavePix> listaChavePix = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConsultarChavePixBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icBack.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        //conversão recycler
        recyclerView = findViewById(R.id.recyclerView);

        this.criarListaChavePix();

        //configuração adapter
        AdapterChavePix AdapterChavePix = new AdapterChavePix(listaChavePix);

        //configuração recycler
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(AdapterChavePix);
    }

    public void criarListaChavePix() {

        SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);
        boolean cbCpf = preferences.getBoolean("chaveCbCpf", true);
        boolean cbCelular = preferences.getBoolean("chaveCbCelular", true);
        boolean cbEmail = preferences.getBoolean("chaveCbEmail", true);
        boolean cbChaveAleatoria = preferences.getBoolean("chaveCbChaveAleatoria", true);

        String chaveCpf = preferences.getString("chaveCpf", "");
        String chaveCelular = preferences.getString("chaveCelular", "");
        String chaveEmail = preferences.getString("chaveEmail", "");
        String chaveAleatoria = preferences.getString("chaveAleatoria", "");

        RecyclerChavePix recyclerChavePix = new RecyclerChavePix("Tipo de chave: " + cbCpf, "Chave: " + chaveCpf);
        listaChavePix.add(recyclerChavePix);

        RecyclerChavePix recyclerChavePix1 = new RecyclerChavePix("Tipo de chave: " + cbCelular, "Chave: " + chaveCelular);
        listaChavePix.add(recyclerChavePix1);

        RecyclerChavePix recyclerChavePix2 = new RecyclerChavePix("Tipo de chave: " + cbEmail, "Chave: " + chaveEmail);
        listaChavePix.add(recyclerChavePix2);

        RecyclerChavePix recyclerChavePix3 = new RecyclerChavePix("Tipo de chave: " + cbChaveAleatoria, "Chave: " + chaveAleatoria);
        listaChavePix.add(recyclerChavePix3);
    }
}