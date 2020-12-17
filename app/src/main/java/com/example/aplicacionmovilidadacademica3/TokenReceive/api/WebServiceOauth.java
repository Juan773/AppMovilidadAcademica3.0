package com.example.aplicacionmovilidadacademica3.TokenReceive.api;

import com.example.aplicacionmovilidadacademica3.TokenReceive.share_pref.TokenManager;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServiceOauth {
    private static final String BASE_URL = "http://3.101.142.22:8888/";
    private static HttpLoggingInterceptor loggingInterceptor;
    private Retrofit retrofit;
    private OkHttpClient.Builder httpClientBuilder;
    private static WebServiceOauth instance;

    private WebServiceOauth() {
        httpClientBuilder = new OkHttpClient.Builder();
        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized WebServiceOauth getInstance() {
        if(instance == null) {
            instance = new WebServiceOauth();
        }
        return instance;
    }

    public <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

    public <S> S createServiceWithOuth2(Class<S> serviceClass, final TokenManager tokenManager){
        final OkHttpClient newClient = httpClientBuilder.addInterceptor((chain) -> {
            Request requestOriginal = chain.request();
            Request.Builder builder = requestOriginal.newBuilder();

            if(tokenManager.getToken().getAccessToken() != null) {
                builder.addHeader("Authorization", "Bearer" + tokenManager.getToken().getAccessToken());
            }

            Request request = builder.build();
            return chain.proceed(request);
        }).authenticator(CustomAuthenticator.getInstance(tokenManager)).build();

        return retrofit.newBuilder().client(newClient).build().create(serviceClass);
    }
}
