package com.example.futurebankgrupo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.futurebankgrupo1.databinding.ActivityContaPoupancaBinding;
import com.example.futurebankgrupo1.recycler.AdapterPoupanca;
import com.example.futurebankgrupo1.recycler.RecyclerPoupanca;

import java.util.ArrayList;
import java.util.List;

public class    ContaPoupanca extends AppCompatActivity {

    private ActivityContaPoupancaBinding binding;
    private MyViewModel viewModel;

    private RecyclerView recyclerView;
    private List<RecyclerPoupanca> listaRecyclerPoupancas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContaPoupancaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //conversão variavel recycler view
        recyclerView = findViewById(R.id.recyclerView);

        this.criarListaPoupanca();

        //configuração adapter
        AdapterPoupanca AdapterPoupanca = new AdapterPoupanca(listaRecyclerPoupancas);


        //configuração recyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(AdapterPoupanca);

        binding.icClearCp.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        binding.ivArrowForward1.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ContaCorrenteActivity.class);
            startActivity(intent);
        });

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        binding.tvValorGuardado.setText(String.valueOf(viewModel.exibirSaldoContaPoupanca()));
        binding.tvGetValorContaCorrente.setText(String.valueOf(viewModel.exibirSaldoContaCorrente()));

    }

    public void criarListaPoupanca() {

        RecyclerPoupanca recyclerPoupanca = new RecyclerPoupanca("Dinheiro guardado", "03/06/2022", "R$100,00", R.drawable.ic_money_verde);
        listaRecyclerPoupancas.add(recyclerPoupanca);

        recyclerPoupanca = new RecyclerPoupanca("Dinheiro guardado", "29/05/2022", "R$50,00", R.drawable.ic_money_verde);
        listaRecyclerPoupancas.add(recyclerPoupanca);

        recyclerPoupanca = new RecyclerPoupanca("Dinheiro retirado", "25/05/2022", "R$200,00", R.drawable.ic__money_vermelho);
        listaRecyclerPoupancas.add(recyclerPoupanca);

        recyclerPoupanca = new RecyclerPoupanca("Dinheiro guardado", "03/05/2022", "R$550,00", R.drawable.ic_money_verde);
        listaRecyclerPoupancas.add(recyclerPoupanca);

        recyclerPoupanca = new RecyclerPoupanca("Dinheiro guardado", "03/05/2022", "R$70,00", R.drawable.ic_money_verde);
        listaRecyclerPoupancas.add(recyclerPoupanca);

        recyclerPoupanca = new RecyclerPoupanca("Dinheiro retirado", "15/05/2022", "R$120,00", R.drawable.ic__money_vermelho);
        listaRecyclerPoupancas.add(recyclerPoupanca);

        recyclerPoupanca = new RecyclerPoupanca("Dinheiro guardado", "03/05/2022", "R$1000,00", R.drawable.ic_money_verde);
        listaRecyclerPoupancas.add(recyclerPoupanca);

        recyclerPoupanca = new RecyclerPoupanca("Dinheiro guardado", "03/05/2022", "R$150,00", R.drawable.ic_money_verde);
        listaRecyclerPoupancas.add(recyclerPoupanca);

        recyclerPoupanca = new RecyclerPoupanca("Dinheiro guardado", "03/05/2022", "R$1000,00", R.drawable.ic_money_verde);
        listaRecyclerPoupancas.add(recyclerPoupanca);

        recyclerPoupanca = new RecyclerPoupanca("Dinheiro retirado", "01/05/2022", "R$100,00", R.drawable.ic__money_vermelho);
        listaRecyclerPoupancas.add(recyclerPoupanca);

    }
}