package com.echart.model;

public class InvestFunds {
    private int id;
    private String item;
    private String city;
    private String region;
    private int invest;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getInvest() {
        return invest;
    }

    public void setInvest(int invest) {
        this.invest = invest;
    }

    @Override
    public String toString() {
        return "InvestFunds{" +
                "id=" + id +
                ", item='" + item + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", invest=" + invest +
                '}';
    }

    public InvestFunds(String item, String city, String region, int invest) {
        this.item = item;
        this.city = city;
        this.region = region;
        this.invest = invest;
    }

    public InvestFunds() {
    }
}
