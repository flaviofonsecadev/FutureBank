package com.example.futurebankgrupo1;

public class User {
    public String nome, idade, email, cpf, telefone, cep, numero, logradouro, bairro, cidade, estado, pais;

    public User(){

    }

    public User(String nome, String idade, String email, String cpf, String telefone, String cep,
                String numero, String logradouro, String bairro, String cidade, String estado, String pais) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.cep = cep;
        this.numero = numero;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }
}
