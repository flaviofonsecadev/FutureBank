package com.example.futurebankgrupo1.fatura.pagarfatura;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.futurebankgrupo1.HomeActivity;
import com.example.futurebankgrupo1.MyViewModel;
import com.example.futurebankgrupo1.User;
import com.example.futurebankgrupo1.databinding.ActivityPagarFaturaBinding;
import com.example.futurebankgrupo1.pagarcompix.TelaPagar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.Locale;

public class PagarFatura extends AppCompatActivity {

    private ActivityPagarFaturaBinding binding;
    private MyViewModel viewModel;

    Locale localeBR = new Locale( "pt", "BR" );
    NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPagarFaturaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



        binding.icClear.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        binding.ivArrowForward.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PagarFaturaConfirmarValor.class);
            startActivity(intent);
        });

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        //binding.tvGetValorFatura.setText(String.valueOf(viewModel.exibirValorFatura()));
        binding.tvGetValorFatura.setText(dinheiroBR.format(viewModel.exibirValorFatura()));
    }
}