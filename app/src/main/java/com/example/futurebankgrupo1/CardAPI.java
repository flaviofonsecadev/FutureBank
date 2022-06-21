package com.example.futurebankgrupo1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CardAPI {

    //Retorna a fatura do cartão especificado
    @GET("/card/credit/bill/{cardId}")
    Call<Card> retornarFaturaCartaoEspecificado(@Path("cardId") int id);

    //Altera o status da fatura
    @PUT("/card/credit/bill/{cardId}")
    Call<Card> alterarStatusFatura(@Path("cardId") int id);

    //Cria um novo cartão
    @GET("/card/{accountId}")
    Call<Card> criarNovoCartao(@Path("accountId") int id);

    //Retorna os dados de um cartao especifico
    @GET("/card/{cardId}")
    Call<List<Card>> retornarDadosDeUmCartaoEspecifico(@Path("cardId") int id);
}
