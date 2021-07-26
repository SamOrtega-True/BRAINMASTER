package com.example.brainmaster.Services;

import com.example.brainmaster.Entidades.Estudiante;
import com.example.brainmaster.Entidades.Juego;
import com.example.brainmaster.Entidades.Preguntas;
import com.example.brainmaster.Entidades.Respuestas;
import com.example.brainmaster.Entidades.Tabla;
import com.example.brainmaster.Requests.PartidaRequest;
import com.example.brainmaster.Responses.Facultad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api.php?ep=login")
    Call<Estudiante> getEstudianteLogin(@Query("u") String u, @Query("p") String p);

    @GET("api.php?ep=juegos")
    Call<List<Juego>> getAllJuegos();

    //locura octavio
    @GET("api.php?ep=preguntasBrain")
    Call<List<Preguntas>> getMostrarPreguntas(@Query("iP") String iP);

    @GET("api.php?ep=respuestasBrain")
    Call<List<Respuestas>> getMostrarRespuestas();

    //locura

    @GET("api.php?ep=facultades")
    Call<List<Facultad>> getAllFacultades();

    @POST("api.php?ep=estudiantesSave")
    Call<Integer> postRegistrarEstudiante(@Body Estudiante estudiante);

    @POST("api.php?ep=partidaSave")
    Call<Integer> postRegistrarPartida(@Body PartidaRequest partida);

    @GET("api.php?ep=preguntas")
    Call<List<Preguntas>> getPreguntas(@Query("j") int juego);

    @GET("api.php?ep=posiciones")
    Call<List<Tabla>> getAllTable();
}
