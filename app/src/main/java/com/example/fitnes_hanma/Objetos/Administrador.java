package com.example.fitnes_hanma.Objetos;

public class Administrador {
    String Aemail, Aid, Aname, Apassword,Arole;

    public Administrador() {
    }

    public Administrador(String aemail, String aid, String aname, String apassword, String arole) {
        Aemail = aemail;
        Aid = aid;
        Aname = aname;
        Apassword = apassword;
        Arole = arole;
    }

    public String getAemail() {
        return Aemail;
    }

    public void setAemail(String aemail) {
        Aemail = aemail;
    }

    public String getAid() {
        return Aid;
    }

    public void setAid(String aid) {
        Aid = aid;
    }

    public String getAname() {
        return Aname;
    }

    public void setAname(String aname) {
        Aname = aname;
    }

    public String getApassword() {
        return Apassword;
    }

    public void setApassword(String apassword) {
        Apassword = apassword;
    }

    public String getArole() {
        return Arole;
    }

    public void setArole(String arole) {
        Arole = arole;
    }
}
