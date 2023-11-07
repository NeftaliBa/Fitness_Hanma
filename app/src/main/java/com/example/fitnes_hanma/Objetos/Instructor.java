package com.example.fitnes_hanma.Objetos;

public class Instructor {
    String nombreInstructor, email;

    public Instructor() {
    }

    public Instructor(String nombreInstructor, String email) {
        this.nombreInstructor = nombreInstructor;
        this.email = email;
    }

    public String getNombreInstructor() {
        return nombreInstructor;
    }

    public void setNombreInstructor(String nombreInstructor) {
        this.nombreInstructor = nombreInstructor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
