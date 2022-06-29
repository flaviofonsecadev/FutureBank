package com.example.futurebankgrupo1.transacoes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.futurebankgrupo1.HomeActivity;
import com.example.futurebankgrupo1.MyViewModel;
import com.example.futurebankgrupo1.databinding.ActivityPixComprovanteCopiaColaBinding;
import com.example.futurebankgrupo1.usuario.UserFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PixComprovanteCopiaCola extends AppCompatActivity {

    private ActivityPixComprovanteCopiaColaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPixComprovanteCopiaColaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icClear.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        String userID = user.getUid();
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
                Toast.makeText(PixComprovanteCopiaCola.this, "Error Firebase", Toast.LENGTH_SHORT).show();
            }
        });

        String valorPix;
        String date;
        String mensagemPixCopiaCola;
        SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);
        valorPix = preferences.getString("chaveValorPix", "");
        date = preferences.getString("chaveDate", "");
        mensagemPixCopiaCola = preferences.getString("chaveMensagemPixCopiaCola", "");
        binding.tvGetValorTransferido.setText("R$" + valorPix);
        binding.tvDataHora.setText(date);
        binding.tvTipoPgto.setText(mensagemPixCopiaCola);
    }
}