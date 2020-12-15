package com.example.aplicacionmovilidadacademica3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aplicacionmovilidadacademica3.Adapter.UniversidadAdapter;
import com.example.aplicacionmovilidadacademica3.Interfaces.UniversidadService;
import com.example.aplicacionmovilidadacademica3.Models.Universidad;
import com.example.aplicacionmovilidadacademica3.TokenReceive.api.WebServiceOauth;
import com.example.aplicacionmovilidadacademica3.TokenReceive.share_pref.TokenManager;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Universidades extends AppCompatActivity {
    RecyclerView recyclerView;
    DrawerLayout drawerLayout;
    private static final String BASE_URL = "http://192.168.0.101:8888";
    private OkHttpClient.Builder httpClientBuilder;
    List<Universidad> universidadList;
    private TokenManager tokenManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convenios);
        drawerLayout = findViewById(R.id.drawer_layout);
        recyclerView = findViewById(R.id.conve_recycler_view);
        universidadList = new ArrayList<>();
        setUpView();
    }

    private void setUpView() {
        tokenManager = TokenManager.getInstance(getSharedPreferences(TokenManager.SHARED_PREFERENCES, MODE_PRIVATE));
        getUniversidad();
    }

    public void ClickMenu(View view){
        Menu.openDrawer(drawerLayout);

    }

    public  void ClickLogo(View view){
        Menu.closeDrawer(drawerLayout);
    }
    public void ClickHome (View view){
        Menu.redirectActivity(this, Menu.class);

    }

    public void ClickVacantes (View view){ redirectActivity(this,Vacantes.class);


    }   public void ClickSolAlumnos(View view ){ redirectActivity(this,Solicitudes_Alumnos.class);}
    public void ClickConvenios(View view){
        recreate();
    }
    public void ClickSolDoc(View view){ redirectActivity(this,Solicitudes_Docentes.class);
    }
    public void ClickLogout (View view){

        Menu.logout(this);
    }
    public static void redirectActivity(Activity activity, Class aClass){
        Intent intent = new Intent(activity,aClass);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Menu.closeDrawer(drawerLayout);
    }
    void getUniversidad() {
        Call<List<Universidad>> call = WebServiceOauth
                .getInstance()
                .createService(UniversidadService.class)
                .getUniversidad("Bearer " + tokenManager.getToken().getAccessToken());

        call.enqueue(new Callback<List<Universidad>>() {
            @Override
            public void onResponse(Call<List<Universidad>> call, Response<List<Universidad>> response) {
                if(response.code() !=200){
                    return;
                
                } List <Universidad> universidads = response.body();
                for (Universidad universidad : universidads){
                    
                    universidadList.add(universidad);
                }
                PutDataIntoRecyclerView(universidadList);
            }

            @Override
            public void onFailure(Call<List<Universidad>> call, Throwable t) {

            }
        });
    }

    private void PutDataIntoRecyclerView(List<Universidad> universidadList) {

        UniversidadAdapter universidadAdapter = new UniversidadAdapter(this,universidadList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(universidadAdapter);
    }
}