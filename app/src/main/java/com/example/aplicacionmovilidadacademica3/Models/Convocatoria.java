package com.example.aplicacionmovilidadacademica3.Models;

import java.io.Serializable;

public class Convocatoria implements Serializable {
    private Number idconvocatoria;
    private String nombre_con;
    private String fecha_ini;
    private String fecha_fin;
    public  String descripcion;
    public String imagen;


    public Convocatoria(Number idconvocatoria, String nombre_con, String fecha_ini, String fecha_fin, String descripcion, String imagen) {
        this.idconvocatoria = idconvocatoria;
        this.nombre_con = nombre_con;
        this.fecha_ini = fecha_ini;
        this.fecha_fin = fecha_fin;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Number getIdconvocatoria() {
        return idconvocatoria;
    }

    public void setIdconvocatoria(Number idconvocatoria) {
        this.idconvocatoria = idconvocatoria;
    }

    public String getNombre_con() {
        return nombre_con;
    }

    public void setNombre_con(String nombre_con) {
        this.nombre_con = nombre_con;
    }

    public String getFecha_ini() {
        return fecha_ini;
    }

    public void setFecha_ini(String fecha_ini) {
        this.fecha_ini = fecha_ini;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
