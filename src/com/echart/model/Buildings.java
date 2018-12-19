package com.echart.model;

public class Buildings {
    private int id;
    private String city;
    private String region;
    private int residential;//住房
    private int office;//办公
    private  int bussiness;//商用
    private int others;//其他
    private int total;//总共

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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getResidential() {
        return residential;
    }

    public void setResidential(int residential) {
        this.residential = residential;
    }

    public int getOffice() {
        return office;
    }

    public void setOffice(int office) {
        this.office = office;
    }

    public int getBussiness() {
        return bussiness;
    }

    public void setBussiness(int bussiness) {
        this.bussiness = bussiness;
    }

    public int getOthers() {
        return others;
    }

    public void setOthers(int others) {
        this.others = others;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Buildings() {
    }

    public Buildings(String city, String region, int residential, int office, int bussiness, int others, int total) {
        this.city = city;
        this.region = region;
        this.residential = residential;
        this.office = office;
        this.bussiness = bussiness;
        this.others = others;
        this.total = total;
    }
}
