package com.example.futurebankgrupo1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
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

                    binding.tvCelular.setText(telefone);
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

        binding.button.setOnClickListener(v -> {
            float saldo = viewModel.exibirSaldoContaCorrente();
            if (saldo>=20){
                viewModel.setarSaldo(saldo - 20);
                Toast.makeText(this, "Recarga efetuada com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Tente novamente.", Toast.LENGTH_LONG).show();}


//            new String(String.valueOf(viewModel.comprarCartaoCredito()));
//            binding.tvGetSaldo.setText(df.format((viewModel.exibirSaldoContaCorrente())));

        });







        /*binding.button.setOnClickListener(v -> {
            new String(String.valueOf(viewModel.comprarCartaoCredito()));
        });*/

        //binding.button.setOnClickListener(String.valueOf(viewModel.comprarCartaoCredito()));

    }
}