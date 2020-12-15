package com.example.aplicacionmovilidadacademica3.Models;

import java.io.Serializable;

public class Universidad implements Serializable {

    private Number iduniversidad;
    private String direccion;
    private String nombre;
    private String pais;
    private Number totalvacantes;
    private String imagen;

    public Universidad(Number iduniversidad, String direccion, String nombre, String pais, Number totalvacantes, String imagen) {
        this.iduniversidad = iduniversidad;
        this.direccion = direccion;
        this.nombre = nombre;
        this.pais = pais;
        this.totalvacantes = totalvacantes;
        this.imagen = imagen;
    }

    public Number getIduniversidad() {
        return iduniversidad;
    }

    public void setIduniversidad(Number iduniversidad) {
        this.iduniversidad = iduniversidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Number getTotalvacantes() {
        return totalvacantes;
    }

    public void setTotalvacantes(Number totalvacantes) {
        this.totalvacantes = totalvacantes;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
