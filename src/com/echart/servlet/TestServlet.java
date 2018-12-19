package com.echart.servlet;

import com.echart.model.InvestFunds;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        //创建了一个bardao的对象，barDAO主要是对数据库的连接和对数据库的操作
        InvestFunds bardao=new InvestFunds();
        //调用bardao的select_all()方法把从数据库中读取所有的数据返回的是一个ArrayList，ArrayList里面放的是一个barBean
        ArrayList<InvestFunds> array = new ArrayList<>();
        //设置返回时的编码格式
        response.setContentType("text/html; charset=utf-8");
        //调用JSONArray.fromObject方法把array中的对象转化为JSON格式的数组
        /*JSONO json=JSONArray.fromObject(array);
        System.out.println(json.toString());

        //返回给前段页面
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.flush();
        out.close();*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        // 设置响应内容类型
        response.setContentType("text/html");

        // 实际的逻辑是在这里
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println("<h1>哈哈哈</h1>");
    }
}
