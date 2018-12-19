package com.echart.model;

public class TotalInvest {
    private int id;
    private String city;
    private String year;
    private double estate;
    private double fixed;

    public TotalInvest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public double getEstate() {
        return estate;
    }

    public void setEstate(int estate) {
        this.estate = estate;
    }

    public double getFixed() {
        return fixed;
    }

    public void setFixed(int fixed) {
        this.fixed = fixed;
    }

    public TotalInvest(String city, String year, double fixed, double estate) {
        this.city = city;
        this.year = year;
        this.estate = estate;
        this.fixed = fixed;
    }
}
