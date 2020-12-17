package com.example.aplicacionmovilidadacademica3.Vista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.aplicacionmovilidadacademica3.Presentador.Adapter.Solicitud_DocenteAdapter;
import com.example.aplicacionmovilidadacademica3.Presentador.Adapter.Interfaces.Solicitudes_DocenteService;
import com.example.aplicacionmovilidadacademica3.Models.Solicitud_Docente;
import com.example.aplicacionmovilidadacademica3.R;
import com.example.aplicacionmovilidadacademica3.TokenReceive.api.WebServiceOauth;
import com.example.aplicacionmovilidadacademica3.TokenReceive.share_pref.TokenManager;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Solicitudes_Docentes extends AppCompatActivity {
    DrawerLayout drawerLayout;
    List<Solicitud_Docente> solicitud_docenteList;
    RecyclerView recyclerView;
    private static final String BASE_URL = "http://3.101.142.22:8888/";
    private OkHttpClient.Builder httpClientBuilder;
    private TokenManager tokenManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitudes__docentes);
        drawerLayout = findViewById(R.id.drawer_layout);
        recyclerView = findViewById(R.id.sol_doc_recycler_view);
        solicitud_docenteList = new ArrayList<>();
        setUpView();
    }

    private void setUpView() {
        tokenManager = TokenManager.getInstance(getSharedPreferences(TokenManager.SHARED_PREFERENCES, MODE_PRIVATE));
        getSolicitud_docente();
    }



    public void ClickMenu(View view){
        Menu.openDrawer(drawerLayout);

    }

    public  void ClickLogo(View view){
        Menu.closeDrawer(drawerLayout);
    }
    public void ClickHome (View view){ Menu.redirectActivity(this, Menu.class);

    }
    public void ClickConvo(View view) { redirectActivity(this, Convocatorias.class);}
    public void ClickSolAlumnos(View view ){   redirectActivity(this,Solicitudes_Alumnos.class);}
    public void ClickVacantes (View view){ redirectActivity(this,Vacantes.class);


    }
    public void ClickSolDoc(View view){ recreate();
    }
    public void ClickConvenios(View view){
        Menu.redirectActivity(this, Universidades.class);
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

    private void getSolicitud_docente() {
        Call<List<Solicitud_Docente>> call = WebServiceOauth
                .getInstance()
                .createService(Solicitudes_DocenteService.class)
                .getSolicitud_Docente("Bearer"+ tokenManager.getToken().getAccessToken());

        call.enqueue(new Callback<List<Solicitud_Docente>>() {
            @Override
            public void onResponse(Call<List<Solicitud_Docente>> call, Response<List<Solicitud_Docente>> response) {
                if(response.code() !=200){
                    return;
                }
                List<Solicitud_Docente> solicitud_docentes = response.body();
                for(Solicitud_Docente solicitud_docente : solicitud_docentes){
                    solicitud_docenteList.add(solicitud_docente);
                }
                PutDataIntoRecyclerView(solicitud_docenteList);

            }

            @Override
            public void onFailure(Call<List<Solicitud_Docente>> call, Throwable t) {

            }
        });
    }

    private void PutDataIntoRecyclerView(List<Solicitud_Docente> solicitud_docenteList) {
        Solicitud_DocenteAdapter solicitud_docenteAdapter = new Solicitud_DocenteAdapter(this,solicitud_docenteList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(solicitud_docenteAdapter);
    }
}