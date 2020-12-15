package com.example.aplicacionmovilidadacademica3.Models;

import java.io.Serializable;

public class Solicitud_Docente implements Serializable {

    public Number idsol_doc;
    public String nombre;
    public String solicitud_mad;
    public String carta_m;
    public String cv;
    public  String imagen;


    public Solicitud_Docente(Number idsol_doc, String nombre, String solicitud_mad, String carta_m, String cv, String imagen) {
        this.idsol_doc = idsol_doc;
        this.nombre = nombre;
        this.solicitud_mad = solicitud_mad;
        this.carta_m = carta_m;
        this.cv = cv;
        this.imagen = imagen;
    }

    public Number getIdsol_doc() {
        return idsol_doc;
    }

    public void setIdsol_doc(Number idsol_doc) {
        this.idsol_doc = idsol_doc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
