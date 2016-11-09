
;(function ($){
   
    $(".mxScrollbar2>dl>dt").on("click",function(){
        if($(this).parent().children("dd").is(":visible")){
            $(this).parent().children("dd").hide()
            $(this).children("span").removeClass("down").addClass("up")
        }
        else{$(this).parent().children("dd").show()
            $(this).parent().siblings().children("dd").hide()
            $(this).children("span").addClass("up")
            $(this).parent().siblings().children("dt").children("span").removeClass("down")
          }
        $(this).parent().addClass("gl").siblings().removeClass("gl")
    })

	
})(jQuery)

 	


	

 


