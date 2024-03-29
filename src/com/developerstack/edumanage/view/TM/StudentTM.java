package com.developerstack.edumanage.view.TM;

import javafx.scene.control.Button;

public class StudentTM {
    private String id;
    private String fullname;
    private String dob;
    private String address;
    private Button btn;

    public StudentTM() {
    }

    public StudentTM(String id, String fullname, String dob, String address, Button btn) {
        this.id = id;
        this.fullname = fullname;
        this.dob = dob;
        this.address = address;
        this.btn = btn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
