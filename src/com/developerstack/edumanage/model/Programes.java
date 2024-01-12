package com.developerstack.edumanage.model;

public class Programes {
    private String code;
    private String name;
    private String [] thechnology;
    private String teacherid;
    private double cost;

    public Programes() {
    }

    public Programes(String code, String name, String[] thechnology, String teacherid, double cost) {
        this.code = code;
        this.name = name;
        this.thechnology = thechnology;
        this.teacherid = teacherid;
        this.cost = cost;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getThechnology() {
        return thechnology;
    }

    public void setThechnology(String[] thechnology) {
        this.thechnology = thechnology;
    }

    public String getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
