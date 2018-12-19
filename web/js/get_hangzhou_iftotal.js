var yearsdata=[];
var address = "";
var list=[];
var rate=[];
var fixedlist=[];
var estatelist=[];
var max=0;
var url = location.search; //获取url中"?"符后的字串
$.ajax({
    type: "post",
    async: false,
    url: "http://localhost:8080/TotalServlet" + url,
    data: {},
    dataType: "json",
    success: function (result) {
        if (result) {
            jsonresult=result;
            for (var key in result) {
                if (key=="2013"||key=="2014"||key=="2015"||key=="2016"||key=="2012" ) {
                    if (key!="2012"){
                        yearsdata.push(result[key]);
                    }
                    if (max<result[key]){
                        max=result[key];
                    }
                    list.push({
                        value:result[key],
                        name:key
                    })
                }else{
                    fixedlist.push(key);
                    estatelist.push(result[key]);
                }

            }
            for (var i=list.length-1;i>0;i--){
                //计算比上一年增高的比率
                var  r=(list[i].value/list[i-1].value).toFixed(1) + ' %';
                rate.push({
                    value: r,
                    name: list[i].name
                })
            }
        }
    }
})
console.log("max:"+max)