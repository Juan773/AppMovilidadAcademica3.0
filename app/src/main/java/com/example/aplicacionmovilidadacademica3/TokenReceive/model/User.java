package com.example.aplicacionmovilidadacademica3.TokenReceive.model;

public class User {

    private Long idusuario;
    private String username;
    private String password;

    public User() {
    }

    public User(Long idusuario, String username, String password) {
        this.idusuario = idusuario;
        this.username = username;
        this.password = password;
    }

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
