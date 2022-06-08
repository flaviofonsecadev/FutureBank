package com.example.futurebankgrupo1.recycler;

public class RecyclerPoupanca {

    private String valorGuardado;
    private String data;
    private String valor;
    private int imagem;

    public RecyclerPoupanca(String valorGuardado, String data, String valor, int imagem) {
        this.valorGuardado = valorGuardado;
        this.data = data;
        this.valor = valor;
        this.imagem = imagem;
    }

    public String getValorGuardado() {
        return valorGuardado;
    }

    public void setValorGuardado(String valorGuardado) {
        this.valorGuardado = valorGuardado;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
