package com.example.futurebankgrupo1.transacoes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.futurebankgrupo1.HomeActivity;
import com.example.futurebankgrupo1.R;
import com.example.futurebankgrupo1.databinding.ActivityTelaTransferirContaBinding;
import com.example.futurebankgrupo1.databinding.ActivityTelaTransferirContaComprovanteBinding;
import com.example.futurebankgrupo1.usuario.UserFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TelaTransferirContaComprovante extends AppCompatActivity {

    private ActivityTelaTransferirContaComprovanteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTelaTransferirContaComprovanteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icClear.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
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
                Toast.makeText(TelaTransferirContaComprovante.this, "Error Firebase", Toast.LENGTH_SHORT).show();
            }
        });
        String date;
        SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);
        date = preferences.getString("chaveDate", "");
        binding.tvDataHora.setText(date);

        String valorTed;
        String banco;
        String tipoConta;
        String agencia;
        String numeroConta;
        String cpfRecebedor;
        valorTed = preferences.getString("chaveValorTED", "");
        binding.tvGetValorTransferido.setText("R$" + valorTed);
        banco = preferences.getString("chaveBanco", "");
        binding.tvNomeInstituicaoRecebedor.setText(banco);
        tipoConta = preferences.getString("chaveTipoConta", "");
        binding.tvTipoContaRecebedor.setText(tipoConta);
        agencia = preferences.getString("chaveAgencia", "");
        binding.tvNumAgRecebedor.setText(agencia);
        numeroConta = preferences.getString("chaveNumeroConta","");
        binding.tvNumCcRecebedor.setText(numeroConta);
        cpfRecebedor = preferences.getString("chaveCpfRecebedor", "");
        binding.tvNumCpfRecebedor.setText(cpfRecebedor);

    }
}