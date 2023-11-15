package com.example.fitnes_hanma.Objetos;

public class Instructor {
    String Contraseña, Correo, Nombre, Role, id;

    public Instructor() {
    }

    public Instructor(String contraseña, String correo, String nombre, String role, String id) {
        Contraseña = contraseña;
        Correo = correo;
        Nombre = nombre;
        Role = role;
        this.id = id;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }


    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}