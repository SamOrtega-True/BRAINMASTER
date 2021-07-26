package com.example.brainmaster.Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.brainmaster.Entidades.Preguntas;
import com.example.brainmaster.Entidades.Respuestas;
import com.example.brainmaster.R;

import java.util.ArrayList;
import java.util.List;

public class DbProccess {

    JuegosDataHelper _helper;

    public DbProccess(Context context){
        _helper = new JuegosDataHelper(context,"juegos",null, R.integer.dbVersion);
    }

    public Boolean AgregarPregunta(Preguntas preguntas){
        try {
            SQLiteDatabase db = _helper.getReadableDatabase();
            if(db != null){
                ContentValues values = new ContentValues();
                values.put("id_pre", preguntas.getId());
                values.put("pregunta", preguntas.getPregunta());
                db.insert("preguntas", null, values);
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public Boolean AgregarRespuesta(Respuestas respuestas){
        try {
            SQLiteDatabase db = _helper.getReadableDatabase();
            if(db != null){
                ContentValues values = new ContentValues();
                values.put("respuestas", respuestas.getRespuesta());
                values.put("id_pregunta", respuestas.getId());
                values.put("status", respuestas.getCorrecta());
                db.insert("respuesta", null, values);
                return true;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public Preguntas ObtenerSiguientePartida(int it){
        try{
            SQLiteDatabase db = _helper.getReadableDatabase();
            if (db != null){
                String[] campo = {"id_pre", "pregunta"};
                Cursor cursor = db.query("preguntas",campo,null,null,null,null,null,null);
                cursor.moveToFirst();
                for(int i=1; i<=it; i++){
                    cursor.moveToNext();
                }
                Preguntas preguntas = new Preguntas(cursor.getString(0), cursor.getString(1));

                return preguntas;
            }
        }catch (Exception e){
            int x = 1;
        }
        return null;
    }

    public List<Respuestas> ObtenerSiguienteRespuesta(String idP){
        try{
            SQLiteDatabase db = _helper.getReadableDatabase();
            if (db != null){
                String[] campo = {"respuestas", "status"};
                Cursor cursor = db.rawQuery("SELECT DISTINCT id_pregunta, respuestas, status FROM respuesta WHERE id_pregunta = '"+idP+"' ORDER BY random()",null);
                //Cursor cursor = qb.query(db, campo, "id_pregunta='"+idP+"'", null, null, null, null, null);

                //Cursor cursor = db.query("respuesta",campo,"id_pregunta='"+idP+"'",null,null,null,"random()",null);
                cursor.moveToFirst();
                List<Respuestas> resp = new ArrayList<>();
                do{
                    resp.add(new Respuestas(idP, cursor.getString(1), cursor.getString(2)));
                    System.out.println(cursor.getString(1));
                }while (cursor.moveToNext());
                System.out.println(resp);
                return resp;
            }
        }catch (Exception e){
        }
        return null;
    }

    public void BorrarPreguntas(){
        try{
            SQLiteDatabase db = _helper.getWritableDatabase();
            if (db != null){
                db.delete("preguntas",null,null);
                db.delete("respuesta",null,null);
                db.close();
            }
        }
        catch (Exception x){}
    }
}
