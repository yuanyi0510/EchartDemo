package com.echart.helper;

import com.echart.model.Buildings;
import com.echart.model.InvestFunds;
import com.echart.model.TotalInvest;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 将excel中的数据导入到数据库
 */
public class ImportData {
    public static ArrayList<Buildings> getBuildingsDataByExcel(String file) throws Exception {
        ArrayList<Buildings> dataList = new ArrayList<>();
        File excel = new File(file);
        if (!excel.isFile() || !excel.exists()) {
            System.out.println("文件路径错误或不存在！");
        }
        Workbook wb = WorkbookFactory.create(new FileInputStream(file));
        //开始解析
        Sheet sheet = wb.getSheetAt(0);
        int firstRowIndex = sheet.getFirstRowNum();
        int lastRowIndex = sheet.getLastRowNum();
        //遍历行
        for (int i = firstRowIndex; i < lastRowIndex; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                String city = "";
                String region = "";
                int residential = 0;//住房
                int office = 0;//办公
                int bussiness = 0;//商用
                int others = 0;//其他
                int total = 0;//总共
                //遍历列
                for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null && !"".equals(cell.toString().trim())) {
                        switch (j) {
                            case 0:
                                city = cell.toString().trim();
                                break;
                            case 1:
                                region = cell.toString().trim();
                                break;
                            case 2:
                                residential = Integer.parseInt(cell.toString());
                                break;
                            case 3:
                                office = Integer.parseInt(cell.toString());
                                break;
                            case 4:
                                bussiness = Integer.parseInt(cell.toString());
                                break;
                            case 5:
                                others = Integer.parseInt(cell.toString());
                                break;
                            case 6:
                                total = Integer.parseInt(cell.toString());
                                break;
                            default:
                                break;
                        }
                    }
                }
                dataList.add(new Buildings(city, region, residential, office, bussiness, others, total));
            }

        }
        return dataList;
    }

    public static ArrayList<InvestFunds> getInvestDataByExcel(String file) throws Exception {
        ArrayList<InvestFunds> dataList = new ArrayList<>();
        File excel = new File(file);
        if (!excel.isFile() || !excel.exists()) {
            System.out.println("文件路径错误或不存在！");
        }
        Workbook wb = WorkbookFactory.create(new FileInputStream(file));
        //开始解析
        Sheet sheet = wb.getSheetAt(0);
        int firstRowIndex = sheet.getFirstRowNum();
        int lastRowIndex = sheet.getLastRowNum();
        //遍历行
        for (int i = firstRowIndex; i < lastRowIndex; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                String item = "";
                String city = "";
                String region = "";
                int invest = 0;
                //遍历列
                for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null && !"".equals(cell.toString().trim())) {
                        switch (j) {
                            case 0:
                                item = cell.toString().trim();
                                break;
                            case 1:
                                city = cell.toString().trim();
                                break;
                            case 2:
                                region = cell.toString().trim();
                                break;
                            case 3:
                                invest = Integer.parseInt(cell.toString());
                                break;
                            default:
                                break;
                        }
                    }
                }
                dataList.add(new InvestFunds(item, city, region, invest));
            }

        }
        return dataList;
    }
    public static ArrayList<TotalInvest> getTotalDataByExcel(String file) throws Exception {
        ArrayList<TotalInvest> dataList = new ArrayList<>();
        File excel = new File(file);
        if (!excel.isFile() || !excel.exists()) {
            System.out.println("文件路径错误或不存在！");
        }
        Workbook wb = WorkbookFactory.create(new FileInputStream(file));
        //开始解析
        Sheet sheet = wb.getSheetAt(0);
        int firstRowIndex = sheet.getFirstRowNum();
        int lastRowIndex = sheet.getLastRowNum();
        //遍历行
        for (int i = firstRowIndex; i < lastRowIndex; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                String year = "";
                String city = "";
                double fixed = 0;
                double estate=0;
                //遍历列
                for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null && !"".equals(cell.toString().trim())) {
                        switch (j) {
                            case 0:
                                city = cell.toString().trim();
                                break;
                            case 1:
                                year = cell.toString().trim();
                                break;
                            case 2:
                                fixed = Double.parseDouble(cell.toString());
                                break;
                            case 3:
                                estate = Double.parseDouble(cell.toString());
                                break;
                            default:
                                break;
                        }
                    }
                }
                dataList.add(new TotalInvest(city,year,fixed,estate));
            }

        }
        return dataList;
    }
    public static void insertTotalData(ArrayList<TotalInvest> list) {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement pstmt = null;
        try {

            for (TotalInvest totalInvest : list) {
                String sql = "insert into summary(city,years,fixed,estate) values(?,?,?,?)";
                pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, totalInvest.getCity());
                pstmt.setString(2, totalInvest.getYear());
                pstmt.setDouble(3, totalInvest.getFixed());
                pstmt.setDouble(4, totalInvest.getEstate());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.release(null,null,connection);
        }
    }
    public static void insertBuildingData(ArrayList<Buildings> list) {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement pstmt = null;
        try {

            for (Buildings buildings : list) {
                String sql = "insert into real_estate(city,region,residential,office,business,others,total ) values(?,?,?,?,?,?,?)";
                int total=buildings.getResidential()+buildings.getOffice()+buildings.getBussiness()+buildings.getOthers();
                pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, buildings.getCity());
                pstmt.setString(2, buildings.getRegion());
                pstmt.setInt(3, buildings.getResidential());
                pstmt.setInt(4, buildings.getOffice());
                pstmt.setInt(5, buildings.getBussiness());
                pstmt.setInt(6, buildings.getOthers());
                pstmt.setInt(7, total);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.release(null,null,connection);
        }
    }

    public static void importData(ArrayList<InvestFunds> list) {
        MySqlConnection connection = new MySqlConnection();
        connection.open();
        for (InvestFunds funds : list) {
            String sql = "insert into InvestFunds(city,item,region,invest ) values('"
                    + funds.getCity() + "','"
                    + funds.getItem() + "','"
                    + funds.getRegion() + "',"
                    + funds.getInvest() + ") ";
            int i = connection.executeUpdate(sql);
            if (i == -1) {
                System.out.println(funds.toString());
            }
        }
        connection.close();
    }

    public static void main(String[] args) throws Exception {
        /*ArrayList list = ImportData.getInvestDataByExcel("D:\\浙大工作\\数据可视化技术\\A作业\\固定资产.xlsx");
        System.out.println(list.size());
        System.out.println(list);
        ImportData.importData(list);
        ImportData.insertBuildingData( ImportData.getBuildingsDataByExcel("D:\\浙大工作\\数据可视化技术\\A作业\\房地产.xlsx"));
*/
    ImportData.insertTotalData(ImportData.getTotalDataByExcel("D:\\\\浙大工作\\\\数据可视化技术\\\\A作业\\\\投资总额.xlsx"));
    }
}
