package com.echart.servlet;

import com.echart.model.TotalInvest;
import com.echart.service.TotalService;
import com.echart.service.impl.TotalServiceImpl;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "TotalServlet")
public class TotalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String city=request.getParameter("city");
        TotalService buildingsService=new TotalServiceImpl();
        ArrayList<TotalInvest> list = buildingsService.selectTotalBycity(city);

        JSONObject jsonObject = new JSONObject();
        for (TotalInvest buildings : list) {
            jsonObject.put(buildings.getYear(),buildings.getFixed());
            jsonObject.put(buildings.getFixed()+"",buildings.getEstate());
        }
        response.setHeader("content-type","text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(jsonObject);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
