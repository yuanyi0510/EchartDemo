var mychart=echarts.init(document.getElementById("pie"));
var data=[];
for (var i=0;i<piex.length;i++){
    data.push(
        {
            value:piey[i],
            name: piex[i]
        }
    )
}
option = {
    title : {
        text: '房地产数据',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: piex
    },
    series : [
        {
            name: '房地产数据',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:data,
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
mychart.setOption(option);