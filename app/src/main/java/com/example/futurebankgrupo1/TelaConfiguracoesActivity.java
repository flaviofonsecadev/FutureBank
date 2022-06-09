package com.example.futurebankgrupo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.futurebankgrupo1.databinding.ActivityTelaConfiguracoesBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TelaConfiguracoesActivity extends AppCompatActivity {

    private ActivityTelaConfiguracoesBinding binding;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTelaConfiguracoesBinding.inflate(getLayoutInflater());
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
                    String nome = userProfile.nome;

                    binding.tvNome.setText(nome);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TelaConfiguracoesActivity.this, "Ocorreu algum erro!", Toast.LENGTH_SHORT).show();
            }
        });


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

        binding.tvPerfilUsuario.setOnClickListener(view123 -> {
            Intent intent = new Intent(getApplicationContext(), DadosConta.class);
            startActivity(intent);
        });
        //arrow perfil usuario
        binding.ivArrowForward2.setOnClickListener(view123 -> {
            Intent intent = new Intent(getApplicationContext(), DadosConta.class);
            startActivity(intent);
        });

        //arrow segurança
        binding.ivArrowForward4.setOnClickListener(view123 -> {
            Intent intent = new Intent(getApplicationContext(), ResetarSenha.class);
            startActivity(intent);
        });


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
