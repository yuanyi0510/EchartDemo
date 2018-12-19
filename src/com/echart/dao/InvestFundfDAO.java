package com.echart.dao;

import com.echart.helper.JDBCUtils;
import com.echart.model.InvestFunds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class InvestFundfDAO {
    public Set<InvestFunds> selectFundsByRegion(String city, String region){
        Set<InvestFunds> list = new HashSet<>();
        Connection connection = JDBCUtils.getConnection();
        String sql = "select * from InvestFunds where city= ? and region=? order by invest";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, city);
            pstmt.setString(2, region);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                InvestFunds inv = new InvestFunds();
                inv.setId(rs.getInt("id"));
                inv.setCity(rs.getString("city"));
                inv.setItem(rs.getString("item"));
                inv.setRegion(rs.getString("region"));
                inv.setInvest(rs.getInt("invest"));
                list.add(inv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, pstmt, connection);
        }
        return list;
    }

    public static void main(String[] args) {
        InvestFundfDAO investFundfDAO=new InvestFundfDAO();
        Set<InvestFunds> list = investFundfDAO.selectFundsByRegion("杭州", "");
        for (InvestFunds funds : list) {
            System.out.println(funds.toString());
        }
    }
}
