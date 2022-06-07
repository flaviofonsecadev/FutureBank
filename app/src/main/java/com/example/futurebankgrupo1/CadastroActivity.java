package com.example.futurebankgrupo1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.futurebankgrupo1.databinding.ActivityCadastroBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class CadastroActivity extends AppCompatActivity {

    private ActivityCadastroBinding binding;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();

        binding.logo.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });

        binding.btnCriar.setOnClickListener(view1 -> {
            registerUser();
        });

    }

    private void registerUser() {
        String nome = binding.edtNome.getText().toString().trim();
        String senha = binding.edtSenha.getText().toString().trim();
        String email = binding.edtEmail.getText().toString().trim();
        String idade = binding.edtIdade.getText().toString().trim();
        String telefone = binding.edtTelefone.getText().toString().trim();
        String cpf = binding.edtCpf.getText().toString().trim();
        String cep = binding.edtCep.getText().toString().trim();
        String numero = binding.edtNumero.getText().toString().trim();
        String logradouro = binding.edtLogradouro.getText().toString().trim();
        String bairro = binding.edtBairro.getText().toString().trim();
        String cidade = binding.edtCidade.getText().toString().trim();
        String estado = binding.edtEstado.getText().toString().trim();
        String pais = binding.edtPais.getText().toString().trim();



        if(nome.isEmpty()){
            binding.edtNome.setError("Insira o seu nome!");
            binding.edtNome.requestFocus();
            return;
        }

        if (telefone.isEmpty()){
            binding.edtTelefone.setError("Insira o seu telefone!");
            binding.edtTelefone.requestFocus();
            return;
        }

        if (telefone.length() != 9 ){
            binding.edtTelefone.setError("Insira um telefone válido!");
            binding.edtTelefone.requestFocus();
            return;
        }

        if(cpf.isEmpty()){
            binding.edtCpf.setError("Insira o seu cpf!");
            binding.edtCpf.requestFocus();
            return;
        }

        if (cpf.length() != 11 ){
            binding.edtCpf.setError("O cpf deve ter no mínimo 11 números!");
            binding.edtCpf.requestFocus();
            return;
        }

        if(idade.isEmpty()){
            binding.edtIdade.setError("Insira a sua idade!");
            binding.edtIdade.requestFocus();
            return;
        }

        if(cep.isEmpty()){
            binding.edtCep.setError("Insira o seu cep!");
            binding.edtCep.requestFocus();
            return;
        }

        if(cep.length() != 8){
            binding.edtCep.setError("Insira um CEP válido!");
            binding.edtCep.requestFocus();
            return;
        }

        if(numero.isEmpty()){
            binding.edtNumero.setError("Insira o número da casa");
            binding.edtNumero.requestFocus();
            return;
        }

        if(logradouro.isEmpty()){
            binding.edtLogradouro.setError("Insira o seu logradouro!");
            binding.edtLogradouro.requestFocus();
            return;
        }

        if(bairro.isEmpty()){
            binding.edtBairro.setError("Insira o seu bairro!");
            binding.edtBairro.requestFocus();
            return;
        }

        if(cidade.isEmpty()){
            binding.edtCidade.setError("Insira a sua cidade!");
            binding.edtCidade.requestFocus();
            return;
        }

        if(estado.isEmpty()){
            binding.edtEstado.setError("Insira o Estado onde você mora!");
            binding.edtEstado.requestFocus();
            return;
        }

        if(pais.isEmpty()){
            binding.edtPais.setError("Insira o seu País!");
            binding.edtPais.requestFocus();
            return;
        }

        if(email.isEmpty()){
            binding.edtEmail.setError("Insira o seu email!");
            binding.edtEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.edtEmail.setError("Insira um email válido!");
            binding.edtEmail.requestFocus();
            return;
        }

        if (senha.isEmpty()){
            binding.edtSenha.setError("Insira uma senha!");
            binding.edtSenha.requestFocus();
            return;
        }

        if (senha.length() < 6 ){
            binding.edtSenha.setError("A senha deve ter no mínimo 6 caracteres!");
            binding.edtSenha.requestFocus();
            return;
        }

        binding.progressBarCadastro.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            User user = new User(nome, idade, email, cpf, telefone, cep, numero, logradouro, bairro, cidade, estado, pais);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()){
                                                Toast.makeText(CadastroActivity.this, "Yes!! Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                                                binding.progressBarCadastro.setVisibility(View.GONE);

                                                //redireciona para tela de login
                                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                                startActivity(intent);

                                            }else {
                                                Toast.makeText(CadastroActivity.this, "Oops! Ocorreu algum erro! Tente novamente!", Toast.LENGTH_LONG).show();
                                                binding.progressBarCadastro.setVisibility(View.GONE);
                                            }
                                        }
                                    });

                        } else {
                            Toast.makeText(CadastroActivity.this, "Oops! Ocorreu algum erro, tente novamente!", Toast.LENGTH_LONG).show();
                            binding.progressBarCadastro.setVisibility(View.GONE);
                        }
                    }
                });

    }
}