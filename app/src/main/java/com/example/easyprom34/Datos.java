package com.example.easyprom34;

public class Datos {

    private int id, imagen;
    private String Titulo, Detalle;


    public Datos(String titulo, String detalle, int imagen, int id) {
        Titulo = titulo;
        Detalle = detalle;
        this.imagen = imagen;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDetalle() {
        return Detalle;
    }

    public void setDetalle(String detalle) {
        Detalle = detalle;
    }
}
