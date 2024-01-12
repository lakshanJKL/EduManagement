package com.developerstack.edumanage.model;

import java.util.Date;

public class Student {

     private String studentid;
     private String fullname;
     private Date dob;
     private String address;

    public Student(String text, String txtnameText) {
    }

    public Student(String studentid, String fullname, Date dob, String address) {
        this.studentid = studentid;
        this.fullname = fullname;
        this.dob = dob;
        this.address = address;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
