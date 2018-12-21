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
