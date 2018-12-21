var piex = [];
var piey = [];
var address = "";
var jsonresult=[];
var url = location.search; //获取url中"?"符后的字串
/*if (url.indexOf("?") != -1) {
    address = decodeURI(url.substr(1).split("=")[1]);
}*/
$.ajax({
    type: "post",
    async: false,
    url: "http://localhost:8080/HangzhouBuildingServlet" + url,
    data: {},
    dataType: "json",
    success: function (result) {
        if (result) {
            jsonresult=result;
            for (var key in result) {
                piex.push(key);
                piey.push(result[key]);
            }
        }
    }
})
console.log(jsonresult);
console.log(piex);
console.log(piey);