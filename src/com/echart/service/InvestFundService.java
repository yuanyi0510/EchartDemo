package com.echart.service;

import com.echart.model.InvestFunds;

import java.util.Set;

public interface InvestFundService {
    Set<InvestFunds> selectFundsByCityAndRegion(String city, String region);
}
