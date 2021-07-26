package com.example.brainmaster.Entidades;

import java.io.Serializable;

public class Respuestas implements Serializable {
      private String id;
      private String pregunta_id;
      private String puntaje;
      private String correcta;
      private String retroalimentacion;
      private String respuesta;

      public Respuestas(String pregunta_id, String respuesta, String correcta){
          this.id = pregunta_id;
          this.respuesta = respuesta;
          this.correcta = correcta;
      }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /*public String getPregunta_id() {
        return pregunta_id;
    }

    public void setPregunta_id(String pregunta_id) {
        this.pregunta_id = pregunta_id;
    }*/

    /*public String getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(String puntaje) {
        this.puntaje = puntaje;
    }*/

    public String getCorrecta() {
        return correcta;
    }

    public void setCorrecta(String correcta) {
        this.correcta = correcta;
    }

    /*public String getRetroalimentacion() {
        return retroalimentacion;
    }

    public void setRetroalimentacion(String retroalimentacion) {
        this.retroalimentacion = retroalimentacion;
    }*/

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
