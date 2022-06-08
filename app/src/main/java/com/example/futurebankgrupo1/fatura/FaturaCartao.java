package com.example.futurebankgrupo1.fatura;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.futurebankgrupo1.recycler.Compra;
import com.example.futurebankgrupo1.HomeActivity;
import com.example.futurebankgrupo1.MyViewModel;
import com.example.futurebankgrupo1.R;
import com.example.futurebankgrupo1.recycler.AdapterCompra;
import com.example.futurebankgrupo1.databinding.ActivityFaturaCartaoBinding;
import com.example.futurebankgrupo1.fatura.pagarfatura.PagarFatura;

import java.util.ArrayList;
import java.util.List;

public class FaturaCartao extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Compra> listaCompras = new ArrayList<>();
    private ActivityFaturaCartaoBinding binding;
    private MyViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFaturaCartaoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //conversão variável recyclerView
        recyclerView = findViewById(R.id.recyclerView);

        this.criarCompra();

        //configuração do adapter
        AdapterCompra AdapterCompra = new AdapterCompra(listaCompras);



        //configuração recyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(AdapterCompra);


        binding.icClear.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        binding.tvPagarFaturaAtual.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PagarFatura.class);
            startActivity(intent);
        });

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        /*binding.btnEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvValorAtual.setText(String.valueOf(viewModel.exibirValorFatura()));

                //viewModel.exibirValorFatura();
            }
        });*/

        /*binding.button.setOnClickListener(v -> {
            new String(String.valueOf(viewModel.comprarCartaoCredito()));
        });

        binding.tvValorAtual.setText(String.valueOf(viewModel.exibirValorFatura()));
        binding.tvGetLimite.setText(String.valueOf(viewModel.exibirLimite()));*/

    }

    public void criarCompra(){

        Compra compra = new Compra("Compra em Petz", "03/06/2022", "R$199,90", R.drawable.ic_pet2);
        this.listaCompras.add(compra);

        compra = new Compra("Compra em Americanas.com", "01/06/2022", "R$89,00", R.drawable.ic_internet);
        this.listaCompras.add(compra);

        compra = new Compra("Compra em Amazon", "29/05/2022", "R$49,90", R.drawable.ic_internet);
        this.listaCompras.add(compra);

        compra = new Compra("Compra em BurgerKing", "25/05/2022", "R$79,80", R.drawable.ic_restaurante);
        this.listaCompras.add(compra);

        compra = new Compra("Recarga de Celular", "23/05/2022", "R$20,00", R.drawable.ic_smartphone);
        this.listaCompras.add(compra);

        compra = new Compra("Compra em MC Donalds", "20/05/2022", "R$234,83", R.drawable.ic_restaurante);
        this.listaCompras.add(compra);

        compra = new Compra("Compra em Carrefour", "15/05/2022", "R$92,34", R.drawable.ic_mercado);
        this.listaCompras.add(compra);

        compra = new Compra("Compra em Assaí", "10/05/2022", "R$132,81", R.drawable.ic_mercado);
        this.listaCompras.add(compra);

        compra = new Compra("Compra em Shopee", "04/05/2022", "R$159,55", R.drawable.ic_internet);
        this.listaCompras.add(compra);

        compra = new Compra("Compra em Farmácia Nissei", "03/05/2022", "R$39,90", R.drawable.ic_farmacia);
        this.listaCompras.add(compra);


    }

}