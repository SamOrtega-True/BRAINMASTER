package com.example.brainmaster.Datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class JuegosDataHelper extends SQLiteOpenHelper {

    //String partidaTable = "CREATE TABLE partida (partida INTEGER,jugador TEXT, juego TEXT, nivel TEXT, pregunta TEXT, respuestas TEXT, puntaje INTEGER, fecha TEXT, hora TEXT)";
    //String userTable = "CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT,user TEXT, password TEXT)";
    //String sessionTable = "CREATE TABLE session (id INTEGER, user TEXT, nombre TEXT)";
    String tabla_pre = "CREATE TABLE preguntas (id INTEGER PRIMARY KEY  , id_pre INTEGER, pregunta TEXT)";
    String tabla_resp = "CREATE TABLE respuesta (id_pregunta INTEGER, respuestas TEXT, status INTEGER, CONSTRAINT C_id_pre FOREIGN KEY (id_pregunta) REFERENCES preguntas (id_pre))";

    public JuegosDataHelper(Context context, String dbName, SQLiteDatabase.CursorFactory cursor, int version){
        super(context,dbName,cursor,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(partidaTable);
        db.execSQL(tabla_pre);
        db.execSQL(tabla_resp);
        //db.execSQL(userTable);
        //db.execSQL(sessionTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
