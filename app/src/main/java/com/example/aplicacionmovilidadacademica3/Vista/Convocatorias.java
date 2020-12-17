package com.example.aplicacionmovilidadacademica3.Vista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.aplicacionmovilidadacademica3.Presentador.Adapter.ConvocatoriaAdapter;
import com.example.aplicacionmovilidadacademica3.Presentador.Adapter.Interfaces.ConvocatoriaService;
import com.example.aplicacionmovilidadacademica3.Models.Convocatoria;
import com.example.aplicacionmovilidadacademica3.R;
import com.example.aplicacionmovilidadacademica3.TokenReceive.api.WebServiceOauth;
import com.example.aplicacionmovilidadacademica3.TokenReceive.share_pref.TokenManager;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Convocatorias extends AppCompatActivity {
    RecyclerView recyclerView;
    DrawerLayout drawerLayout;

    List<Convocatoria> convocatoriaList;

    private static final String BASE_URL = "http://3.101.142.22:8888/";
    private OkHttpClient.Builder httpClientBuilder;
    private TokenManager tokenManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_convocatorias);
        drawerLayout = findViewById(R.id.drawer_layout);
        recyclerView = findViewById(R.id.convo_recycler_view);
        convocatoriaList = new ArrayList<>();
        setUpView();



    }
    @Override
    public void onBackPressed() {
        Toast.makeText(Convocatorias.this,"",Toast.LENGTH_LONG).show();
    }



    private void setUpView() {
        tokenManager = TokenManager.getInstance(getSharedPreferences(TokenManager.SHARED_PREFERENCES, MODE_PRIVATE));
        getConvocatorias();
    }



    public void ClickMenu(View view){
        openDrawer(drawerLayout);

    }

    public static void openDrawer(DrawerLayout drawerLayout) {

        drawerLayout.openDrawer(GravityCompat.START);
    }
    public void ClickLogo(View view){
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){

            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public void ClickHome (View view){ Menu.redirectActivity(this, Menu.class);

    }
    public void ClickConvo(View view) { recreate();}
    public void ClickVacantes(View view){
        redirectActivity(this, Vacantes.class);
    }
    public void ClickConvenios(View view){
        redirectActivity(this, Universidades.class);
    }
    public void ClickSolAlumnos(View view ){ redirectActivity(this, Solicitudes_Alumnos.class);}
    public void ClickLogout(View view){
        logout(this);
    }
    public void ClickSolDoc(View view){ redirectActivity(this, Solicitudes_Docentes.class);
    }

    public static void logout(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Logout");
        builder.setMessage("Are you seguro ? xd");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public static void redirectActivity(Activity activity,Class aClass){
        Intent intent = new Intent(activity,aClass);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);

    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
    private void getConvocatorias() {
        Call<List<Convocatoria>> call = WebServiceOauth
                .getInstance()
                .createService(ConvocatoriaService.class)
                .getConvocatorias("Bearer"+ tokenManager.getToken().getAccessToken());
        call.enqueue(new Callback<List<Convocatoria>>() {
            @Override
            public void onResponse(Call<List<Convocatoria>> call, Response<List<Convocatoria>> response) {
                if(response.code() !=200){
                    return;
                }
                List<Convocatoria> convocatorias = response.body();
                for (Convocatoria convocatoria : convocatorias){
                    convocatoriaList.add(convocatoria);
                }
                PutDataIntoRecyclerView(convocatoriaList);

            }

            @Override
            public void onFailure(Call<List<Convocatoria>> call, Throwable t) {

            }
        });
    }

    private void PutDataIntoRecyclerView(List<Convocatoria> convocatoriaList) {

        ConvocatoriaAdapter convocatoriaAdapter = new ConvocatoriaAdapter(this,convocatoriaList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(convocatoriaAdapter);
    }



}