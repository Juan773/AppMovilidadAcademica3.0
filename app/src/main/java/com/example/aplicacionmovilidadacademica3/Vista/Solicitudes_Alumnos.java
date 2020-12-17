package com.example.aplicacionmovilidadacademica3.Vista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.aplicacionmovilidadacademica3.Presentador.Adapter.Solicitud_AlumnoAdapter;
import com.example.aplicacionmovilidadacademica3.Presentador.Adapter.Interfaces.Solicitudes_AlumnosService;
import com.example.aplicacionmovilidadacademica3.Models.Solicitud_Alumno;
import com.example.aplicacionmovilidadacademica3.R;
import com.example.aplicacionmovilidadacademica3.TokenReceive.api.WebServiceOauth;
import com.example.aplicacionmovilidadacademica3.TokenReceive.share_pref.TokenManager;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Solicitudes_Alumnos extends AppCompatActivity {
    DrawerLayout drawerLayout;
    List<Solicitud_Alumno> solicitud_alumnoList;
    RecyclerView recyclerView;
    private static final String BASE_URL = "http://3.101.142.22:8888/";
    private OkHttpClient.Builder httpClientBuilder;
    private TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitudes__alumnos);
        drawerLayout = findViewById(R.id.drawer_layout);
        recyclerView = findViewById(R.id.sol_alu_recycler_view);
        solicitud_alumnoList = new ArrayList<>();
        setUpView();
    }
    @Override
    public void onBackPressed() {
        Toast.makeText(Solicitudes_Alumnos.this,"",Toast.LENGTH_LONG).show();
    }

    private void setUpView() {
        tokenManager = TokenManager.getInstance(getSharedPreferences(TokenManager.SHARED_PREFERENCES, MODE_PRIVATE));
        getSolicitud_alumno();
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
    public void ClickSolAlumnos(View view ){   recreate();}
    public void ClickVacantes (View view){ redirectActivity(this,Vacantes.class);


    }
    public void ClickSolDoc(View view){ redirectActivity(this,Solicitudes_Docentes.class);
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

    private void getSolicitud_alumno(){
        Call<List<Solicitud_Alumno>>call = WebServiceOauth
                .getInstance()
                .createService(Solicitudes_AlumnosService.class)
                .getSolicitud_alumno("Bearer" + tokenManager.getToken().getAccessToken());

        call.enqueue(new Callback<List<Solicitud_Alumno>>() {
            @Override
            public void onResponse(Call<List<Solicitud_Alumno>> call, Response<List<Solicitud_Alumno>> response) {
                if(response.code() !=200){
                    return;
                }
                List<Solicitud_Alumno> solicitud_alumnos = response.body();
                for (Solicitud_Alumno solicitud_alumno : solicitud_alumnos){
                    solicitud_alumnoList.add(solicitud_alumno);
                }
                PutDataIntoRecyclerView(solicitud_alumnoList);
            }

            @Override
            public void onFailure(Call<List<Solicitud_Alumno>> call, Throwable t) {

            }
        });
    }

    private void PutDataIntoRecyclerView(List<Solicitud_Alumno> solicitud_alumnoList) {
        Solicitud_AlumnoAdapter solicitud_alumnoAdapter = new Solicitud_AlumnoAdapter(this,solicitud_alumnoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(solicitud_alumnoAdapter);
    }
}