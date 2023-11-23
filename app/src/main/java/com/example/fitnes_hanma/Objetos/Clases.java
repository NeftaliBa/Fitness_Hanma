package com.example.fitnes_hanma.Objetos;

public class Clases {
    String id_clase, nombreClase, descripcion, correoInstructor, nombreInstructor, iDInstructor, limCli, hor1, hor2, hor3;
    Instructor instructor;

    public Clases() {
    }

    public Clases(String id_clase, String nombreClase, String descripcion, String correoInstructor, String nombreInstructor, String iDInstructor, String limCli, String hor1, String hor2, String hor3, Instructor instructor) {
        this.id_clase = id_clase;
        this.nombreClase = nombreClase;
        this.descripcion = descripcion;
        this.correoInstructor = correoInstructor;
        this.nombreInstructor = nombreInstructor;
        this.iDInstructor = iDInstructor;
        this.limCli = limCli;
        this.hor1 = hor1;
        this.hor2 = hor2;
        this.hor3 = hor3;
        this.instructor = instructor;
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

    public String getCorreoInstructor() {
        return correoInstructor;
    }

    public void setCorreoInstructor(String correoInstructor) {
        this.correoInstructor = correoInstructor;
    }

    public String getNombreInstructor() {
        return nombreInstructor;
    }

    public void setNombreInstructor(String nombreInstructor) {
        this.nombreInstructor = nombreInstructor;
    }

    public String getiDInstructor() {
        return iDInstructor;
    }

    public void setiDInstructor(String iDInstructor) {
        this.iDInstructor = iDInstructor;
    }

    public String getLimCli() {
        return limCli;
    }

    public void setLimCli(String limCli) {
        this.limCli = limCli;
    }

    public String getHor1() {
        return hor1;
    }

    public void setHor1(String hor1) {
        this.hor1 = hor1;
    }

    public String getHor2() {
        return hor2;
    }

    public void setHor2(String hor2) {
        this.hor2 = hor2;
    }

    public String getHor3() {
        return hor3;
    }

    public void setHor3(String hor3) {
        this.hor3 = hor3;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
}
