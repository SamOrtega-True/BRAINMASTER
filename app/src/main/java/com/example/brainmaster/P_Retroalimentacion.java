package com.example.brainmaster;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.brainmaster.Datos.DbProccess;
import com.example.brainmaster.Entidades.Preguntas;
import com.example.brainmaster.Entidades.Respuestas;

import java.util.List;

public class P_Retroalimentacion extends AppCompatActivity {
    ImageView PressBack;
    TextView txtPregunta, txtPuntos;
    TextView Op1, Op2, Op3, Op4;
    RelativeLayout lblOp1, lblOp2, lblOp3, lblOp4;
    ImageView handOp1, handOp2, handOp3, handOp4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_retroalimentacion);
        IniciarComponentes();
        Back();
        MostrarPregunta();
    }

    protected void onRestart(){
        super.onRestart();
        Intent retroalimentacion = new Intent(getApplicationContext(), P_Menu_Principal.class);
        startActivity(retroalimentacion);
    }

    private void IniciarComponentes(){
        PressBack = (ImageView)findViewById(R.id.ImgBack);
        txtPregunta = (TextView)findViewById(R.id.txtPregunta);
        Op1 = (TextView)findViewById(R.id.Opcion1);
        Op2 = (TextView)findViewById(R.id.Opcion2);
        Op3 = (TextView)findViewById(R.id.Opcion3);
        Op4 = (TextView)findViewById(R.id.Opcion4);
        lblOp1 = (RelativeLayout)findViewById(R.id.lblOp1);
        lblOp2 = (RelativeLayout)findViewById(R.id.lblOp2);
        lblOp3 = (RelativeLayout)findViewById(R.id.lblOp3);
        lblOp4 = (RelativeLayout)findViewById(R.id.lblOp4);
        handOp1 = (ImageView)findViewById(R.id.handOp1);
        handOp2 = (ImageView)findViewById(R.id.handOp2);
        handOp3 = (ImageView)findViewById(R.id.handOp3);
        handOp4 = (ImageView)findViewById(R.id.handOp4);
        txtPuntos = (TextView)findViewById(R.id.txtPuntos);
    }

    private void Back(){
        PressBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(P_Retroalimentacion.this);
                builder.setMessage("¿Seguro que desea abandonar?")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
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

    public void Continuar(View view){
        SharedPreferences SP = getSharedPreferences("It", Context.MODE_PRIVATE);
        SharedPreferences SP2 = getSharedPreferences("PartidaGeneral", Context.MODE_PRIVATE);
        if(SP.getInt("cant", 0) == 10){
            DbProccess dbProccess = new DbProccess(getApplicationContext());
            dbProccess.BorrarPreguntas();
            System.out.println(SP2.getInt("Puntaje", 0));
            Intent continuar = new Intent(this, P_Estudiante.class);
            startActivity(continuar);
        }else{
            Intent continuar = new Intent(this, P_Partida.class);
            startActivity(continuar);
        }
    }

    private void MostrarPregunta(){
        SharedPreferences SP = getSharedPreferences("It", Context.MODE_PRIVATE);
        DbProccess dbProccess = new DbProccess(getApplicationContext());
        Preguntas preguntas = dbProccess.ObtenerSiguientePartida(SP.getInt("cant", 0));
        txtPregunta.setText(preguntas.getPregunta());

        MostrarRespuesta();
    }

    private void MostrarRespuesta(){
        List<Respuestas> respuestas = (List<Respuestas>) getIntent().getExtras().getSerializable("opciones");

        Op1.setText(respuestas.get(0).getRespuesta());
        Op2.setText(respuestas.get(1).getRespuesta());
        Op3.setText(respuestas.get(2).getRespuesta());
        Op4.setText(respuestas.get(3).getRespuesta());

        ValidarRespuesta(respuestas);
    }

    private void ValidarRespuesta(List<Respuestas> respuestas){
        int rep = getIntent().getIntExtra("btn", 0);
        txtPuntos.setText("0 pts");

        if(respuestas.get(0).getCorrecta().equals("1")){
            lblOp1.setAlpha(1);
            handOp1.setVisibility(View.VISIBLE);
            handOp1.setImageResource(R.drawable.like);
        }else if(respuestas.get(1).getCorrecta().equals("1")){
            lblOp2.setAlpha(1);
            handOp2.setVisibility(View.VISIBLE);
            handOp2.setImageResource(R.drawable.like);
        }else if(respuestas.get(2).getCorrecta().equals("1")){
            lblOp3.setAlpha(1);
            handOp3.setVisibility(View.VISIBLE);
            handOp3.setImageResource(R.drawable.like);
        }else if(respuestas.get(3).getCorrecta().equals("1")){
            lblOp4.setAlpha(1);
            handOp4.setVisibility(View.VISIBLE);
            handOp4.setImageResource(R.drawable.like);
        }

        SharedPreferences SP = getSharedPreferences("PartidaGeneral", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = SP.edit();
        int sum;

        switch (rep){
            case 1:
                handOp1.setVisibility(View.VISIBLE);
                lblOp1.setAlpha(1);
                if(respuestas.get(0).getCorrecta().equals("1")){
                    handOp1.setImageResource(R.drawable.like);
                    txtPuntos.setText("+4 pts");
                    sum = SP.getInt("Puntaje", 0) + 4;
                    editor.putInt("Puntaje", sum);
                    editor.commit();
                }else{
                    handOp1.setImageResource(R.drawable.dislike);
                    txtPuntos.setText("0 pts");
                }
                break;
            case 2:
                handOp2.setVisibility(View.VISIBLE);
                lblOp2.setAlpha(1);
                if(respuestas.get(1).getCorrecta().equals("1")){
                    handOp2.setImageResource(R.drawable.like);
                    txtPuntos.setText("+4 pts");

                    sum = SP.getInt("Puntaje", 0) + 4;
                    editor.putInt("Puntaje", sum);
                    editor.commit();
                }else{
                    handOp2.setImageResource(R.drawable.dislike);
                    txtPuntos.setText("0 pts");
                }
                break;
            case 3:
                handOp3.setVisibility(View.VISIBLE);
                lblOp3.setAlpha(1);
                if(respuestas.get(2).getCorrecta().equals("1")){
                    handOp3.setImageResource(R.drawable.like);
                    txtPuntos.setText("+4 pts");

                    sum = SP.getInt("Puntaje", 0) + 4;
                    editor.putInt("Puntaje", sum);
                    editor.commit();
                }else{
                    handOp3.setImageResource(R.drawable.dislike);
                    txtPuntos.setText("0 pts");
                }
                break;
            case 4:
                handOp4.setVisibility(View.VISIBLE);
                lblOp4.setAlpha(1);
                if(respuestas.get(3).getCorrecta().equals("1")){
                    handOp4.setImageResource(R.drawable.like);
                    txtPuntos.setText("+4 pts");

                    sum = SP.getInt("Puntaje", 0) + 4;
                    editor.putInt("Puntaje", sum);
                    editor.commit();
                }else{
                    handOp4.setImageResource(R.drawable.dislike);
                    txtPuntos.setText("0 pts");
                }
                break;
        }
    }

}