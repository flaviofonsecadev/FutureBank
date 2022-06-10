package com.example.futurebankgrupo1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {



    public MutableLiveData<Double> saldoContaCorrente = new MutableLiveData<>();
    public LiveData<Double> saldoCC = saldoContaCorrente;

    public MutableLiveData<Integer> saldoContaPoupanca = new MutableLiveData<>();

    public MutableLiveData<Integer> valorFaturaAtual = new MutableLiveData<>();

    public MutableLiveData<Integer> limiteDisponivel = new MutableLiveData<>();
    //public LiveData<Float> limiteD = limiteDisponivel;



    public MyViewModel() {
        limiteDisponivel.setValue(1000);
        valorFaturaAtual.setValue(0);
        saldoContaCorrente.setValue(5000.00d);
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

    public double exibirSaldoContaCorrente() {
        double saldoCC = saldoContaCorrente.getValue();
        return saldoCC;
    }

    public float exibirSaldoContaPoupanca() {
        float saldoCP = saldoContaPoupanca.getValue();
        return saldoCP;
    }

    public MutableLiveData<Double> comprarCartaoCredito() {
        double saldo = saldoContaCorrente.getValue();
        double compra = 20;

        saldoContaCorrente.setValue(saldo - compra);

        return saldoContaCorrente;

    }

    /*public void comprarCredito() {
    int compra = 100;
    valorFaturaAtual.setValue(valorFaturaAtual.getValue() + compra);
    limiteDisponivel.setValue(limiteDisponivel.getValue() - compra);
    }*/
}