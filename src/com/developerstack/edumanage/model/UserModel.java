package com.developerstack.edumanage.model;

public class UserModel {

    private String fristname;
    private String lastname;
    private String email;
    private String password;

    public UserModel() {

    }

    public UserModel(String fristname, String lastname, String email, String password) {
        this.fristname = fristname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public String getFristname() {
        return fristname;
    }

    public void setFristname(String fristname) {
        this.fristname = fristname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
