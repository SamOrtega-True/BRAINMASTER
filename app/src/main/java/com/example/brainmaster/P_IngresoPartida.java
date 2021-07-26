package com.example.brainmaster;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.brainmaster.Datos.DbProccess;
import com.example.brainmaster.Entidades.Preguntas;
import com.example.brainmaster.Entidades.Respuestas;
import com.example.brainmaster.Services.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class P_IngresoPartida extends AppCompatActivity {
    LinearLayout Error;
    EditText Code;
    DbProccess db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_ingreso_partida);
        db = new DbProccess(getApplicationContext());
        IniciarComponentes();
        try {
            SharedPreferences SP = getSharedPreferences("It", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = SP.edit();
            editor.putInt("cant", 0);
            editor.commit();
        }catch (Exception e){}
    }

    private void IniciarComponentes(){
        Error = (LinearLayout)findViewById(R.id.JugarError);
        Code = (EditText)findViewById(R.id.JugarCode);
    }

    public void ValidarCodigo(View view){
        CargarPreguntas();
    }

    //incompleto preguntas
    List<Preguntas> _preguntas;
    String SCode;
    private void CargarPreguntas(){
        SCode = this.Code.getText().toString().trim();
        Call<List<Preguntas>> response = ApiService.getApiService().getMostrarPreguntas(SCode);

        response.enqueue(new Callback<List<Preguntas>>() {
            @Override
            public void onResponse(Call<List<Preguntas>> call, Response<List<Preguntas>> response) {
                if (response.isSuccessful()){
                    _preguntas = response.body();

                    SharedPreferences SP = getSharedPreferences("PartidaGeneral", Context.MODE_PRIVATE);
                    SharedPreferences SP2 = getSharedPreferences("Sesion", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = SP.edit();
                    editor.putString("correo", SP2.getString("correo", null));
                    editor.putString("juego", "BrainMaster");
                    editor.putString("idPartida", _preguntas.get(0).getId());
                    editor.putInt("Puntaje", 0);
                    editor.commit();

                    for(Preguntas preg : _preguntas){
                        Preguntas preguntas = new Preguntas(preg.getId(), preg.getPregunta());
                        db.AgregarPregunta(preguntas);
                    }
                    Intent P = new Intent(getApplicationContext(), P_Partida.class);
                    startActivity(P);
                    Error.setAlpha(0);
                }else{
                    Error.setAlpha(1);
                }
            }
            @Override
            public void onFailure(Call<List<Preguntas>> call, Throwable t) {

            }
        });
        CargarRespuestas();
    }

    //incompleto respuestas
    List<Respuestas> _respuestas;
    private void CargarRespuestas(){
        Call<List<Respuestas>> response = ApiService.getApiService().getMostrarRespuestas();

        response.enqueue(new Callback<List<Respuestas>>() {
            @Override
            public void onResponse(Call<List<Respuestas>> call, Response<List<Respuestas>> response) {
                if (response.isSuccessful()){
                    _respuestas = response.body();
                    for(Respuestas resp : _respuestas){
                        Respuestas respuesta = new  Respuestas(resp.getId(), resp.getRespuesta(), resp.getCorrecta());
                        db.AgregarRespuesta(respuesta);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Respuestas>> call, Throwable t) {

            }
        });
    }
}