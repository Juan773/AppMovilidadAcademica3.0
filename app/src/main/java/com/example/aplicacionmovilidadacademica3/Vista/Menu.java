package com.example.aplicacionmovilidadacademica3.Vista;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.aplicacionmovilidadacademica3.R;

public class Menu extends AppCompatActivity {
     DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);

        ImageView imageButtonVac5 = (ImageView) findViewById(R.id.imageButtonVac5);
        ImageView imageButtonVac8 = (ImageView) findViewById(R.id. imageButtonVac8) ;
        ImageView imageButtonVac7 = (ImageView) findViewById(R.id.imageButtonVac7 );
        ImageView imageButtonVac6 = (ImageView)  findViewById(R.id.imageButtonVac6 );
        ImageView ImageButtonVac = (ImageView) findViewById(R.id. ImageButtonVac );
        imageButtonVac5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Solicitudes_Docentes.class);
                startActivityForResult(intent,0);
            }
        });
        imageButtonVac8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Solicitudes_Alumnos.class);
                startActivityForResult(intent,0);
            }
        });
        imageButtonVac7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Convocatorias.class);
                startActivityForResult(intent,0);
            }
        });
        imageButtonVac6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Universidades.class);
                startActivityForResult(intent,0);
            }
        });
        ImageButtonVac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Vacantes.class);
                startActivityForResult(intent,0);
            }
        });





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
    public void ClickHome(View view ){
        recreate();
    }
    public void ClickConvo(View view) { redirectActivity(this,Convocatorias.class);}
    public void ClickSolAlumnos(View view ){ redirectActivity(this,Solicitudes_Alumnos.class);}
    public void ClickSolDoc(View view){ redirectActivity(this,Solicitudes_Docentes.class);
    }
    public void ClickVacantes(View view){
        redirectActivity(this, Vacantes.class);
    }
    public void ClickConvenios(View view){
        redirectActivity(this, Universidades.class);
    }
    public void ClickLogout(View view){
        logout(this);
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
}