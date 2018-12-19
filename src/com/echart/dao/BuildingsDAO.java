package com.echart.dao;

import com.echart.helper.JDBCUtils;
import com.echart.model.Buildings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BuildingsDAO {
    public ArrayList<Buildings> selectBuildingsBycityAndregion(String city, String region) {
        ArrayList<Buildings> list = new ArrayList<>();
        Connection connection = JDBCUtils.getConnection();
        String sql = "select * from real_estate where city=? and region=?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, city);
            pstmt.setString(2, region);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int residential = rs.getInt("residential");
                int office = rs.getInt("office");
                int bussiness = rs.getInt("business");
                int others = rs.getInt("others");
                int total = rs.getInt("total");
                Buildings buildings = new Buildings(rs.getString("city"),
                        rs.getString("region"),
                        residential,
                        office,
                        bussiness,
                        others,
                        total);
                list.add(buildings);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }
}
