package com.example.futurebankgrupo1.contas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.futurebankgrupo1.HomeActivity;
import com.example.futurebankgrupo1.MyViewModel;
import com.example.futurebankgrupo1.R;
import com.example.futurebankgrupo1.usuario.UserFirebase;
import com.example.futurebankgrupo1.databinding.ActivityContaCorrenteBinding;
import com.example.futurebankgrupo1.recycler.RecyclerCorrente;
import com.example.futurebankgrupo1.recycler.AdapterCorrente;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ContaCorrenteActivity extends AppCompatActivity {

    private ActivityContaCorrenteBinding binding;
    private MyViewModel viewModel;

    Locale localeBR = new Locale( "pt", "BR" );
    NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);

    private RecyclerView recyclerView;
    private List<RecyclerCorrente> listaCorrente = new ArrayList<>();
    private AdapterCorrente adapterCorrente;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContaCorrenteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserFirebase userProfile = snapshot.getValue(UserFirebase.class);

                if (userProfile != null){
                    float saldo = userProfile.getSaldo();
                    float saldoPoupanca = userProfile.getSaldoPoupanca();

                    //binding.tvSaldoDisponivelCc.setText(String.valueOf("R$" + saldo));
                    binding.tvSaldoDisponivelCc.setText(dinheiroBR.format(saldo));
                    //binding.tvGetValorGuardadoCorrente.setText(String.valueOf("R$" + saldoPoupanca));
                    binding.tvGetValorGuardadoCorrente.setText(dinheiroBR.format(saldoPoupanca));

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ContaCorrenteActivity.this, "Ocorreu algum erro ao exibir saldo!", Toast.LENGTH_SHORT).show();
            }
        });

        /*String valorAplicar;
        String transacao;
        String data;
        SharedPreferences preferences = getSharedPreferences("chaveGeral", MODE_PRIVATE);
        valorAplicar = preferences.getString("chaveValorAplicar", "");
        transacao = preferences.getString("chaveTransacao", "");
        data = preferences.getString("chaveData", "");

        RecyclerCorrente recyclerCorrente = new RecyclerCorrente(transacao, valorAplicar, data);
        listaCorrente.add(recyclerCorrente);*/

        //conversão recycler
        recyclerView = findViewById(R.id.recyclerView);
        //this.criarListaCorrente();

        //configuração adapter
        adapterCorrente = new AdapterCorrente(ContaCorrenteActivity.this, listaCorrente);

        //configuração recycler
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterCorrente);

        readItemsAplicar();
        readItemsResgatar();



        binding.ivArrowForward1.setOnClickListener(v -> {
            Intent intent  = new Intent(getApplicationContext(), ContaPoupanca.class);
            startActivity(intent);
        });

        binding.tvValorGuardado.setOnClickListener(v -> {
            Intent intent  = new Intent(getApplicationContext(), ContaPoupanca.class);
            startActivity(intent);
        });

        binding.ivContaPoupanca.setOnClickListener(v -> {
            Intent intent  = new Intent(getApplicationContext(), ContaPoupanca.class);
            startActivity(intent);
        });

        binding.icClearCc.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        //forma mais resumida para mudar tela
        binding.tvAplicarCc.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AplicarCP.class));
        } );

        binding.ivArrowAplicarCc.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AplicarCP.class));
        } );

        binding.ivAplicarCc.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AplicarCP.class));
        } );

        binding.tvAplicarTitleCc.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AplicarCP.class));
        } );

        binding.tvResgatarCc.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ResgatarCC.class));
        } );

        binding.ivArrowResgatarCc.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ResgatarCC.class));
        } );

        binding.tvResgatarTitleCc.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ResgatarCC.class));
        } );

        binding.ivResgatarCc.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ResgatarCC.class));
        } );

    }

    private void readItemsAplicar(){

        //DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat dateFormat = DateFormat.getDateInstance();
        Calendar cal = Calendar.getInstance();
        String data = dateFormat.format(cal.getTime());

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("expenses").child(userID);
        Query query = reference.orderByChild("data").equalTo(data);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //listaCorrente.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    RecyclerCorrente recyclerCorrente = dataSnapshot.getValue(RecyclerCorrente.class);
                    listaCorrente.add(recyclerCorrente);
                }

                adapterCorrente.notifyDataSetChanged();

                //int totalAmount = 0;
//                for (DataSnapshot ds : snapshot.getChildren()){
//                    Map< String, Object> map = (Map<String, Object>) ds.getValue();
//                    Object total = map.get("amount");
//                    int pTotal = Integer.parseInt(String.valueOf(total));
//                    totalAmount+=pTotal;
//
//                    amountTxtview.setText("Total Day's Spending: $"+totalAmount);
//
//                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

   private void readItemsResgatar(){

        DateFormat dateFormat = DateFormat.getDateInstance();
        Calendar cal = Calendar.getInstance();
        String data = dateFormat.format(cal.getTime());

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("resgatar").child(userID);
        Query query = reference.orderByChild("data").equalTo(data);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //listaCorrente.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    RecyclerCorrente recyclerCorrente = dataSnapshot.getValue(RecyclerCorrente.class);
                    listaCorrente.add(recyclerCorrente);
                }
                adapterCorrente.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


}