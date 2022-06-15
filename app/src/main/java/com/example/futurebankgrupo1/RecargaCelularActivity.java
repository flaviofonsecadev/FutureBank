package com.example.futurebankgrupo1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.futurebankgrupo1.databinding.ActivityRecargaCelularBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RecargaCelularActivity extends AppCompatActivity {

    private ActivityRecargaCelularBinding binding;
    private MyViewModel viewModel;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    //public static Float valRecarga; //********************************


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecargaCelularBinding.inflate(getLayoutInflater());
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
                    String telefone = userProfile.getTelefone();

                    binding.edtTelefone.setText(telefone);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RecargaCelularActivity.this, "Ocorreu um erro ao exibir o numero do celular!", Toast.LENGTH_SHORT).show();
            }
        });


        binding.icClear.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        binding.btnRecarregar.setOnClickListener(view1 -> {


            //String valorSelect1 = binding.spValor.getSelectedItem().toString();
            float valorSelect = Float.parseFloat(binding.spValor.getSelectedItem().toString());

            String pagamentoSelect = binding.spPagamento.getSelectedItem().toString();
            String operadoraSelect = binding.spOperadora.getSelectedItem().toString();
            String telefone = binding.edtTelefone.getText().toString();

            //float saldo = viewModel.exibirSaldoContaCorrente();


            if (viewModel.exibirSaldoContaCorrente() >= valorSelect){ //*****************************************************
                viewModel.setarSaldo(viewModel.exibirSaldoContaCorrente() - valorSelect); //*******************************
                Toast.makeText(this, "Recarga efetuada com sucesso!", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(getApplicationContext(), RecargaComprovanteActivity.class);
                //startActivity(intent);
            } else {
                Toast.makeText(this, "Saldo insuficiente para realizar essa recarga! Tente novamente.", Toast.LENGTH_LONG).show();}


            if (!pagamentoSelect.isEmpty() && !operadoraSelect.isEmpty() && !telefone.isEmpty() && Patterns.PHONE.matcher(telefone).matches()) {
                Intent intent = new Intent(this, RecargaComprovanteActivity.class);
                startActivity(intent);
            } else {
                if (telefone.isEmpty()) {
                    binding.edtTelefone.setError("Preencha o campo");
                }
                if (!Patterns.PHONE.matcher(telefone).matches()) {
                    binding.edtTelefone.setError("Preencha o campo");
                }
               /* if (valorSelect1.isEmpty()) {
                    ((TextView) binding.spValor.getSelectedView()).setError("Selecione um dos campos");
                }*/
                if (pagamentoSelect.isEmpty()) {
                    ((TextView) binding.spOperadora.getSelectedView()).setError("Selecione um dos campos");
                }
                if (operadoraSelect.isEmpty()) {
                    ((TextView) binding.spPagamento.getSelectedView()).setError("Selecione um dos campos");
                };
            }
            SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("chaveTelefone", binding.edtTelefone.getText().toString());
            editor.putString("chaveOperadora", binding.spOperadora.getSelectedItem().toString());
            editor.putString("chaveValorRecarga", binding.spValor.getSelectedItem().toString());
            editor.putString("chaveTipoPagamento", binding.spPagamento.getSelectedItem().toString());
            editor.commit();
        });



        /*binding.btnRecarregar.setOnClickListener(v -> {
            float saldo = viewModel.exibirSaldoContaCorrente();
            if (saldo >= valRecarga){ //*****************************************************
                viewModel.setarSaldo(saldo - valRecarga); //*******************************
                Toast.makeText(this, "Recarga efetuada com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), RecargaComprovanteActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Saldo insuficiente para realizar essa recarga! Tente novamente.", Toast.LENGTH_LONG).show();}*/


//            new String(String.valueOf(viewModel.comprarCartaoCredito()));
//            binding.tvGetSaldo.setText(df.format((viewModel.exibirSaldoContaCorrente())));









        /*binding.button.setOnClickListener(v -> {
            new String(String.valueOf(viewModel.comprarCartaoCredito()));
        });*/

        //binding.button.setOnClickListener(String.valueOf(viewModel.comprarCartaoCredito()));

    }
}