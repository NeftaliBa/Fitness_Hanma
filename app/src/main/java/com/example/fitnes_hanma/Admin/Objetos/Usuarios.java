package com.example.fitnes_hanma.Admin.Objetos;

public class Usuarios {
    String id_usuario, nombre, correo;

    public Usuarios() {
    }

    public Usuarios(String id_usuario, String nombre, String correo) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
