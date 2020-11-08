package com.example.eventos_usr.utilidades;

/*
 * Karla Pamela Cárdenas Leyva 18550338
 * Establecer los campos y la instruccion SQL para crear la tabla en la base de datos
 * */

//Clase para constantes
public class Utilidades {

    //Constantes campos tabla eventos

    public static final String TABLA_EVENTOS="eventos";
    public static final String CAMPO_IDEv="id";
    public static final String CAMPO_NOMBREEv="NombreEv";
    public static final String CAMPO_DESCRI="descri";
    public static final String CAMPO_FECHA="fecha";
    public static final String CAMPO_HORA="hora";
    public static final String CAMPO_ORGANI="organi";
    public static final String CAMPO_IMAGEN="img";

    public static final String CREAR_TABLA_EVENTOS="CREATE TABLE IF NOT EXISTS "+TABLA_EVENTOS+" " +
            "("+CAMPO_IDEv+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_NOMBREEv+" VARCHAR, "+CAMPO_DESCRI+" VARCHAR," +
            " "+CAMPO_FECHA+" VARCHAR, "+CAMPO_HORA+" VARCHAR, "+CAMPO_ORGANI+" VARCHAR, "+CAMPO_IMAGEN+" BLOG)";

}
