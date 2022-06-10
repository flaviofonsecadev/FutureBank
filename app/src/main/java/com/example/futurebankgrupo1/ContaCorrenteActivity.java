package com.example.futurebankgrupo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.futurebankgrupo1.databinding.ActivityContaCorrenteBinding;
import com.example.futurebankgrupo1.recycler.RecyclerCorrente;
import com.example.futurebankgrupo1.recycler.AdapterCorrente;

import java.util.ArrayList;
import java.util.List;

public class ContaCorrenteActivity extends AppCompatActivity {

    private ActivityContaCorrenteBinding binding;
    private MyViewModel viewModel;

    private RecyclerView recyclerView;
    private List<RecyclerCorrente> listaCorrente = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContaCorrenteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //conversão recycler
        recyclerView = findViewById(R.id.recyclerView);

        this.criarListaCorrente();


        //configuração adapter
        AdapterCorrente AdapterCorrente = new AdapterCorrente(listaCorrente);


        //configuração recycler
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(AdapterCorrente);



        binding.ivArrowForward1.setOnClickListener(v -> {
            Intent intent  = new Intent(getApplicationContext(), ContaPoupanca.class);
            startActivity(intent);
        });

        binding.icClearCc.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        binding.tvSaldoDisponivelCc.setText(String.valueOf(viewModel.exibirSaldoContaCorrente()));
        binding.tvGetValorGuardadoCorrente.setText(String.valueOf(viewModel.exibirSaldoContaPoupanca()));

    }

    public void criarListaCorrente() {

        RecyclerCorrente recyclerCorrente = new RecyclerCorrente("Transferência enviada", "R$50,00","03/06/2022", R.drawable.ic__money_vermelho);
        listaCorrente.add(recyclerCorrente);

        recyclerCorrente = new RecyclerCorrente("Transferência recebida", "R$200,00","01/06/2022", R.drawable.ic_money_verde);
        listaCorrente.add(recyclerCorrente);

        recyclerCorrente = new RecyclerCorrente("Transferência recebida", "R$1000,00","29/05/2022", R.drawable.ic_money_verde);
        listaCorrente.add(recyclerCorrente);

        recyclerCorrente = new RecyclerCorrente("Compra em Carrefour", "R$54,79","25/05/2022", R.drawable.ic__money_vermelho);
        listaCorrente.add(recyclerCorrente);

        recyclerCorrente = new RecyclerCorrente("Transferência enviada", "R$70,00","22/05/2022", R.drawable.ic__money_vermelho);
        listaCorrente.add(recyclerCorrente);

        recyclerCorrente = new RecyclerCorrente("Transferência enviada", "R$120,00","20/05/2022", R.drawable.ic__money_vermelho);
        listaCorrente.add(recyclerCorrente);

        recyclerCorrente = new RecyclerCorrente("Transferência recebida", "R$2000,00","15/05/2022", R.drawable.ic_money_verde);
        listaCorrente.add(recyclerCorrente);

        recyclerCorrente = new RecyclerCorrente("Boleto de água", "R$100,00","10/05/2022", R.drawable.ic__money_vermelho);
        listaCorrente.add(recyclerCorrente);

        recyclerCorrente = new RecyclerCorrente("Boleto de Luz", "R$190,00","10/05/2022", R.drawable.ic__money_vermelho);
        listaCorrente.add(recyclerCorrente);

        recyclerCorrente = new RecyclerCorrente("Transferência recebida", "R$3000,00","05/05/2022", R.drawable.ic_money_verde);
        listaCorrente.add(recyclerCorrente);
    }
}