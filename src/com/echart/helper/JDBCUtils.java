package com.echart.helper;

import com.echart.model.InvestFunds;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private static DataSource dataSource;

    static {
        try {
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
            Properties properties = new Properties();
            properties.load(is);
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
public static void release(ResultSet rs,PreparedStatement pstmt,Connection conn){
    try {
        rs.close();
        pstmt.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    public static void main(String[] args) {
        Connection connection=JDBCUtils.getConnection();
        String sql="select * from InvestFunds where city= ? and region=?";
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            pstmt= connection.prepareStatement(sql);
            pstmt.setString(1,"杭州");
            pstmt.setString(2,"");
            rs=pstmt.executeQuery();
            while (rs.next()){
                InvestFunds inv=new InvestFunds();
                //investFunds.setId(rs.getInt("id"));
                inv.setId(rs.getInt("id"));
                inv.setCity(rs.getString("city"));
                inv.setItem(rs.getString("item"));
                inv.setRegion(rs.getString("region"));
                inv.setInvest(rs.getInt("invest"));
                System.out.println(inv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.release(rs,pstmt,connection);
        }

    }

}
