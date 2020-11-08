package com.example.eventos_usr;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.example.eventos_usr.utilidades.Utilidades;

/*
 * Karla Pamela Cárdenas Leyva 18550338
 * Conexión y métodos para manejar la base de datos
 * */

public class ConexionSQLHelper extends SQLiteOpenHelper {


    //Al llamar al constructor se crea la base de datos
    //Contexto, Nombre de la BD, un factory y la version de la BD
    public ConexionSQLHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void datosConsulta (String sql){
        //Se abre la base de datos y se manda la instruccion sql
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    //insertar datos a la base de datos
    public void insertarDatos(String NombreEv, String desc, String fecha, String hora, String organi, byte[] img){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO "+ Utilidades.TABLA_EVENTOS +" VALUES (NULL, ?, ?, ?, ?, ?, ?)";

        SQLiteStatement statement = db.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, NombreEv);
        statement.bindString(2, desc);
        statement.bindString(3, String.valueOf(fecha));
        statement.bindString(4, String.valueOf(hora));
        statement.bindString(5, organi);
        statement.bindBlob(6, img);

        statement.executeInsert();
    }

    //Obtener los datos
    public Cursor obtenerDatos(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql,null);
    }

    //Genera las tablas o scripts correspondientes de las entidades
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_EVENTOS);
    }


    //Verifica si existe una version antigua de la BD, y puede refrescar una BD nueva
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS eventos");
        onCreate(db);
    }
}
