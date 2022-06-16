package com.example.futurebankgrupo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.futurebankgrupo1.databinding.ActivityHomeBinding;
import com.example.futurebankgrupo1.fatura.FaturaCartao;
import com.example.futurebankgrupo1.pagarcompix.TelaPagar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private MyViewModel viewModel;

    Locale localeBR = new Locale( "pt", "BR" );
    NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();


        //



        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    String nome = userProfile.getNome();
                    float saldo = userProfile.getSaldo();

                    binding.tvOlaCliente.setText("Olá, " + nome);
                    binding.tvSaldoDisponivel.setText(String.valueOf("R$" + saldo));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivity.this, "Ocorreu algum erro!", Toast.LENGTH_SHORT).show();
            }
        });


        //botão home
        binding.icMenuHome.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TelaConfiguracoesActivity.class);
            startActivity(intent);
        });


        //botões pix
        binding.ivPix.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AreaPixActivity.class);
            startActivity(intent);
        });

        binding.tvPix.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AreaPixActivity.class);
            startActivity(intent);
        });

        binding.constraintPix.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AreaPixActivity.class);
            startActivity(intent);
        });


        //botões pagar
        binding.ivPagar.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TelaPagar.class);
            startActivity(intent);
        });

        binding.tvPagar.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TelaPagar.class);
            startActivity(intent);
        });

        binding.constraintPagar.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TelaPagar.class);
            startActivity(intent);
        });


        //botões transferir
        binding.ivTransferirHome.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PixTransferirActivity.class);
            startActivity(intent);
        });

        binding.tvTransferirHome.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PixTransferirActivity.class);
            startActivity(intent);
        });

        binding.constraintTransferir.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PixTransferirActivity.class);
            startActivity(intent);
        });


        //botões cobrar
        binding.ivCobrarHome.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CobrarPix.class);
            startActivity(intent);
        });

        binding.tvCobrarHome.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CobrarPix.class);
            startActivity(intent);
        });

        binding.constraintCobrar.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CobrarPix.class);
            startActivity(intent);
        });


        //botões cartões
        binding.ivCartoesHome.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MeusCartoesActivity.class);
            startActivity(intent);
        });


        binding.tvCartoesHome.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MeusCartoesActivity.class);
            startActivity(intent);
        });

        binding.constraintCartoes.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MeusCartoesActivity.class);
            startActivity(intent);
        });


        //botoes contas
        binding.ivContas.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TiposContasActivity.class);
            startActivity(intent);
        });

        binding.tvContas.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TiposContasActivity.class);
            startActivity(intent);
        });

        binding.constraintContas.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TiposContasActivity.class);
            startActivity(intent);
        });

        //fatura cartão de credito
        binding.tvCartaoCredito.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FaturaCartao.class);
            startActivity(intent);
        });

        binding.tvFaturaAtual.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FaturaCartao.class);
            startActivity(intent);
        });

        binding.tvLimiteDisponivel.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FaturaCartao.class);
            startActivity(intent);
        });

        binding.tvGetValorLimiteDisponivel.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FaturaCartao.class);
            startActivity(intent);
        });

        binding.tvGetValorFaturaAtual.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FaturaCartao.class);
            startActivity(intent);
        });

        binding.ivArrowForward1.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), FaturaCartao.class);
            startActivity(intent);
        });


        //Seguro cartão
        binding.ivSeguroCartao.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SeguroCartaoActivity.class);
            startActivity(intent);
        });

        binding.tvSeguroCartao.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SeguroCartaoActivity.class);
            startActivity(intent);
        });

        binding.constraintSeguroCatao.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SeguroCartaoActivity.class);
            startActivity(intent);
        });


        //seguro de vida
        binding.constraintSeguroVida.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SeguroVida.class);
            startActivity(intent);
        });

        binding.ivSeguroVida.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SeguroVida.class);
            startActivity(intent);
        });

        binding.tvSeguroVida.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SeguroVida.class);
            startActivity(intent);
        });


        //acessar extrato
        binding.tvOlaCliente.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ContaCorrenteActivity.class);
            startActivity(intent);
        });

        binding.tvContaCorrente.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ContaCorrenteActivity.class);
            startActivity(intent);
        });

        binding.tvSaldoDisponivel.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ContaCorrenteActivity.class);
            startActivity(intent);
        });

        binding.ivArrowForward1.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ContaCorrenteActivity.class);
            startActivity(intent);
        });

        //recarga celular
        binding.tvRecargaHome.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), RecargaCelularActivity.class);
            startActivity(intent);
        });

        binding.constraintRecarga.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), RecargaCelularActivity.class);
            startActivity(intent);
        });

        binding.ivRecarga.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), RecargaCelularActivity.class);
            startActivity(intent);
        });

        //Mostrar saldo conta corrente
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);



        binding.tvSaldoDisponivel.setText(dinheiroBR.format((viewModel.exibirSaldoContaCorrente())));
        binding.tvGetValorFaturaAtual.setText(dinheiroBR.format((viewModel.exibirValorFatura())));
        binding.tvGetValorLimiteDisponivel.setText(dinheiroBR.format((viewModel.exibirLimite())));

    }
}















































































































































































































































































