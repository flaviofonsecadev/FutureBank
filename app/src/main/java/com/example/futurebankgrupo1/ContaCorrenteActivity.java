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

import com.example.futurebankgrupo1.databinding.ActivityContaCorrenteBinding;
import com.example.futurebankgrupo1.recycler.RecyclerCorrente;
import com.example.futurebankgrupo1.recycler.AdapterCorrente;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ContaCorrenteActivity extends AppCompatActivity {

    private ActivityContaCorrenteBinding binding;
    private MyViewModel viewModel;

    Locale localeBR = new Locale( "pt", "BR" );
    NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);

    private RecyclerView recyclerView;
    private List<RecyclerCorrente> listaCorrente = new ArrayList<>();

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContaCorrenteBinding.inflate(getLayoutInflater());
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

                    //binding.tvSaldoDisponivelCc.setText(String.valueOf("R$" + saldo));
                    binding.tvSaldoDisponivelCc.setText(dinheiroBR.format(saldo));
                    //binding.tvGetValorGuardadoCorrente.setText(String.valueOf("R$" + saldoPoupanca));
                    binding.tvGetValorGuardadoCorrente.setText(dinheiroBR.format(saldoPoupanca));

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ContaCorrenteActivity.this, "Ocorreu algum erro ao exibir saldo!", Toast.LENGTH_SHORT).show();
            }
        });

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

        binding.tvValorGuardado.setOnClickListener(v -> {
            Intent intent  = new Intent(getApplicationContext(), ContaPoupanca.class);
            startActivity(intent);
        });

        binding.ivContaPoupanca.setOnClickListener(v -> {
            Intent intent  = new Intent(getApplicationContext(), ContaPoupanca.class);
            startActivity(intent);
        });

        binding.icClearCc.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        //forma mais resumida para mudar tela
        binding.tvAplicarCc.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AplicarCP.class));
        } );

        binding.ivArrowAplicarCc.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AplicarCP.class));
        } );

        binding.ivAplicarCc.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AplicarCP.class));
        } );

        binding.tvAplicarTitleCc.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AplicarCP.class));
        } );

        binding.tvResgatarCc.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ResgatarCC.class));
        } );

        binding.ivArrowResgatarCc.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ResgatarCC.class));
        } );

        binding.tvResgatarTitleCc.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ResgatarCC.class));
        } );

        binding.ivResgatarCc.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ResgatarCC.class));
        } );





//        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
//
//        binding.tvSaldoDisponivelCc.setText(String.valueOf(viewModel.exibirSaldoContaCorrente()));
//        binding.tvGetValorGuardadoCorrente.setText(String.valueOf(viewModel.exibirSaldoContaPoupanca()));

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