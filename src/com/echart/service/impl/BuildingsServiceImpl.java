package com.echart.service.impl;

import com.echart.dao.BuildingsDAO;
import com.echart.model.Buildings;
import com.echart.service.BuildingsService;

import java.util.ArrayList;

public class BuildingsServiceImpl implements BuildingsService {
    BuildingsDAO buildingsDAO=new BuildingsDAO();
    @Override
    public ArrayList<Buildings> selectByCityAndRegion(String city, String region) {
        return buildingsDAO.selectBuildingsBycityAndregion(city,region);
    }
}
