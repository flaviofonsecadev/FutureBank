package com.example.futurebankgrupo1.seguros;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.futurebankgrupo1.HomeActivity;
import com.example.futurebankgrupo1.contas.ContaCorrenteActivity;
import com.example.futurebankgrupo1.databinding.ActivityHomeBinding;
import com.example.futurebankgrupo1.databinding.ActivitySeguroVidaBinding;
import com.example.futurebankgrupo1.fatura.pagarfatura.ComprovanteFatura;
import com.example.futurebankgrupo1.fatura.pagarfatura.PagarFaturaConfirmarValor;
import com.example.futurebankgrupo1.usuario.UserFirebase;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SeguroVida extends AppCompatActivity {

    private ActivitySeguroVidaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySeguroVidaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icClear.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        binding.cbIndividual.setOnClickListener(v -> {
            binding.cbPessoas.setChecked(false);
            binding.cbFamilia.setChecked(false);
        });

        binding.cbPessoas.setOnClickListener(v -> {
            binding.cbIndividual.setChecked(false);
            binding.cbFamilia.setChecked(false);
        });

        binding.cbFamilia.setOnClickListener(v-> {
            binding.cbIndividual.setChecked(false);
            binding.cbPessoas.setChecked(false);
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        String userID = user.getUid();
        binding.btnContratarSeguroVida.setOnClickListener(view1 -> {

            reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    UserFirebase userProfile = snapshot.getValue(UserFirebase.class);
                    if (userProfile != null){
                        float saldo = userProfile.getSaldo();

                        if (binding.cbIndividual.isChecked()){
                            if (saldo >= 37.90){
                                reference.child(userID).child("saldo").setValue(saldo - 37.90);
                                Toast.makeText(SeguroVida.this, "Plano individual contratado!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                    startActivity(intent);
                            }else {
                                Toast.makeText(SeguroVida.this, "Saldo insuficiente!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        else if (binding.cbPessoas.isChecked()){
                            if (saldo >= 43.90){
                                reference.child(userID).child("saldo").setValue(saldo - 43.90);
                                Toast.makeText(SeguroVida.this, "Plano para duas pessoas contratado!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SeguroVida.this, "Saldo insuficiente!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        else if (binding.cbFamilia.isChecked()){
                            if (saldo >= 59.90){
                                reference.child(userID).child("saldo").setValue(saldo - 59.90);
                                Toast.makeText(SeguroVida.this, "Plano família contratado!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SeguroVida.this, "Saldo insuficiente!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else if(!binding.cbIndividual.isChecked() || !binding.cbPessoas.isChecked() || !binding.cbFamilia.isChecked()) {
                            Toast.makeText(SeguroVida.this, "Nenhum plano selecionado!", Toast.LENGTH_LONG).show();
                        }

                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(SeguroVida.this, "Ocorreu algum erro, tente novamente!", Toast.LENGTH_LONG).show();
                }
            });

        });

    }
}