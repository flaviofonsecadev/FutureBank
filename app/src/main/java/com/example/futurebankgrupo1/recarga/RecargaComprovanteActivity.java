package com.example.futurebankgrupo1.recarga;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.futurebankgrupo1.HomeActivity;
import com.example.futurebankgrupo1.MyViewModel;
import com.example.futurebankgrupo1.databinding.ActivityRecargaComprovanteBinding;
import com.example.futurebankgrupo1.usuario.UserFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RecargaComprovanteActivity extends AppCompatActivity {

    private ActivityRecargaComprovanteBinding binding;
    private MyViewModel viewModel;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecargaComprovanteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.icClear.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        String telefone;
        String operadora;
        String valorRecarga;
        String tipoPagamento;

        SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);

        telefone = preferences.getString("chaveTelefone", "");
        binding.tvGetTelRecebedor.setText(telefone);

        operadora = preferences.getString("chaveOperadora", "");
        binding.tvGetOperRecebedora.setText(operadora);

        valorRecarga = preferences.getString("chaveValorRecarga", "");
        binding.tvGetValorRecarga.setText("R$" + valorRecarga);

        tipoPagamento = preferences.getString("chaveTipoPagamento", "");
        binding.tvGetConta1.setText(tipoPagamento);


//        //puxa dados tela p/ comprovante
//        binding.tvGetTelRecebedor.getText().toString();
//        binding.tvGetOperRecebedora.getText().toString();
//        binding.tvGetValorRecarga.getText().toString();
//        binding.tvGetConta1.getText().toString(); //tipo de pagamento
//        binding.tvGetPagador.getText().toString();


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

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
                Toast.makeText(RecargaComprovanteActivity.this, "Ocorreu algum erro com o nome do pagador!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}