# EchartDemo

### 项目介绍

基于java web和echarts的数据可视化项目

主要分析浙江省各市区的gdp和固定资产投资、以及房产数据，数据源[浙江省经济社会发展统计](http://data.cnki.net/Area/Home/Index/D11)，数据经过整理后插入数据库中。

主页面：

![主页面](https://raw.githubusercontent.com/yuanyi0510/EchartDemo/master/images/main.gif)

固定资产页面：

![固定资产投资分析](https://raw.githubusercontent.com/yuanyi0510/EchartDemo/master/images/fixed.gif)

### 将excel中的数据导入mysql数据库

所需jar包[百度云](https://pan.baidu.com/s/1-GWJi0I9HcktaZUbzvbf2g)

1. 数据库的连接配置在dbcpconfig.properties文件中,注意更换自己的用户名、密码等信息
   ​

   ```properties
   #连接设置
   driverClassName=com.mysql.jdbc.Driver
   url=jdbc:mysql://113.141.72.49:3306/yy-visualization
   username=root
   password=root
   #<!-- 初始化连接 -->
   initialSize=10
   #最大连接数量
   maxActive=50
   #<!-- 最大空闲连接 -->
   maxIdle=20
   #<!-- 最小空闲连接 -->
   minIdle=5
   #<!-- 超时等待时间以毫秒为单位 6000毫秒/1000等于60秒 -->
   maxWait=60000
   #JDBC驱动建立连接时附带的连接属性属性的格式必须为这样：[属性名=property;] 
   #注意："user" 与 "password" 两个属性会被明确地传递，因此这里不需要包含他们。
   connectionProperties=useUnicode=true;characterEncoding=utf-8
   ```

2. JDBC连接池——DBCP

   ```java
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
   }
   ```

3. 读取excel文件——ImportData类

   ```java
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
   ```

4. 插入到数据库

   ```java
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
             //注意要释放资源
               JDBCUtils.release(null,null,connection);
           }
       }
   ```

### echart

以柱状图为例

1. 定义一个div容器

   ```html
   <div id='if_bar' style='width:500px;height:400px; margin-right: 30px'>
                   <script src="js/hangzhou_if_bar.js"></script>
          </div>
   ```

2. js页面，在echart官网找到合适的图表，可以直接使用官网代码，更换掉自己的数据就好

   ```javascript
   var mychart=echarts.init(document.getElementById("if_bar"));
   option = {
       title: {
           text: '固定资产投资'
       },
       color: ['#3398DB'],
       tooltip : {
           trigger: 'axis',
           axisPointer : {            // 坐标轴指示器，坐标轴触发有效
               type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
           }
       },
       grid: {
           left: '3%',
           right: '4%',
           bottom: '3%',
           containLabel: true
       },
       xAxis : [
           {
               type : 'category',
               data : xdata,
               axisTick: {
                   alignWithLabel: true
               },
               axisLabel: {
                   interval: 0,//横轴信息全部显示
                   rotate: -35,//-15度角倾斜显示
                   formatter:function (val) {
                       if (val.length>5){
                           return val.substring(0,6)+'……';
                       } else {
                           return val;
                       }
                   }
               },
           }
       ],
       yAxis : [
           {
               type : 'value'
           }
       ],
       series : [
           {
               name:'固定资产投资',
               type:'bar',
               barWidth: '60%',
               data:ydata
           }
       ]
   };
   mychart.setOption(option);
   ```


3. ajax异步请求数据

   ```javascript
   var xdata = [];//x轴
   var ydata = [];//y轴
   var url = location.search; //获取url中"?"符后的字串
   $.ajax({
       type: "post",
       async: false,
       url: "http://localhost:8080/HangZhouIFServlet" + url,
       data: {},
       dataType: "json",
       success: function (result) {
           if (result) {
               for (var key in result) {
                   xdata.push(key);
                   ydata.push(result[key]);
               }
           }
       }
   })

   ```

### 首页的热力图

[参考博客](https://blog.csdn.net/qq_33912088/article/details/70241080)

echart官网提供的地图只有市级，以下是私藏的省级地图

[echart各省json文件](https://pan.baidu.com/s/1S9QodeOvTYPOJBlcjrjjWA)