function pageClick(k) {
    $(k).parent().find("div").removeClass("active");
    $(k).addClass("active");
    $("#flTitle").text($(k).text());
    var text=$(k).text().trim();
    var hangzhoulist=["萧山","余杭","桐庐","富阳","淳安","建德","临安"];
    var ningbolist=["海曙","江北","镇海","北仑","鄞州","奉化","余姚","慈溪","宁海","象山"];
    var jinhualist=["兰溪","义乌","东阳","永康","武义","浦江","磐安"];
    var shaoxinglsit=["越城","柯桥","上虞","诸暨","嵊州","新昌"];
    var jiaxinglist=["南湖","秀洲","嘉善","海盐","海宁","平湖","桐乡"];
    if (text =="杭州市") {
        var iframe = document.getElementById("showcharts");
        iframe.src = "hangzhou_if.html?city=杭州&region=";
    }else if (hangzhoulist.indexOf(text)>=0) {
        var iframe = document.getElementById("showcharts");
        iframe.src = "hangzhou_if.html?city=杭州&region="+text;
    }else if (text=="宁波市"){
        var iframe = document.getElementById("showcharts");
        iframe.src = "hangzhou_if.html?city=宁波&region=";
    } else if (ningbolist.indexOf(text)>=0) {
        var iframe = document.getElementById("showcharts");
        iframe.src = "hangzhou_if.html?city=宁波&region="+text;
    }else if (text=="嘉兴市"){
        var iframe = document.getElementById("showcharts");
        iframe.src = "hangzhou_if.html?city=嘉兴&region=";
    } else if (jiaxinglist.indexOf(text)>=0) {
        var iframe = document.getElementById("showcharts");
        iframe.src = "hangzhou_if.html?city=嘉兴&region="+text;
    }else if (text=="金华市"){
        var iframe = document.getElementById("showcharts");
        iframe.src = "hangzhou_if.html?city=金华&region=";
    } else if (jinhualist.indexOf(text)>=0) {
        var iframe = document.getElementById("showcharts");
        iframe.src = "hangzhou_if.html?city=金华&region="+text;
    }else if (text=="绍兴市"){
        var iframe = document.getElementById("showcharts");
        iframe.src = "hangzhou_if.html?city=绍兴&region=";
    } else if (shaoxinglsit.indexOf(text)>=0) {
        var iframe = document.getElementById("showcharts");
        iframe.src = "hangzhou_if.html?city=绍兴&region="+text;
    }else{
        var iframe = document.getElementById("showcharts");
        iframe.src = "analysis_charts.html";
    }
}
$(function() {
    $('.d-firstNav').click(function(e) {
        console.log("111");
        dropSwift($(this), '.d-firstDrop');
        e.stopPropagation();
    });
    $('.d-secondNav').click(function(e) {
        dropSwift($(this), '.d-secondDrop');
        e.stopPropagation();
    });



    /**
     * @param dom   点击的当前元素
     * @param drop  下一级菜单
     */
    function dropSwift(dom, drop) {
        //点击当前元素，收起或者伸展下一级菜单


        dom.next().slideToggle();

        //设置旋转效果

        //1.将所有的元素都至为初始的状态
        dom.parent().siblings().find('.fa-caret-right').removeClass('iconRotate');

        //2.点击该层，将其他显示的下滑层隐藏
        dom.parent().siblings().find(drop).slideUp();

        var iconChevron = dom.find('.fa-caret-right');
        if(iconChevron.hasClass('iconRotate')) {
            iconChevron.removeClass('iconRotate');
        } else {
            iconChevron.addClass('iconRotate');
        }
    }
})
