package com.example.aplicacionmovilidadacademica3.Models;

import java.io.Serializable;

public class Solicitud_Alumno implements Serializable {
    public Number idsolicitud;
    public String solicitud_mad;
    public String carta_m;
    public String cv;
    public String nombre;
    public String imagen;


    public Solicitud_Alumno(Number idsolicitud, String solicitud_mad, String carta_m, String cv, String nombre, String imagen) {
        this.idsolicitud = idsolicitud;
        this.solicitud_mad = solicitud_mad;
        this.carta_m = carta_m;
        this.cv = cv;
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public Number getIdsolicitud() {
        return idsolicitud;
    }

    public void setIdsolicitud(Number idsolicitud) {
        this.idsolicitud = idsolicitud;
    }

    public String getSolicitud_mad() {
        return solicitud_mad;
    }

    public void setSolicitud_mad(String solicitud_mad) {
        this.solicitud_mad = solicitud_mad;
    }

    public String getCarta_m() {
        return carta_m;
    }

    public void setCarta_m(String carta_m) {
        this.carta_m = carta_m;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
