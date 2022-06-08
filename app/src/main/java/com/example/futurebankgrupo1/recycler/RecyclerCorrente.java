package com.example.futurebankgrupo1.recycler;

public class RecyclerCorrente {

    private String transacaoCreditoDebito;
    private String valor;
    private String data;
    private int imagem;

    public RecyclerCorrente(String transacaoCreditoDebito, String valor, String data, int imagem) {
        this.transacaoCreditoDebito = transacaoCreditoDebito;
        this.valor = valor;
        this.data = data;
        this.imagem = imagem;
    }

    public String getTransacaoCreditoDebito() {
        return transacaoCreditoDebito;
    }

    public void setTransacaoCreditoDebito(String transacaoCreditoDebito) {
        this.transacaoCreditoDebito = transacaoCreditoDebito;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
