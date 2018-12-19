package com.echart.helper;

import java.sql.*;

public class MySqlConnection {
    private Connection connection = null;
    private Statement statement = null;
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://113.141.72.49:3306/yy-visualization?useUnicode=true&characterEncoding=utf-8";
    private String username = "root";
    private String pwd = "root";

    public void open() {
        try {
            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(url, username, pwd);
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ResultSet executeQuery(String sql) {
        try {
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int executeUpdate(String sql) {
        try {
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public void close(){
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void main(String args[])
    {
        try {
            MySqlConnection jdbc= new MySqlConnection();
            jdbc.open();
            ResultSet rs =  jdbc.executeQuery("select * from Ningbo_GDP");

            while(rs.next()){
                String email = rs.getString("County") ;
                System.out.println(email);

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}
