package com.echart.service.impl;

import com.echart.dao.InvestFundfDAO;
import com.echart.model.InvestFunds;
import com.echart.service.InvestFundService;

import java.util.ArrayList;
import java.util.Set;

public class InvestFundServiceImpl implements InvestFundService {
    private InvestFundfDAO ifDAO=null;
    @Override
    public Set<InvestFunds> selectFundsByCityAndRegion(String city, String region) {
        ifDAO=new InvestFundfDAO();
        Set<InvestFunds> investFunds = ifDAO.selectFundsByRegion(city, region);
        return investFunds;
    }
}
