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
import com.example.futurebankgrupo1.R;
import com.example.futurebankgrupo1.databinding.ActivityPixTransferirBinding;
import com.example.futurebankgrupo1.databinding.ActivityTelaTransferirContaBinding;
import com.example.futurebankgrupo1.usuario.UserFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.Locale;

public class TelaTransferirConta extends AppCompatActivity {

    private ActivityTelaTransferirContaBinding binding;

    private MyViewModel viewModel;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    Locale localeBR = new Locale("pt", "BR");
    NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTelaTransferirContaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //botão voltar
        binding.icClear.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        //texto transferir pix
        binding.tvViaPix.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PixTransferirActivity.class);
            startActivity(intent);
        });


        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserFirebase userProfile = snapshot.getValue(UserFirebase.class);

                if (userProfile != null) {
                    float saldo = userProfile.getSaldo();

                    //binding.tvSaldoDisponivelPix.setText(String.valueOf(saldo));
                    binding.tvGetSaldoConta.setText(dinheiroBR.format(saldo));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Ocorreu algum erro!", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnTranfConta.setOnClickListener(v -> {
            String textoMask = binding.edtValorConta.getText().toString();
            String textoNovo = textoMask.replace(",", ".");
            float valor = Float.parseFloat(textoNovo);

            if (viewModel.exibirSaldoContaCorrente() >= valor) {
                viewModel.setarSaldo(viewModel.exibirSaldoContaCorrente() - valor);

                SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("chaveValorTED", binding.edtValorConta.getText().toString());
                editor.putString("chaveBanco", binding.edtInstFinanceira.getText().toString());
                editor.putString("chaveTipoConta", binding.edtTipoConta.getSelectedItem().toString());
                editor.putString("chaveAgencia", binding.edtAgencia.getText().toString());
                editor.putString("chaveNumeroConta", binding.edtConta.getText().toString());
                editor.putString("chaveCpfRecebedor", binding.edtCpfRecebedor.getText().toString());
                editor.commit();
                Intent intent = new Intent(getApplicationContext(), TelaConfirmarDadosTransferirConta.class);
                startActivity(intent);

            } else {
                Toast.makeText(this, "Tente novamente.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}