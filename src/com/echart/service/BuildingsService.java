package com.echart.service;

import com.echart.model.Buildings;

import java.util.ArrayList;

public interface BuildingsService {
    ArrayList<Buildings> selectByCityAndRegion(String city,String region);
}
