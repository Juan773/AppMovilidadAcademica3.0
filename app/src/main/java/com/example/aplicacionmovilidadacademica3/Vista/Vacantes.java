 package com.example.aplicacionmovilidadacademica3.Vista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.aplicacionmovilidadacademica3.Presentador.Adapter.VacanteAdapter;
import com.example.aplicacionmovilidadacademica3.Presentador.Adapter.Interfaces.VacanteService;
import com.example.aplicacionmovilidadacademica3.Models.Vacante;
import com.example.aplicacionmovilidadacademica3.R;
import com.example.aplicacionmovilidadacademica3.TokenReceive.api.WebServiceOauth;
import com.example.aplicacionmovilidadacademica3.TokenReceive.share_pref.TokenManager;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class Vacantes extends AppCompatActivity  {

     RecyclerView recyclerView;
     List<Vacante> vacanteList;
    DrawerLayout drawerLayout;
     private static final String BASE_URL = "http://3.101.142.22:8888/";
     private OkHttpClient.Builder httpClientBuilder;
     private TokenManager tokenManager;
private TextView tv_prueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacantes);
        Button btn_req = (Button) findViewById(R.id.btn_req);
        btn_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(),Requisito.class);
                startActivityForResult(intent, 0);
            }
        });
        recyclerView = findViewById(R.id.vac_recycler_view);
        vacanteList = new ArrayList<>();
        setUpView();
    drawerLayout = findViewById(R.id.drawer_layout);
    }
     private void setUpView() {
         tokenManager = TokenManager.getInstance(getSharedPreferences(TokenManager.SHARED_PREFERENCES, MODE_PRIVATE));
         getVacantes();
     }

    public void ClickMenu(View view){
        Menu.openDrawer(drawerLayout);

     }
     @Override
     public void onBackPressed() {
         Toast.makeText(Vacantes.this,"",Toast.LENGTH_LONG).show();
     }

     public  void ClickLogo(View view){
        Menu.closeDrawer(drawerLayout);
     }
     public void ClickHome (View view){ Menu.redirectActivity(this, Menu.class);

     }
     public void ClickConvo(View view) { redirectActivity(this, Convocatorias.class);}
     public void ClickSolAlumnos(View view ){ redirectActivity(this,Solicitudes_Alumnos.class);}
     public void ClickVacantes (View view){

        recreate();
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


     private void getVacantes(){
         Call<List<Vacante>> call = WebServiceOauth
                 .getInstance()
                 .createService(VacanteService.class)
                 .getVacantes("Bearer" + tokenManager.getToken().getAccessToken());
         call.enqueue(new Callback<List<Vacante>>() {
             @Override
             public void onResponse(Call<List<Vacante>> call, Response<List<Vacante>> response) {
                 if(response.code() !=200){
                  return;
                 }
                 List <Vacante> vacantes = response.body();
                 for(Vacante vacante : vacantes){
                 //    String responseTest = "";
                     //Test
              //       responseTest += vacante.getIdvacante();
                 //    Log.v("Tag", ""+responseTest);
                   vacanteList.add(vacante);


                 }
                 PutDataIntoRecyclerView(vacanteList);


             }

             @Override
             public void onFailure(Call<List<Vacante>> call, Throwable t) {

             }


         });


 }

     private void PutDataIntoRecyclerView(List<Vacante> vacanteList) {

         VacanteAdapter vacanteAdapter = new VacanteAdapter(this,vacanteList);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));
         recyclerView.setAdapter(vacanteAdapter);

     }


 }