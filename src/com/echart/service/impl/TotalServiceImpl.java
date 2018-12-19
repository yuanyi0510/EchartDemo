package com.echart.service.impl;

import com.echart.dao.TotalDAO;
import com.echart.model.TotalInvest;
import com.echart.service.TotalService;

import java.util.ArrayList;

public class TotalServiceImpl implements TotalService {
    TotalDAO totalDAO=new TotalDAO();
    @Override
    public ArrayList<TotalInvest> selectTotalBycity(String city) {
        return totalDAO.selectTotalBycityAndregion(city);
    }
}
