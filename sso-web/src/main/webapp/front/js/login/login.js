$(function(){
	/*头部数据获取*/
	 $.ajax({
        type: 'get',
        url: 'http://192.168.15.190:8812/alimsService/test/getRsp',
        dataType:'jsonp',
        jsonp:'jsonpcallback',
        data:{
            "data":"D:\\E\\testJson\\top.json"
        },
        success: function(data){
            $(".top-left").append(template(topData,data));
        },
        error:function(){
            
        }
    })
	 /*尾部数据获取*/
	 $.ajax({
        type: 'get',
        url: 'http://192.168.15.190:8812/alimsService/test/getRsp',
        dataType:'jsonp',
        jsonp:'jsonpcallback',
        data:{
            "data":"D:\\E\\testJson\\bottom.json"
        },
        success: function(data){
            $(".bottom").html(template(bottomdata,data));
        },
        error:function(){
            
        }
    })
    $('#login-div').on('click',function(){
    	alert(11);
    	$('#login-form').submit();
        var userName = $('.login-user').val();
        var passWord =  $('.login-pwd').val();
        
        //用户登录

    })
    $('.running_check').click(function(){
        layer.open({
            type: 1,
            title : ['运行环境检查'],
            content: $('#running_check') //这里content是一个DOM
        });
    })

    $('.zc-check').on('click',function(){
        setTimeout('$(".js-zc").show("slow")',1000);
        setTimeout('$("#js-zc").show("slow")',1000);
        setTimeout('$(".brower-bb").show("slow")',2000);
        setTimeout('$("#brower-bb").show("slow")',2000);
        setTimeout('$(".cookie-zc").show("slow")',3000);
        setTimeout('$("#cookie-zc").show("slow")',3000);
        setTimeout('$(".fp-zc").show("slow")',4000);
        setTimeout('$("#fp-zc").show("slow")',4000);
        setTimeout('$(".dns-cx").show("slow")',5000);
        setTimeout('$("#dns-cx").show("slow")',5000);
        setTimeout('$(".hc-qc").show("slow")',6000);
        setTimeout('$("#hc-qc").show("slow")',6000);
        setTimeout('$(".zc-check").val("再次检查")',7000);
        setTimeout('$(".zc-check").addClass("zc-check")',7000);
        $('.zc-check').on('click',function(){
            $('.run-ch-r .query-results ul').hide();
            $('.run-ch-l ul .trueF').hide();
        })
    })
	 /*轮播图数据获取*/
	 $.ajax({
        type: 'get',
        url: 'http://172.16.0.230:8812/alimsService/test/getRsp',
        dataType:'jsonp',
        jsonp:'jsonpcallback',
        data:{
            "data":"D:\\E\\testJson\\banner.json"
        },
        success: function(data){
            $(".index_focus").append(template(banner,data));
            $(".index_focus").hover(function(){
				$(this).find(".index_focus_pre,.index_focus_next").stop(true, true).fadeTo("show", 1)
			},function(){
				$(this).find(".index_focus_pre,.index_focus_next").fadeOut()
			});
			
			$(".index_focus").slide({
				titCell: ".slide_nav a ",
				mainCell: ".bd ul",
				delayTime: 500,
				interTime: 3500,
				prevCell:".index_focus_pre",
				nextCell:".index_focus_next",
				effect: "fold",
				autoPlay: true,
				trigger: "click",
				startFun:function(i){
					$(".index_focus_info").eq(i).find("h3").css("display","block").fadeTo(1000,1);
					$(".index_focus_info").eq(i).find(".text").css("display","block").fadeTo(1000,1);
				}
			});
        },
        error:function(){
            
        }
    });
     var isChrome = navigator.userAgent.toLowerCase().match(/chrome/) != null;//判断是否是谷歌浏览器
         if(isChrome){
            $(".content-login").show();
            $(".wrong-info").hide();
         } else{
            $(".content-login").hide();
            $(".wrong-info").show();
         }

})