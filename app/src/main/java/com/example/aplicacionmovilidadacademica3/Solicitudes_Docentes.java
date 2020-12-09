package com.example.aplicacionmovilidadacademica3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Solicitudes_Docentes extends AppCompatActivity {
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitudes__docentes);
        drawerLayout = findViewById(R.id.drawer_layout);
    }
    public void ClickMenu(View view){
        Menu.openDrawer(drawerLayout);

    }

    public  void ClickLogo(View view){
        Menu.closeDrawer(drawerLayout);
    }
    public void ClickHome (View view){ Menu.redirectActivity(this, Menu.class);

    }
    public void ClickConvo(View view) { redirectActivity(this,Convocatorias.class);}
    public void ClickSolAlumnos(View view ){   redirectActivity(this,Solicitudes_Alumnos.class);}
    public void ClickVacantes (View view){ redirectActivity(this,Vacantes.class);


    }
    public void ClickSolDoc(View view){ recreate();
    }
    public void ClickConvenios(View view){
        Menu.redirectActivity(this, Convenios.class);
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