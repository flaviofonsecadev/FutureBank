package com.example.futurebankgrupo1;

import com.example.futurebankgrupo1.viacep.ViaCEP;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserApi {

    //Autenticação do usuário - verifica se o cpf e senha estão corretos
    @POST("/auth")
    Call<User> autenticarUser(@Header("User") String cpf, String password);

    //Usuário
    @GET("/user/{userId}")
    Call<User> informacoesUser(@Path("userId") Integer id);

    @PUT("/user/{userId}")
    Call<User> atualizarUser(@Path("userId") Integer id);

    @DELETE("/user/{userId}")
    Call<User> excluirUser(@Path("userId") Integer id);

    /* exemplo do retrofit
    @GET("user")
    Call<User> getUser(@Header("Authorization") String authorization)
     */

    @POST("/user")
    Call<User> cadastrarUser(@Header("User") User user);

    @PUT("/user/password")
    Call<User> atualizarSenhaUser(@Header("User") String password);




}
