package com.example.eventos_usr;

/*
 * Karla Pamela Cárdenas Leyva 18550338
 * Variables y métodos get y set
 * */

public class Eventos {

    //Declaracion de las variables
    private int id;
    private String NombreEvent;
    private String desc;
    private String fecha;
    private String hora;
    private String organi;
    private byte[] img;

    //Constructor
    public Eventos(int id, String nombreEvent, String desc, String fecha, String hora, String organi, byte[] img) {
        this.id = id;
        this.NombreEvent = nombreEvent;
        this.desc = desc;
        this.fecha = fecha;
        this.hora = hora;
        this.organi = organi;
        this.img = img;
    }

    //Constructor vacio
    public Eventos() {
    }

    //Metodos get y set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreEvent() {
        return NombreEvent;
    }

    public void setNombreEvent(String nombreEvent) {
        NombreEvent = nombreEvent;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getOrgani() {
        return organi;
    }

    public void setOrgani(String organi) {
        this.organi = organi;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

}
