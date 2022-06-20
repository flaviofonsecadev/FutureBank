package com.example.futurebankgrupo1.contas;

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
import com.example.futurebankgrupo1.usuario.User;
import com.example.futurebankgrupo1.databinding.ActivityAplicarCpBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.Locale;

public class AplicarCP extends AppCompatActivity {
    ActivityAplicarCpBinding binding;
    MyViewModel viewModel;

    Locale localeBR = new Locale( "pt", "BR" );
    NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAplicarCpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icClearAplicar.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        });

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        binding.buttonAplicar.setOnClickListener(v -> {
            float valor = Float.parseFloat(binding.edtValorAplicar.getText().toString());
            float saldoCc = viewModel.exibirSaldoContaCorrente();
            float saldoCp = viewModel.exibibirSaldoPoupancaFirebase();

            if (saldoCc >=valor){
                viewModel.setarSaldo(saldoCc - valor);
                viewModel.setarSaldoPoupanca(saldoCp + valor);

                SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("chaveValorAplicar", binding.edtValorAplicar.getText().toString());
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), AplicarComprovante.class);
                startActivity(intent);

            }else {
                Toast.makeText(AplicarCP.this, "Tente novamente.", Toast.LENGTH_SHORT).show();
            }
        });


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        String userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    float saldo = userProfile.getSaldo();
                    float saldoPoupanca = userProfile.getSaldoPoupanca();

                    //binding.tvSaldoDisponivelCcAplicar.setText(String.valueOf("R$" + saldo));
                    binding.tvSaldoDisponivelCcAplicar.setText(dinheiroBR.format(saldo));
                    //binding.tvSaldoDisponivelAplicar.setText(String.valueOf("R$" + saldoPoupanca));
                    binding.tvSaldoDisponivelAplicar.setText(dinheiroBR.format(saldoPoupanca));

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AplicarCP.this, "Ocorreu algum erro ao exibir saldo!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}