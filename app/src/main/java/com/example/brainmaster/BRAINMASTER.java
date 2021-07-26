package com.example.brainmaster;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class BRAINMASTER extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brainmaster);

        try {
            SharedPreferences SP = getSharedPreferences("Sesion", Context.MODE_PRIVATE);
            if(SP.getString("type", null).equals("0")){
                Intent iniciarSesion = new Intent(this, P_Menu_Principal.class);
                startActivity(iniciarSesion);
            }else if(SP.getString("type", null).equals("1")){
                Intent iniciarSesion = new Intent(this, P_Principal.class);
                startActivity(iniciarSesion);
            }
        }catch (Exception e){

        }
    }

    public void IniciarSesion(View view){
        Intent iniciarSesion = new Intent(this, P_Login.class);
        //Intent iniciarSesion = new Intent(this, P_Estudiante.class);
        startActivity(iniciarSesion);
    }

    public void Ayuda(View view){
        Intent iniciarSesion = new Intent(this, P_Ayuda_Principal.class);
        startActivity(iniciarSesion);
    }

    public void Referencia(View view){
        Intent iniciarSesion = new Intent(this, P_Bibliografia.class);
        startActivity(iniciarSesion);
    }
}