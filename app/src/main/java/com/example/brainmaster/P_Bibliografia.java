package com.example.brainmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class P_Bibliografia extends AppCompatActivity {
    CardView Pag1, Pag2, Pag3, Pag4, Pag5;
    ImageView PressBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_bibliografia);
        IniciarControles();
        AbrirPagina();
        Back();
    }

    private void IniciarControles(){
        Pag1 = (CardView)findViewById(R.id.Web1);
        Pag2 = (CardView)findViewById(R.id.Web2);
        Pag3 = (CardView)findViewById(R.id.Web3);
        Pag4 = (CardView)findViewById(R.id.Web4);
        Pag5 = (CardView)findViewById(R.id.Web5);
        PressBack = (ImageView)findViewById(R.id.ImgBack);
    }

    private void AbrirPagina(){
        Pag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://es.wikipedia.org/wiki/Ingenier%C3%ADa_de_software_basada_en_componentes");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        Pag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.ecured.cu/Desarrollo_de_software_basado_en_componentes");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        Pag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://instintobinario.com/890/#:~:text=El%20desarrollo%20basado%20en%20componentes%20consiste%20en%20construir%20aplicaciones%20mediante,que%20van%20a%20ser%20utilizados");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        Pag4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://utp.ac.pa/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        Pag5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://fisc.utp.ac.pa/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
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