package com.example.futurebankgrupo1;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyViewModel extends ViewModel {

    private MutableLiveData<Integer> saldoContaCorrente = new MutableLiveData<>();

    //public LiveData<Float> valorFA = valorFaturaAtual;
    private MutableLiveData<Integer> saldoContaPoupanca = new MutableLiveData<>();

    private MutableLiveData<Integer> valorFaturaAtual = new MutableLiveData<>();

    private MutableLiveData<Integer> limiteDisponivel = new MutableLiveData<>();
    //public LiveData<Float> limiteD = limiteDisponivel;

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    public FirebaseUser getUser() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        return user;
    }

    public DatabaseReference getReference() {
        reference = FirebaseDatabase.getInstance().getReference("Users");
        return reference;
    }

    public String getUserID() {
        userID = user.getUid();
        return userID;
    }



    public MyViewModel() {
        limiteDisponivel.setValue(1000);
        valorFaturaAtual.setValue(55);
        saldoContaCorrente.setValue(5000);
        saldoContaPoupanca.setValue(2500);
    }

    public float exibirValorFatura() {
        float valor = valorFaturaAtual.getValue();
        return valor;
    }

    public float exibirLimite() {
        float limite = limiteDisponivel.getValue();
        return limite;
    }



    float saldo;

    public float exibirSaldoContaCorrente() {
        getReference().child(getUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    saldo = userProfile.getSaldo();

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("Erro", error.getMessage());
            }
        });
        return saldo;
    }

    public void setarSaldo (float novoSaldo){
        getReference().child(getUser().getUid()).child("saldo").setValue(novoSaldo);

    }


    float limiteCartao;
    public float exibirLimiteCartao() {
        getReference().child(getUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    limiteCartao = userProfile.getSaldo();

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("Erro", error.getMessage());
            }
        });
        return limiteCartao;
    }



    float saldoPoupanca;
    public float exibibirSaldoPoupanca() {
        getReference().child(getUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    saldoPoupanca = userProfile.getSaldo();

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("Erro", error.getMessage());
            }
        });
        return saldoPoupanca;
    }



    float valorFatura;
    public float exibirValorFaturaFirebase() {
        getReference().child(getUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null){
                    valorFatura = userProfile.getSaldo();

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("Erro", error.getMessage());
            }
        });
        return valorFatura;
    }



    public float exibirSaldoContaPoupanca() {
        float saldoCP = saldoContaPoupanca.getValue();
        return saldoCP;
    }

    /*public void comprarCredito() {
    int compra = 100;
    valorFaturaAtual.setValue(valorFaturaAtual.getValue() + compra);
    limiteDisponivel.setValue(limiteDisponivel.getValue() - compra);
    }*/
}