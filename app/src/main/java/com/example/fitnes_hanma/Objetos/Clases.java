package com.example.fitnes_hanma.Objetos;

public class Clases {
    String id_clase, nombreClase, descripcion, nombreInstructor, fechaClase,horaClase, CliRegis;

    public Clases() {
    }

    public Clases(String id_clase, String nombreClase, String descripcion, String nombreInstructor, String fechaClase, String horaClase, String cliRegis) {
        this.id_clase = id_clase;
        this.nombreClase = nombreClase;
        this.descripcion = descripcion;
        this.nombreInstructor = nombreInstructor;
        this.fechaClase = fechaClase;
        this.horaClase = horaClase;
        CliRegis = cliRegis;

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

    public String getFechaClase() {
        return fechaClase;
    }

    public void setFechaClase(String fechaClase) {
        this.fechaClase = fechaClase;
    }

    public String getHoraClase() {
        return horaClase;
    }

    public void setHoraClase(String horaClase) {
        this.horaClase = horaClase;
    }

    public String getCliRegis() {
        return CliRegis;
    }

    public void setCliRegis(String cliRegis) {
        CliRegis = cliRegis;
    }
}
