package com.echart.dao;

import com.echart.helper.JDBCUtils;
import com.echart.model.TotalInvest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TotalDAO {
    public ArrayList<TotalInvest> selectTotalBycityAndregion(String city) {
        ArrayList<TotalInvest> list = new ArrayList<>();
        Connection connection = JDBCUtils.getConnection();
        String sql = "select * from summary where city=? ";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, city);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String years = rs.getString("years");
                double fixed = rs.getDouble("fixed");
                double estate = rs.getDouble("estate");
                TotalInvest totalInvest = new TotalInvest(city, years, fixed, estate);
                list.add(totalInvest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }
}
