package com.example.listador;

import java.io.Serializable;

public class Peliculafue implements Serializable {

    String id;
    String Titulo;
    String Genero;
    String Fecha;
    String Calificacion;

    public Peliculafue(String id, String titulo, String genero, String fecha, String calificacion) {
        this.id = id;
        Titulo = titulo;
        Genero = genero;
        Fecha = fecha;
        Calificacion = calificacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getCalificacion() {
        return Calificacion;
    }

    public void setCalificacion(String calificacion) {
        Calificacion = calificacion;
    }
}
