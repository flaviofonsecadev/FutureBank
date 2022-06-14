package com.example.futurebankgrupo1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.futurebankgrupo1.databinding.ActivityDadosContaBinding;
import com.example.futurebankgrupo1.databinding.ActivityTelaConfiguracoesBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DadosConta extends AppCompatActivity {
    ActivityDadosContaBinding binding;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDadosContaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //botão voltar
        binding.icBack.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), TelaConfiguracoesActivity.class);
            startActivity(intent);
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    String nome = userProfile.getNome();
                    String cpf = userProfile.getCpf();
                    String idade = userProfile.getIdade();
                    String email = userProfile.getEmail();
                    String telefone = userProfile.getTelefone();
                    String logradouro = userProfile.getLogradouro();
                    String numero = userProfile.getNumero();
                    String bairro = userProfile.getBairro();
                    String cidade = userProfile.getCidade();
                    String estado = userProfile.getEstado();
                    String pais = userProfile.getPais();
                    String cep = userProfile.getCep();


                    binding.tvGetNomeUsuario.setText(nome);
                    binding.tvGetCpf.setText(cpf);
                    binding.tvGetDataNascimento.setText(idade);
                    binding.tvGetConta1.setText(email);
                    binding.tvGetTelefone.setText(telefone);
                    binding.tvGetLogradouro.setText(logradouro);
                    binding.tvGetNumero.setText(numero);
                    binding.tvGetBairro.setText(bairro);
                    binding.tvGetCidade.setText(cidade);
                    binding.tvGetEstado.setText(estado);
                    binding.tvGetPais.setText(pais);
                    binding.tvGetCep.setText(cep);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DadosConta.this, "Ocorreu algum erro!", Toast.LENGTH_SHORT).show();

            }
        });




    }
}