package com.example.futurebankgrupo1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.futurebankgrupo1.databinding.ActivityContaPoupancaBinding;
import com.example.futurebankgrupo1.recycler.AdapterPoupanca;
import com.example.futurebankgrupo1.recycler.RecyclerPoupanca;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class    ContaPoupanca extends AppCompatActivity {

    private ActivityContaPoupancaBinding binding;
    private MyViewModel viewModel;

    private RecyclerView recyclerView;
    private List<RecyclerPoupanca> listaRecyclerPoupancas = new ArrayList<>();
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContaPoupancaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    float saldo = userProfile.getSaldo();
                    float saldoPoupanca = userProfile.getSaldoPoupanca();

                    binding.tvGetValorContaCorrente.setText(String.valueOf("R$" + saldo));
                    binding.tvValorGuardado.setText(String.valueOf("R$" + saldoPoupanca));

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ContaPoupanca.this, "Ocorreu algum erro ao exibir saldo!", Toast.LENGTH_SHORT).show();
            }
        });


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

        binding.ivContaCorrente.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ContaCorrenteActivity.class);
            startActivity(intent);
        });

        binding.tvExtratoContaCorrente.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ContaCorrenteActivity.class);
            startActivity(intent);
        });

        //forma mais resumida para mudar tela
        binding.tvAplicar.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AplicarCP.class));
        } );

        binding.ivArrowAplicar.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AplicarCP.class));
        } );

        binding.ivAplicar1.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AplicarCP.class));
        } );

        binding.tvAplicarTitle.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AplicarCP.class));
        } );

        binding.tvResgatarCp.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ResgatarCC.class));
        } );

        binding.ivArrowResgatarCp.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ResgatarCC.class));
        } );

        binding.tvResgatarTitleCp.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ResgatarCC.class));
        } );



        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        binding.tvValorGuardado.setText(String.valueOf(viewModel.exibirSaldoContaPoupanca()));
        //binding.tvGetValorContaCorrente.setText(String.valueOf(viewModel.exibirSaldoContaCorrente()));

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