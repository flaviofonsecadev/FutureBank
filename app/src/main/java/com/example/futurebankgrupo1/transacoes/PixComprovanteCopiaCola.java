package com.example.futurebankgrupo1.transacoes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

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
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPixComprovanteCopiaColaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icClear.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
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

        String valorPix;
        int dayPix;
        int monthPix;
        int yearPix;
        SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);
        valorPix = preferences.getString("chaveValorPix", "");
        dayPix = preferences.getInt("chaveDayPix",0);
        monthPix = preferences.getInt("chaveMonthPix",0);
        yearPix= preferences.getInt("chaveYearPix", 0);
        binding.tvDataHora.setText(dayPix + "/" +monthPix+"/"+yearPix);

        binding.tvGetValorTransferido.setText("R$" + valorPix);

        String mensagemPixCopiaCola;
        SharedPreferences preferences1 = getSharedPreferences("chaveGeral", MODE_PRIVATE);
        mensagemPixCopiaCola = preferences1.getString("chaveMensagemPixCopiaCola", "");
        binding.tvTipoPgto.setText(mensagemPixCopiaCola);

    }
}