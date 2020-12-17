package com.example.aplicacionmovilidadacademica3.Vista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;

import com.example.aplicacionmovilidadacademica3.R;

public class Requisito extends AppCompatActivity {
    DrawerLayout drawerLayout;
    private ImageView btn_soli,btn_carta;
    private String url,url1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisito);
        drawerLayout = findViewById(R.id.drawer_layout);
        btn_soli = findViewById(R.id.btn_soli);
        btn_carta = findViewById(R.id.btn_carta);
        url1="https://drive.google.com/file/d/1c9maAs-jAdPPbuwSDjDGQJW12znBddlI/view";

        url="https://drive.google.com/file/d/1c9maAs-jAdPPbuwSDjDGQJW12znBddlI/view";
        btn_soli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        btn_carta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(url1);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

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
        redirectActivity(this, Universidades.class);
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
}