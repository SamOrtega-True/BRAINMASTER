package com.example.brainmaster;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.brainmaster.Datos.DbProccess;
import com.example.brainmaster.Entidades.Preguntas;
import com.example.brainmaster.Entidades.Respuestas;

import java.io.Serializable;
import java.util.List;

public class P_Partida extends AppCompatActivity {
    ImageView PressBack;
    TextView txtPregunta;
    Button Op1, Op2, Op3, Op4;
    ProgressBar progressBarAnimation;
    private ObjectAnimator progressAnimator;
    List<Respuestas> respuestasI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_partida);

        IniciarComponentes();
        Back();

        SharedPreferences SP = getSharedPreferences("It", Context.MODE_PRIVATE);
        int i = SP.getInt("cant", 0) + 1;
        SharedPreferences.Editor editor = SP.edit();
        editor.putInt("cant", i);
        editor.commit();

        MostrarPregunta();
        Retroalimentacion();
        BarraProgreso();
    }

    private void IniciarComponentes(){
        PressBack = (ImageView)findViewById(R.id.ImgBack);
        txtPregunta = (TextView)findViewById(R.id.txtPregunta);
        Op1 = (Button)findViewById(R.id.Opcion1);
        Op2 = (Button)findViewById(R.id.Opcion2);
        Op3 = (Button)findViewById(R.id.Opcion3);
        Op4 = (Button)findViewById(R.id.Opcion4);
    }

    private void Back(){
        PressBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(P_Partida.this);
                builder.setMessage("¿Seguro que desea abandonar?")
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            progressAnimator.cancel();
                            DbProccess dbProccess = new DbProccess(getApplicationContext());
                            dbProccess.BorrarPreguntas();
                            Intent retroalimentacion = new Intent(getApplicationContext(), P_Menu_Principal.class);
                            startActivity(retroalimentacion);
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // No coloque nada
                        }
                    });
                builder.create();
                builder.show();
            }
        });
    }

    public void Retroalimentacion(){
        Op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressAnimator.cancel();
                IntentSend(1);
            }
        });

        Op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressAnimator.cancel();
                IntentSend(2);
            }
        });

        Op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressAnimator.cancel();
                IntentSend(3);
            }
        });

        Op4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressAnimator.cancel();
                IntentSend(4);
            }
        });
    }

    private void IntentSend(int ret){
        Intent retroalimentacion = new Intent(getApplicationContext(), P_Retroalimentacion.class);
        retroalimentacion.putExtra("btn", ret);
        retroalimentacion.putExtra("opciones", (Serializable) respuestasI);
        startActivity(retroalimentacion);
    }

    private void MostrarPregunta(){
        SharedPreferences SP = getSharedPreferences("It", Context.MODE_PRIVATE);
        DbProccess dbProccess = new DbProccess(getApplicationContext());
        Preguntas preguntas = dbProccess.ObtenerSiguientePartida(SP.getInt("cant", 0));
        txtPregunta.setText(preguntas.getPregunta());

        MostrarRespuesta(preguntas.getId());
    }

    private void MostrarRespuesta(String idP){
        DbProccess dbProccess = new DbProccess(getApplicationContext());
        List<Respuestas> respuestas = dbProccess.ObtenerSiguienteRespuesta(idP);
        Op1.setText(respuestas.get(0).getRespuesta());
        Op2.setText(respuestas.get(1).getRespuesta());
        Op3.setText(respuestas.get(2).getRespuesta());
        Op4.setText(respuestas.get(3).getRespuesta());

        respuestasI = respuestas;
    }

    private void BarraProgreso(){
        init();
        progressAnimator.setDuration(10000);
        progressAnimator.start();

        progressAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                progressAnimator.cancel();
                Intent retroalimentacion = new Intent(getApplicationContext(), P_Retroalimentacion.class);
                retroalimentacion.putExtra("btn", 0);
                retroalimentacion.putExtra("opciones", (Serializable) respuestasI);
                startActivity(retroalimentacion);
            }
        });
    }

    private void init(){
        progressBarAnimation = findViewById(R.id.progressbar);
        progressAnimator = ObjectAnimator.ofInt(progressBarAnimation, "progress",100,0);

    }
}