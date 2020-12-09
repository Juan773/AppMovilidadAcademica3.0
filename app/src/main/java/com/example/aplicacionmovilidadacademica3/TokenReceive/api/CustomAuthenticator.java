package com.example.aplicacionmovilidadacademica3.TokenReceive.api;

import android.util.Base64;

import androidx.annotation.Nullable;

import com.example.aplicacionmovilidadacademica3.TokenReceive.model.Token;
import com.example.aplicacionmovilidadacademica3.TokenReceive.share_pref.TokenManager;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;

public class CustomAuthenticator implements Authenticator {
    private TokenManager tokenManager;
    private static CustomAuthenticator INSTANCE;

    private CustomAuthenticator(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    public static synchronized CustomAuthenticator getInstance(TokenManager tokenManager) {
        if(INSTANCE == null) {
            INSTANCE = new CustomAuthenticator(tokenManager);
        }
        return INSTANCE;
    }

       /*
       El metodo authenticate se ejecuta cuando nuestro token de Acceso ha caducado y queremos obtener un nuevo token de Acceso con nuestro token de refresco.
       En caso que el token de refresco haya caducado nos deslogamos.
      */
    @Nullable
    @Override
    public Request authenticate(@Nullable Route route, Response response) throws IOException {
        String authHeader = "Basic " + Base64.encodeToString(("alejoelrey:alejoelmejor123456").getBytes(), Base64.NO_WRAP);

        Token token = tokenManager.getToken();

        Call<Token> call = WebServiceOauth
                .getInstance()
                .createService(WebServiceOauthApi.class)
                .obtenerTokenconRefreshToken(
                        authHeader,
                        token.getRefreshToken(),
                        "refresh_token"
                );

        retrofit2.Response<Token> response1 = call.execute();
        if(response1.isSuccessful()) {
            /*
            Si la respuesta es correcta, actualizamos el token de Acceso.
             */
            Token newtoken = response1.body();
            tokenManager.saveToken(newtoken);
            return response.request().newBuilder().header("Authorization", "Bearer" + response1.body().getAccessToken()).build();
        } else {
            /*
            Si la respuesta no es sucessfull, quiere decir que el token de refresco ha caducado
            y por lo tanto tendremos que deslogearnos de la aplicacion
            y pedir al usuario que vuelva a introducir sus credenciales.
             */
            return null;
        }
    }
}
