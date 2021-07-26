package com.example.brainmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class P_Recuperar_Contrasena extends AppCompatActivity {
    ImageView PressBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_recuperar_contrasena);
        IniciarComponentes();
        Back();
    }

    private void IniciarComponentes(){
        PressBack = (ImageView)findViewById(R.id.ImgBack);
    }

    private void Back(){
        PressBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}