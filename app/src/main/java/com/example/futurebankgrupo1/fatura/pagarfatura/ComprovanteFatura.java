package com.example.futurebankgrupo1.fatura.pagarfatura;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.futurebankgrupo1.HomeActivity;
import com.example.futurebankgrupo1.MyViewModel;
import com.example.futurebankgrupo1.usuario.UserFirebase;
import com.example.futurebankgrupo1.databinding.ActivityComprovanteFaturaBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.Locale;

public class ComprovanteFatura extends AppCompatActivity {

    private ActivityComprovanteFaturaBinding binding;
    private MyViewModel viewModel;
    Locale localeBR = new Locale( "pt", "BR" );
    NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityComprovanteFaturaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icClear.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

//        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
//
//        binding.tvGetValorPago.setText(String.valueOf(viewModel.exibirValorFatura()));

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        String userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserFirebase userProfile = snapshot.getValue(UserFirebase.class);

                if (userProfile != null){
                    String nome = userProfile.getNome();

                    binding.tvGetPagador.setText(nome);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ComprovanteFatura.this, "Ocorreu algum erro com o nome do pagador!", Toast.LENGTH_SHORT).show();
            }
        });

        /*int day;
        int month;
        int year;
        SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);
        day = preferences.getInt("chaveDay",0);
        month = preferences.getInt("chaveMonth",0);
        year= preferences.getInt("chaveYear", 0);
        binding.tvGetDataHora.setText(day + "/" +month+"/"+year);*/


        Intent intentReceberDados = getIntent();
        Bundle receberDados = intentReceberDados.getExtras();

        if (receberDados != null) {
            Float valorFatura = receberDados.getFloat("valorFatura");
            binding.tvGetValorPago.setText(dinheiroBR.format(valorFatura));
        }



    }
}