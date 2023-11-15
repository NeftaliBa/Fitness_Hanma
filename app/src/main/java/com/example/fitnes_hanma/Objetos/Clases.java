package com.example.fitnes_hanma.Objetos;

public class Clases {
    String id_clase, nombreClase, descripcion, nombreInstructor ,horaClase, limCli;

    public Clases() {
    }

    public Clases(String id_clase, String nombreClase, String descripcion, String nombreInstructor, String horaClase, String limCli) {
        this.id_clase = id_clase;
        this.nombreClase = nombreClase;
        this.descripcion = descripcion;
        this.nombreInstructor = nombreInstructor;
        this.horaClase = horaClase;
        this.limCli = limCli;
    }

    public String getId_clase() {
        return id_clase;
    }

    public void setId_clase(String id_clase) {
        this.id_clase = id_clase;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreInstructor() {
        return nombreInstructor;
    }

    public void setNombreInstructor(String nombreInstructor) {
        this.nombreInstructor = nombreInstructor;
    }

    public String getHoraClase() {
        return horaClase;
    }

    public void setHoraClase(String horaClase) {
        this.horaClase = horaClase;
    }

    public String getLimCli() {
        return limCli;
    }

    public void setLimCli(String limCli) {
        this.limCli = limCli;
    }
}
