package com.example.fitnes_hanma.Objetos;

public class Instructor {
    String Temail, Tid, Tname, Tpassword, Trole;

    public Instructor() {
    }

    public Instructor(String temail, String tid, String tname, String tpassword, String trole) {
        Temail = temail;
        Tid = tid;
        Tname = tname;
        Tpassword = tpassword;
        Trole = trole;
    }

    public String getTemail() {
        return Temail;
    }

    public void setTemail(String temail) {
        Temail = temail;
    }

    public String getTid() {
        return Tid;
    }

    public void setTid(String tid) {
        Tid = tid;
    }

    public String getTname() {
        return Tname;
    }

    public void setTname(String tname) {
        Tname = tname;
    }

    public String getTpassword() {
        return Tpassword;
    }

    public void setTpassword(String tpassword) {
        Tpassword = tpassword;
    }

    public String getTrole() {
        return Trole;
    }

    public void setTrole(String trole) {
        Trole = trole;
    }
}