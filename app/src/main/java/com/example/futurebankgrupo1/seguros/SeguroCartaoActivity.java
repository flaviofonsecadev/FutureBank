package com.example.futurebankgrupo1.seguros;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.futurebankgrupo1.HomeActivity;
import com.example.futurebankgrupo1.databinding.ActivitySeguroCartaoBinding;
import com.example.futurebankgrupo1.usuario.UserFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SeguroCartaoActivity extends AppCompatActivity {

    private ActivitySeguroCartaoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySeguroCartaoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icClear.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        binding.cbStandard.setOnClickListener(v -> {
            binding.cbPlus.setChecked(false);
            binding.cbPremium.setChecked(false);
        });

        binding.cbPlus.setOnClickListener(v -> {
            binding.cbStandard.setChecked(false);
            binding.cbPremium.setChecked(false);
        });

        binding.cbPremium.setOnClickListener(v-> {
            binding.cbStandard.setChecked(false);
            binding.cbPlus.setChecked(false);
        });


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        String userID = user.getUid();
        binding.btnContratarSeguroCartao.setOnClickListener(view1 -> {

            reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    UserFirebase userProfile = snapshot.getValue(UserFirebase.class);
                    if (userProfile != null){
                        float saldo = userProfile.getSaldo();

                        if (binding.cbStandard.isChecked()){
                            if (saldo >= 2.90){
                                reference.child(userID).child("saldo").setValue(saldo - 2.90);
                                Toast.makeText(SeguroCartaoActivity.this, "Plano Standart contratado!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            }else {
                                Toast.makeText(SeguroCartaoActivity.this, "Saldo insuficiente!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        else if (binding.cbPlus.isChecked()){
                            if (saldo >= 4.90){
                                reference.child(userID).child("saldo").setValue(saldo - 4.90);
                                Toast.makeText(SeguroCartaoActivity.this, "Plano Plus contratado!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SeguroCartaoActivity.this, "Saldo insuficiente!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        else if (binding.cbPremium.isChecked()){
                            if (saldo >= 7.30){
                                reference.child(userID).child("saldo").setValue(saldo - 7.30);
                                Toast.makeText(SeguroCartaoActivity.this, "Plano Premium contratado!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SeguroCartaoActivity.this, "Saldo insuficiente!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else if(!binding.cbStandard.isChecked() || !binding.cbPlus.isChecked() || !binding.cbPremium.isChecked()) {
                            Toast.makeText(SeguroCartaoActivity.this, "Nenhum plano selecionado!", Toast.LENGTH_LONG).show();
                        }

                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(SeguroCartaoActivity.this, "Ocorreu algum erro, tente novamente!", Toast.LENGTH_LONG).show();
                }
            });

        });
    }
}