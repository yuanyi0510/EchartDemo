package com.echart.service;

import com.echart.model.TotalInvest;

import java.util.ArrayList;

public interface TotalService {
    ArrayList<TotalInvest> selectTotalBycity(String city);
}
