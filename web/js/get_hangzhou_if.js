var xdata = [];
var ydata = [];
var address = "";
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
console.log("xdata"+xdata)
console.log("ydata"+ydata)
