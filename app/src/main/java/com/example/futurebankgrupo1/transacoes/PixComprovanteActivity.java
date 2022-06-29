package com.example.futurebankgrupo1.transacoes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.futurebankgrupo1.HomeActivity;
import com.example.futurebankgrupo1.usuario.UserFirebase;
import com.example.futurebankgrupo1.databinding.ActivityPixComprovanteBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PixComprovanteActivity extends AppCompatActivity {
    private ActivityPixComprovanteBinding binding;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPixComprovanteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icClear.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserFirebase userProfile = snapshot.getValue(UserFirebase.class);

                if (userProfile != null){
                    String nome = userProfile.getNome();
                    String cpf = userProfile.getCpf();

                    binding.tvPagador.setText(nome);
                    binding.tvNumCpfPagador.setText(cpf);
                    }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
               // Toast.makeText(HomeActivity.this, "Ocorreu algum erro!", Toast.LENGTH_SHORT).show();
            }
        });

//        String date;
//        SharedPreferences preferences3 = getSharedPreferences("chaveGeral", MODE_PRIVATE);
//        date = preferences3.getString("chaveDate", "");
//        binding.tvDataHora.setText(date);

        String valorPix;
        String nomeRecebedor;
        String mensagemPix;
        String date;
        SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);
        valorPix = preferences.getString("chaveValorPix", "");
        nomeRecebedor = preferences.getString("chaveNomeRecebedor", "");
        mensagemPix = preferences.getString("chaveMensagemPix", "");
        date = preferences.getString("chaveDate", "");
        binding.tvGetValorTransferido.setText("R$" + valorPix);
        binding.tvRecebedor.setText(nomeRecebedor);
        binding.tvTipoPgto.setText(mensagemPix);
        binding.tvDataHora.setText(date);

    }
}