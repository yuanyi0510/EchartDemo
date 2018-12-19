package com.echart.servlet;

import com.echart.model.Buildings;
import com.echart.model.InvestFunds;
import com.echart.service.BuildingsService;
import com.echart.service.InvestFundService;
import com.echart.service.impl.BuildingsServiceImpl;
import com.echart.service.impl.InvestFundServiceImpl;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Set;

public class HangzhouBuildingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String city=request.getParameter("city");
        String region=request.getParameter("region");
        BuildingsService buildingsService=new BuildingsServiceImpl();
        ArrayList<Buildings> list = buildingsService.selectByCityAndRegion(city, region);

        JSONObject jsonObject = new JSONObject();
        for (Buildings buildings : list) {
            jsonObject.put("住房",buildings.getResidential());
            jsonObject.put("办公",buildings.getOffice());
            jsonObject.put("商用",buildings.getBussiness());
            jsonObject.put("其他",buildings.getOthers());
        }
        response.setHeader("content-type","text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(jsonObject);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
