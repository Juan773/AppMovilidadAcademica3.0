package com.example.aplicacionmovilidadacademica3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aplicacionmovilidadacademica3.Interfaces.UniversidadService;
import com.example.aplicacionmovilidadacademica3.Models.Universidad;
import com.example.aplicacionmovilidadacademica3.TokenReceive.api.WebServiceOauth;
import com.example.aplicacionmovilidadacademica3.TokenReceive.api.WebServiceOauthApi;
import com.example.aplicacionmovilidadacademica3.TokenReceive.model.Token;
import com.example.aplicacionmovilidadacademica3.TokenReceive.share_pref.TokenManager;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    Button btn_login;
    EditText user, pass;
    private TokenManager tokenManager;
    UniversidadService universidadService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button  gomenu = (Button) findViewById(R.id.sigin);

        btn_login = findViewById(R.id.sigin);
        user = findViewById(R.id.userName);
        pass = findViewById(R.id.password);

        setUpView();

    }

    private void setUpView() {
        tokenManager = TokenManager.getInstance(getSharedPreferences(TokenManager.SHARED_PREFERENCES, MODE_PRIVATE));
        btn_login.setOnClickListener((v) -> {
            obtenerToken();
            getUniversidad();
        });
    }

    Token token = new Token();
    private void obtenerToken() {
        String authHeader = "Basic " + Base64.encodeToString(("alejoelrey:alejoelmejor123456").getBytes(), Base64.NO_WRAP);
        Call<Token> call = WebServiceOauth
                .getInstance()
                .createService(WebServiceOauthApi.class)
                .obtenerToken(
                        authHeader,
                        user.getText().toString(),
                        pass.getText().toString(),
                        "password"
                );

        call.enqueue(new Callback<Token>() {

            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if(response.code()==200) {
                    Log.d("TAG1", "Access Token: " + response.body().getAccessToken()
                            +" Refresh Token: " + response.body().getRefreshToken());

                    token = response.body();
                    tokenManager.saveToken(token);


                    startActivity(new Intent(Login.this, Menu.class));
                    new SweetAlertDialog(Login.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Bienvenido "  + user.getText().toString())
                            .show();
                    // Toast.makeText(MainActivity.this, "Logeado", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("TAG1", "Error");
                    new SweetAlertDialog(Login.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error al logearse.. vuelva a intentarlo")
                            .show();
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

            }
        });
    }

    void getUniversidad() {
        Call<List<Universidad>> call = WebServiceOauth
                .getInstance()
                .createService(UniversidadService.class)
                .getUniversidad("Bearer " + tokenManager.getToken().getAccessToken());

        call.enqueue(new Callback<List<Universidad>>() {
            @Override
            public void onResponse(Call<List<Universidad>> call, Response<List<Universidad>> response) {
                if(response.code()==200){
                    for (int i=0; i<response.body().size(); i++){
                        Log.d("TAG1 ", "iduniversidad: " + response.body().get(i).getIduniversidad() +
                                "nombre: " + response.body().get(i).getNombre() +
                                "direccion: " + response.body().get(i).getDireccion() +
                                "país: " + response.body().get(i).getPais() +
                                "totalVacantes" + response.body().get(i).getTotalvacantes());
                    }
                } else {
                    Log.d("TAG1", "Error");
                }
            }

            @Override
            public void onFailure(Call<List<Universidad>> call, Throwable t) {

            }
        });
    }
}