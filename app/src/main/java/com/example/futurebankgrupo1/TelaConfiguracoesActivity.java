package com.example.futurebankgrupo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.futurebankgrupo1.databinding.ActivityTelaConfiguracoesBinding;
import com.google.firebase.auth.FirebaseAuth;

public class TelaConfiguracoesActivity extends AppCompatActivity {

    private ActivityTelaConfiguracoesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTelaConfiguracoesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //botão fechar
        binding.icClear.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        //botão perfil usuário
        binding.ivArrowForward2.setOnClickListener(v -> {
            Intent intent =  new Intent(getApplicationContext(), DadosConta.class);
            startActivity(intent);
        });

        binding.tvPerfilUsuario.setOnClickListener(v -> {
            Intent intent =  new Intent(getApplicationContext(), DadosConta.class);
            startActivity(intent);
        });

        binding.icConfigUser.setOnClickListener(v -> {
            Intent intent =  new Intent(getApplicationContext(), DadosConta.class);
            startActivity(intent);
        });


        //botão configurações cartão
        binding.icConfigCard.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MeusCartoesActivity.class);
            startActivity(intent);
        });

        binding.tvConfigCard.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MeusCartoesActivity.class);
            startActivity(intent);
        });

        binding.ivArrowForward3.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MeusCartoesActivity.class);
            startActivity(intent);
        });


        //botão configurações app
        binding.icConfigApp.setOnClickListener(view12 -> {
            Intent intent = new Intent(getApplicationContext(), ConfiguracoesApp.class);
            startActivity(intent);
        });

        binding.tvConfigApp.setOnClickListener(view12 -> {
            Intent intent = new Intent(getApplicationContext(), ConfiguracoesApp.class);
            startActivity(intent);
        });

        binding.ivArrowConfigApp.setOnClickListener(view12 -> {
            Intent intent = new Intent(getApplicationContext(), ConfiguracoesApp.class);
            startActivity(intent);
        });

        //botão segurança
        binding.icConfigSecurity.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Security.class);
            startActivity(intent);
        });

        binding.tvConfigSecurity.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Security.class);
            startActivity(intent);
        });

        binding.ivArrowForward4.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Security.class);
            startActivity(intent);
        });


        //botão sair
        binding.icConfigSair.setOnClickListener(view1 -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
            startActivity(intent);
        });

        binding.tvConfigSair.setOnClickListener(view1 -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
            startActivity(intent);
        });


    }

}
