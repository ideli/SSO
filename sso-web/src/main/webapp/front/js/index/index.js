$(function(){
    /*菜单栏数据加载*/
    $.ajax({
        type: 'get',
        url: 'http://192.168.15.190:8812/alimsService/test/getRsp',
        dataType:'jsonp',
        jsonp:'jsonpcallback',
        data:{
            "data":"D:\\E\\testJson\\test.json"
        },
        success  : function(data) {  
            $(".menu-data").html(template(meun,data));
        //$(".sidebar-menu").mCustomScrollbar();
        },  
        error : function() {  
            //alert('fail');  
        }  
    })
     /*头部数据获取*/
     $.ajax({
        type: 'get',
        url: 'http://192.168.15.190:8812/alimsService/test/getRsp',
        dataType:'jsonp',
        jsonp:'jsonpcallback',
        data:{
            "data":"D:\\E\\testJson\\alltop.json"
        },
        success: function(data){
            $(".top-left").append(template(topData,data));
        },
        error:function(){
            
        }
    })
    /*topcenter数据*/
    $.ajax({
        type: 'get',
        url: 'http://192.168.15.190:8812/alimsService/test/getRsp',
        dataType:'jsonp',
        jsonp:'jsonpcallback',
        data:{
            "data":"D:\\E\\testJson\\topcenter.json"
        },
        success: function(data){
            $(".top-center").append(template(topcenterData,data));
        },
        error:function(){

        }
    })
    $("#toggle_sidemenu_l,.fa-sign-out").click(function(){
       if($(".nano-content .person-top").hasClass("click-show")){
            $(".nano-content .person-top").removeClass("click-show");
       }else{
            $(".nano-content .person-top").addClass("click-show");
       }
    })

    /*点击检验鉴定显示菜单，及改变当前内容区页面*/
    $('#inspectionIndent').click(function(){
        $('#menu-data',parent.document).find('li').show();
        $("iframe",parent.document.body).attr("src","weituo.html");
    })
    /*退出*/
    $('#logout').click(function(){
        location.href="../alims/login/login.html";
    })



})


