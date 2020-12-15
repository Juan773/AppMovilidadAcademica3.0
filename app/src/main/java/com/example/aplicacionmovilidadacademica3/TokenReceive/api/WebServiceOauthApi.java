package com.example.aplicacionmovilidadacademica3.TokenReceive.api;

import com.example.aplicacionmovilidadacademica3.TokenReceive.model.Token;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface WebServiceOauthApi {

    @FormUrlEncoded
    @POST("/oauth/token")
    Call<Token> obtenerToken(
            @Header("Authorization") String authorization,
            @Field("username") String username,
            @Field("password") String password,
            @Field("grant_type") String grantType
    );

    @FormUrlEncoded
    @POST("/oauth/token")
    Call<Token> obtenerTokenconRefreshToken(
            @Header("Authorization") String authorization,
            @Field("refresh_token") String refreshToken,
            @Field("grant_type") String grantType
    );
}
