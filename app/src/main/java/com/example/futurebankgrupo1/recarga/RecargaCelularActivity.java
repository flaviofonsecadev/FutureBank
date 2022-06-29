package com.example.futurebankgrupo1.recarga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.futurebankgrupo1.HomeActivity;
import com.example.futurebankgrupo1.databinding.ActivityRecargaCelularBinding;
import com.example.futurebankgrupo1.transacoes.PixComprovanteCopiaCola;
import com.example.futurebankgrupo1.transacoes.TelaConfirmarDadosPixCopiaCola;
import com.example.futurebankgrupo1.usuario.UserFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Executor;

public class RecargaCelularActivity extends AppCompatActivity {

    private ActivityRecargaCelularBinding binding;
    //MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecargaCelularBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        String userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserFirebase userProfile = snapshot.getValue(UserFirebase.class);

                if (userProfile != null){
                    String telefoneFirebase = userProfile.getTelefone();
                    float valorFatura = userProfile.getValorFatura();
                    float limite = userProfile.getLimiteCartao();
                    float saldo = userProfile.getSaldo();

                    binding.edtTelefone.setText(telefoneFirebase);



                    Executor executor = ContextCompat.getMainExecutor(getApplicationContext());

                    BiometricPrompt biometricPrompt = new BiometricPrompt(RecargaCelularActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
                        @Override
                        public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                            super.onAuthenticationError(errorCode, errString);
                            Toast.makeText(RecargaCelularActivity.this, "Digital com erro ou não cadastrada em seu dispositivo! Tente outra digital.", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                            super.onAuthenticationSucceeded(result);
                            Toast.makeText(getApplicationContext(), "Recarga realizada com sucesso!", Toast.LENGTH_SHORT).show();

                            //spinner
                            float valorSelect = Float.parseFloat(binding.spValor.getSelectedItem().toString());
                            String pagamentoSelect = binding.spPagamento.getSelectedItem().toString();
                            String operadoraSelect = binding.spOperadora.getSelectedItem().toString();
                            String telefone = binding.edtTelefone.getText().toString();

                            if ((pagamentoSelect.equals("Débito")) && saldo >= valorSelect) {
                                reference.child(userID).child("saldo").setValue(saldo - valorSelect);
                                Intent intent = new Intent(getApplicationContext(), RecargaComprovanteActivity.class);
                                startActivity(intent);
                            }

                            if ((pagamentoSelect.equals("Crédito")) && limite >= valorSelect){
                                reference.child(userID).child("valorFatura").setValue(valorFatura + valorSelect);
                                reference.child(userID).child("limiteCartao").setValue(limite - valorSelect);
                                Intent intent = new Intent(getApplicationContext(), RecargaComprovanteActivity.class);
                                startActivity(intent);
                            }


                            if (!pagamentoSelect.isEmpty() && !operadoraSelect.isEmpty() && !telefone.isEmpty() && Patterns.PHONE.matcher(telefone).matches()) {

                            } else {
                                if (telefone.isEmpty()) {
                                    binding.edtTelefone.setError("Preencha o campo");
                                }
                                if (!Patterns.PHONE.matcher(telefone).matches()) {
                                    binding.edtTelefone.setError("Preencha o campo");
                                }
                                if (valorSelect<=0) {
                                    ((TextView) binding.spValor.getSelectedView()).setError("Selecione um dos campos");
                                }
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
                        }

                        @Override
                        public void onAuthenticationFailed() {
                            super.onAuthenticationFailed();
                            Toast.makeText(RecargaCelularActivity.this, "Este dispositivo não suporta autenticação por biometria.", Toast.LENGTH_SHORT).show();
                        }
                    });

                    BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                            .setTitle("Confirmar Transação")
                            .setDescription("Use sua digital para confirmar esta transação.")
                            .setNegativeButtonText("Cancelar")
                            .build();


                    binding.btnRecarregar.setOnClickListener(view1 -> {
                        //Prompt biometria
                        biometricPrompt.authenticate(promptInfo);
                    });


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


        //viewModel = new ViewModelProvider(this).get(MyViewModel.class);



        //
//        binding.btnRecarregar.setOnClickListener(view1 -> {
//
//            //String valorSelect1 = binding.spValor.getSelectedItem().toString();
//            float valorSelect = Float.parseFloat(binding.spValor.getSelectedItem().toString());
//
//            String pagamentoSelect = binding.spPagamento.getSelectedItem().toString();
//            String operadoraSelect = binding.spOperadora.getSelectedItem().toString();
//            String telefone = binding.edtTelefone.getText().toString();
//
//            //float saldo = viewModel.exibirSaldoContaCorrente();
//
//
//            if ((pagamentoSelect.equals("Débito")) && viewModel.exibirSaldoContaCorrente() >= valorSelect) {
//                viewModel.setarSaldo(viewModel.exibirSaldoContaCorrente() - valorSelect);
//                Toast.makeText(this, "Recarga efetuada com sucesso!", Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(getApplicationContext(), RecargaComprovanteActivity.class);
//                startActivity(intent);
//            }
//
//            if ((pagamentoSelect.equals("Crédito")) && viewModel.exibirLimiteCartaoFirebase() >= valorSelect){
//                viewModel.setarValorFaturaFirebase(viewModel.exibirValorFaturaFirebase() + valorSelect);
//                viewModel.setarLimiteCartaoFirebase(viewModel.exibirLimiteCartaoFirebase() - valorSelect);
//                Intent intent = new Intent(getApplicationContext(), RecargaComprovanteActivity.class);
//                startActivity(intent);
//            }
//
//
//            if (!pagamentoSelect.isEmpty() && !operadoraSelect.isEmpty() && !telefone.isEmpty() && Patterns.PHONE.matcher(telefone).matches()) {
////                Intent intent = new Intent(this, RecargaComprovanteActivity.class);
////                startActivity(intent);
//            } else {
//                if (telefone.isEmpty()) {
//                    binding.edtTelefone.setError("Preencha o campo");
//                }
//                if (!Patterns.PHONE.matcher(telefone).matches()) {
//                    binding.edtTelefone.setError("Preencha o campo");
//                }
//                if (valorSelect<=0) {
//                    ((TextView) binding.spValor.getSelectedView()).setError("Selecione um dos campos");
//                }
//                if (pagamentoSelect.isEmpty()) {
//                    ((TextView) binding.spOperadora.getSelectedView()).setError("Selecione um dos campos");
//                }
//                if (operadoraSelect.isEmpty()) {
//                    ((TextView) binding.spPagamento.getSelectedView()).setError("Selecione um dos campos");
//                };
//            }
//            SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.putString("chaveTelefone", binding.edtTelefone.getText().toString());
//            editor.putString("chaveOperadora", binding.spOperadora.getSelectedItem().toString());
//            editor.putString("chaveValorRecarga", binding.spValor.getSelectedItem().toString());
//            editor.putString("chaveTipoPagamento", binding.spPagamento.getSelectedItem().toString());
//            editor.commit();
//        });

//            new String(String.valueOf(viewModel.comprarCartaoCredito()));
//            binding.tvGetSaldo.setText(df.format((viewModel.exibirSaldoContaCorrente())));

        /*binding.button.setOnClickListener(v -> {
            new String(String.valueOf(viewModel.comprarCartaoCredito()));
        });*/

        //binding.button.setOnClickListener(String.valueOf(viewModel.comprarCartaoCredito()));
    }
}