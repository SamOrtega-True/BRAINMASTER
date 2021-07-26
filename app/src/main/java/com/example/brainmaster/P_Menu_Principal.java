package com.example.brainmaster;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class P_Menu_Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_menu_principal);
    }

    public void JugarBM(View view){
        Intent Jugar = new Intent(this, P_IngresoPartida.class);
        startActivity(Jugar);
    }

    public void CerrarSesion(View view){
        SharedPreferences.Editor s = getSharedPreferences("Sesion", Context.MODE_PRIVATE).edit();
        s.clear().apply();
        Intent salir = new Intent(this, BRAINMASTER.class);
        startActivity(salir);
    }
}