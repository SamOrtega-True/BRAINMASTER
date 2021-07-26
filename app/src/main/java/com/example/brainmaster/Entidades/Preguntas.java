package com.example.brainmaster.Entidades;

public class Preguntas {
    private String id;
    private String pregunta;

    public Preguntas(String id, String pregunta){
        this.id = id;
        this.pregunta = pregunta;
    }
    //private List<Respuestas> respuestas;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /*public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }*/

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    /*public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo_pregunta_id() {
        return tipo_pregunta_id;
    }

    public void setTipo_pregunta_id(String tipo_pregunta_id) {
        this.tipo_pregunta_id = tipo_pregunta_id;
    }

    public List<Respuestas> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuestas> respuestas) {
        this.respuestas = respuestas;
    }*/
}
