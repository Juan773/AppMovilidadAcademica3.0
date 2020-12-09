package com.example.aplicacionmovilidadacademica3.Models;

public class Universidad {

    private Number iduniversidad;
    private String direccion;
    private String nombre;
    private String pais;
    private Number totalvacantes;

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
}
