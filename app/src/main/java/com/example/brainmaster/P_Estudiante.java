package com.example.brainmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.brainmaster.Adapter.RankingPartida;
import com.example.brainmaster.Entidades.Preguntas;
import com.example.brainmaster.Entidades.Tabla;
import com.example.brainmaster.Services.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class P_Estudiante extends AppCompatActivity {
    ListView PuntuacionEstudiante;
    //borrar
    List<Preguntas> _preguntas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_estudiante);
        InicializarControles();
        LoadListView();

    }

    private void InicializarControles(){
        PuntuacionEstudiante = (ListView)findViewById(R.id.lslRanking);
    }

    /*private void LoadListViewTemplate() {
        List<Puntuacion_Estudiante> opciones = this.GetElementsToListViewTemplate();
        RankingPartida adapter = new RankingPartida(this, opciones);
        PuntuacionEstudiante.setAdapter(adapter);
    }*/

    private void LoadListView() {
        Call<List<Tabla>> response = ApiService.getApiService().getAllTable();
        response.enqueue(new Callback<List<Tabla>>() {
            @Override
            public void onResponse(Call<List<Tabla>> call, Response<List<Tabla>> response) {
                if (response.isSuccessful()){
                    List<Tabla> table = response.body();
                    RankingPartida adapter = new RankingPartida(getApplicationContext(),table);
                    PuntuacionEstudiante.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Tabla>> call, Throwable t) {

            }
        });
    }

    /*private List<Puntuacion_Estudiante> GetElementsToListViewTemplate() {//METDODS DE SAMUEL
        List<Puntuacion_Estudiante> opciones = new ArrayList<Puntuacion_Estudiante>();
        opciones.add(new Puntuacion_Estudiante("Samuel Ortega"));
        opciones.add(new Puntuacion_Estudiante("Samuel Ortega"));
        opciones.add(new Puntuacion_Estudiante("Samuel Ortega"));
        opciones.add(new Puntuacion_Estudiante("Samuel Ortega"));
        opciones.add(new Puntuacion_Estudiante("Samuel Ortega"));
        opciones.add(new Puntuacion_Estudiante("Samuel Ortega"));
        return  opciones;
    }*/

    public void Salir(View view){
        Intent salir = new Intent(this, P_Menu_Principal.class);
        startActivity(salir);
    }
}