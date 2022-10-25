package com.example.biblioteca.model;

import java.util.Objects;

public class Usuario {
    private String userName;
    private String pass;

    public Usuario(String userName, String pass) {
        this.userName = userName;
        this.pass = pass;
    }

    public Usuario() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return  userName + " " + pass ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(userName, usuario.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, pass);
    }

    /**
     * Construye un Usuario apratir de un array de datos
     * @param s Array con los datos del usuario: pos 0 -> Nombre, pos 1 -> pass
     * @return Usuario correspondiente al array de bytes
     */
    public static Usuario usuario(String[] s) {
        return new Usuario(s[0], s[1]);
    }
}
