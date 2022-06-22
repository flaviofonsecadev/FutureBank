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
import com.example.futurebankgrupo1.usuario.UserFirebase;
import com.example.futurebankgrupo1.databinding.ActivityResgatarCcBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.Locale;

public class ResgatarCC extends AppCompatActivity {
    ActivityResgatarCcBinding binding;
    MyViewModel viewModel;

    Locale localeBR = new Locale( "pt", "BR" );
    NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResgatarCcBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icClearResgatar.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        });

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        binding.buttonResgatar.setOnClickListener(v -> {String textoMask = binding.edtValorResgatar.getText().toString();
            String textoNovo = textoMask.replace(",", ".");
            float valor = Float.parseFloat(textoNovo);
            //float valor = Float.parseFloat(binding.edtValorResgatar.getText().toString());
            float saldoCc = viewModel.exibirSaldoContaCorrente();
            float saldoCp = viewModel.exibibirSaldoPoupancaFirebase();

            if (saldoCp >=valor){
                viewModel.setarSaldo(saldoCc + valor);
                viewModel.setarSaldoPoupanca(saldoCp - valor);


                SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("chaveValorResgatar", binding.edtValorResgatar.getText().toString());
                //editor.putInt("chaveValorResgatar", Integer.parseInt(String.valueOf(binding.edtValorResgatar)));
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), ResgatarComprovante.class);
                startActivity(intent);

            }else {
                Toast.makeText(ResgatarCC.this, "Tente novamente.", Toast.LENGTH_SHORT).show();
            }


        });


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        String userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserFirebase userProfile = snapshot.getValue(UserFirebase.class);

                if (userProfile != null){
                    float saldo = userProfile.getSaldo();
                    float saldoPoupanca = userProfile.getSaldoPoupanca();

                    //binding.tvSaldoDisponivelCcResgatar.setText(String.valueOf("R$" + saldo));
                    binding.tvSaldoDisponivelCcResgatar.setText(dinheiroBR.format(saldo));
                    //binding.tvSaldoDisponivelResgatar.setText(String.valueOf("R$" + saldoPoupanca));
                    binding.tvSaldoDisponivelResgatar.setText(dinheiroBR.format(saldoPoupanca));

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ResgatarCC.this, "Ocorreu algum erro ao exibir saldo!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}