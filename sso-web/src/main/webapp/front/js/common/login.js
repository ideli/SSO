$(function(){
/*判断浏览器版本*/ 
    function getOs() 
    { 
        var OsObject = "";  
       if(isChrome=navigator.userAgent.indexOf("Chrome")!=-1){ 
            return "Chrome"; 
       }else{
            alert("不是chorme");
       }

    }
    getOs(); 
	/*头部数据获取*/
	 $.ajax({
        type: 'post',
        url: '../json/top.json',
        cache: false,
        dataType:'json',
        data:'',
        success: function(data){
            $(".top-left").append(template(topData,data));
        },
        error:function(){
            
        }
    })
	 /*尾部数据获取*/
	 $.ajax({
        type: 'post',
        url: '../json/bottom.json',
        cache: false,
        dataType:'json',
        data:'',
        success: function(data){
            $(".bottom").html(template(bottomdata,data));
        },
        error:function(){
            
        }
    })

	 /*轮播图数据获取*/
	 $.ajax({
        type: 'post',
        url: '../json/banner.json',
        cache: false,
        dataType:'json',
        data:'',
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
    })
})