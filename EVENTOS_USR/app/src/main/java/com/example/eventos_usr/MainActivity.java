package com.example.eventos_usr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.example.eventos_usr.utilidades.Utilidades;

import java.util.ArrayList;

/*
* Karla Pamela Cárdenas Leyva 18550338
* Clase Principal para el proyecto EventU
* */

public class MainActivity extends AppCompatActivity {

    Intent intentAgEv;
    RecyclerView rcViewEvProx;
    ArrayList<Eventos> listEventos;

    ConexionSQLHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Contexto, nombre de base de datos, parametro factory y version de la bd, para crear la base de datos
       conn = new ConexionSQLHelper(getApplicationContext(),"bd_eventos.sqlite",null,1);

       //Lista con los eventos
       listEventos = new ArrayList<>();

       rcViewEvProx = findViewById(R.id.rcViewEvProx);
       rcViewEvProx.setLayoutManager(new LinearLayoutManager(this));

       consultarListaEventos();

       Adaptador adapter = new Adaptador(listEventos);
       rcViewEvProx.setAdapter(adapter);
    }

    public void consultarListaEventos(){
        Eventos evento = null;
        //Se hace la consulta para obtener los datos de la tabla
        Cursor cursor = conn.obtenerDatos("SELECT * FROM " + Utilidades.TABLA_EVENTOS);

        //Se hace el recorrido de los datos
        while (cursor.moveToNext()){
            evento = new Eventos();

            evento.setId(cursor.getInt(0));
            evento.setNombreEvent(cursor.getString(1));
            evento.setDesc(cursor.getString(2));
            evento.setFecha(cursor.getString(3));
            evento.setHora(cursor.getString(4));
            evento.setOrgani(cursor.getString(5));
            evento.setImg(cursor.getBlob(6));

            //Llenado de la lista
            listEventos.add(evento);
        }
    }

    public void agregarEvento(View view) {
        //Se abre la vista para agregar eventos, al seleccional el boton "agregar evento"
        intentAgEv = new Intent(getApplicationContext(),AgregarEvento.class);
        startActivityForResult(intentAgEv,0);
    }






}