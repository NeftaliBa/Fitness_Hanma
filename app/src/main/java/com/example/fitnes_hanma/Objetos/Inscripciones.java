package com.example.fitnes_hanma.Objetos;

public class Inscripciones {
    String inscripcionID, cleinteID, claseID;

    public Inscripciones() {
    }

    public Inscripciones(String inscripcionID, String cleinteID, String claseID) {
        this.inscripcionID = inscripcionID;
        this.cleinteID = cleinteID;
        this.claseID = claseID;
    }

    public String getInscripcionID() {
        return inscripcionID;
    }

    public void setInscripcionID(String inscripcionID) {
        this.inscripcionID = inscripcionID;
    }

    public String getCleinteID() {
        return cleinteID;
    }

    public void setCleinteID(String cleinteID) {
        this.cleinteID = cleinteID;
    }

    public String getClaseID() {
        return claseID;
    }

    public void setClaseID(String claseID) {
        this.claseID = claseID;
    }
}
