package com.echart.servlet;

import com.echart.model.InvestFunds;
import com.echart.service.InvestFundService;
import com.echart.service.impl.InvestFundServiceImpl;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class HangZhouIFServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String city=request.getParameter("city");
        String region=request.getParameter("region");
        InvestFundService investFundService=new InvestFundServiceImpl();
        Set<InvestFunds> list= investFundService.selectFundsByCityAndRegion(city, region);

        JSONObject jsonObject = new JSONObject();
        for (InvestFunds funds : list) {
            jsonObject.put(funds.getItem(),funds.getInvest());
        }
        response.setHeader("content-type","text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(jsonObject);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
