package com.example.futurebankgrupo1;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private MutableLiveData<Integer> saldoContaCorrente = new MutableLiveData<>();

    private MutableLiveData<Integer> saldoContaPoupanca = new MutableLiveData<>();

    private MutableLiveData<Integer> valorFaturaAtual = new MutableLiveData<>();
    //public LiveData<Float> valorFA = valorFaturaAtual;

    private MutableLiveData<Integer> limiteDisponivel = new MutableLiveData<>();
    //public LiveData<Float> limiteD = limiteDisponivel;



    public MyViewModel() {
        limiteDisponivel.setValue(1000);
        valorFaturaAtual.setValue(0);
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

    public float exibirSaldoContaCorrente() {
        float saldoCC = saldoContaCorrente.getValue();
        return saldoCC;
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