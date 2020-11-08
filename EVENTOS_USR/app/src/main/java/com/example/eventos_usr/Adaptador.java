package com.example.eventos_usr;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/*
 * Karla Pamela Cárdenas Leyva 18550338
 * Vincular los items de la interfaz con el recyclerView
 * */

//Hereda de recycler view adapter
//ViewHolder se declara como clase interna y extiende de recyclerview
//Nos da acceso a los items de la interfaz y mostrarlos en el recyclerview
public class Adaptador extends RecyclerView.Adapter<Adaptador.EventosViewHolder> {

    ArrayList<Eventos> eventosList; //Lista para los eventos

    //Constructor
    public Adaptador(ArrayList<Eventos> eventosList) {
        this.eventosList = eventosList;
    }

    public class EventosViewHolder extends RecyclerView.ViewHolder{
        //Declaramos los textView segun la interfaz list
        TextView txtVwTitulo, txtVwFecha,txtVwHora,txtVwDesc,txtVwOrg;
        ImageView imgVwEv;
        CheckBox asistCheck;

        //Da acceso a los items de la interfaz
        public EventosViewHolder(@NonNull View itemView) {
            super(itemView);
            txtVwTitulo = itemView.findViewById(R.id.txtVwTitulo);
            txtVwFecha = itemView.findViewById(R.id.txtVwFecha);
            txtVwHora = itemView.findViewById(R.id.txtVwHora);
            imgVwEv = itemView.findViewById(R.id.imgVwEv);
        }
    }


    //inicializa la interfaz de un item de la lista usando apartir de layout
    //usando para ello layout inflater, y lo guarda en el viewholder
    @Override
    public EventosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,null,false);
        return new EventosViewHolder(view);
    }

    //Actualizamos los campos de la interfaz de viewholder con los elementos
    // que corresponden a la posicion. Se hace enfásis por la parte de la documentacion de
    //recycler view, para estar reciclando las vistas sin usar o buscar por id
    @Override
    public void onBindViewHolder(@NonNull EventosViewHolder holder, int position) {
        Eventos eventos = eventosList.get(position);

        //Se colocan los eventos guardados de la base de datos en la lista
        holder.txtVwTitulo.setText(eventos.getNombreEvent());
        holder.txtVwFecha.setText(eventos.getFecha());
        holder.txtVwHora.setText(eventos.getHora());

        //Se obtiene la imagen en mapa de bits
        byte[] eventImg = eventos.getImg();
        Bitmap bitmap = BitmapFactory.decodeByteArray(eventImg,0,eventImg.length);
        holder.imgVwEv.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {
        return eventosList.size();
    }

}
