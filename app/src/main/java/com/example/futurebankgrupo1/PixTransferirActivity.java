package com.example.futurebankgrupo1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import com.example.futurebankgrupo1.databinding.ActivityPixTransferirBinding;
import com.example.futurebankgrupo1.pagarcompix.TelaConfirmarDadosPix;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.Scanner;

public class PixTransferirActivity extends AppCompatActivity {

    private ActivityPixTransferirBinding binding;
    private MyViewModel viewModel;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pix_transferir);
        binding = ActivityPixTransferirBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //botão voltar
        binding.icClear.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    String nome = userProfile.getNome();
                    float saldo = userProfile.getSaldo();

                    binding.tvSaldoDisponivelPix.setText(String.valueOf(saldo));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PixTransferirActivity.this, "Ocorreu algum erro!", Toast.LENGTH_SHORT).show();
            }
        });

        binding.button.setOnClickListener(v -> {
            float valor = Float.parseFloat(binding.edtValorPix.getText().toString());

            if (viewModel.exibirSaldoContaCorrente() >=valor){
                viewModel.setarSaldo(viewModel.exibirSaldoContaCorrente() - valor);

                SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("chaveValorPix", binding.edtValorPix.getText().toString());
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), TelaConfirmarDadosPix.class);
                startActivity(intent);

            }else {
                Toast.makeText(this, "Tente novamente.", Toast.LENGTH_SHORT).show();
            }


    });

        //        binding.button.setOnClickListener(view1 -> {
//            String valor = binding.edtValorPix.getText().toString();
//            float valor2 = Float.parseFloat(valor);
//
//            if (valor2 <= viewModel.exibirSaldoContaCorrente()){
//                viewModel.setarSaldo(viewModel.exibirSaldoContaCorrente() - valor2);
//            }else {
//                Toast.makeText(this, "Saldo insuficiente.", Toast.LENGTH_LONG).show();}
//

}}