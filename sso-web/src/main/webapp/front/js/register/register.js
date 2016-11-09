

;(function($){
	var username=$("#username"),
	    card=$("#card"),
	    number=$("#number"),
	    phone=$("#phone"),
	    account=$("#account"),
	    password=$("#password"),
	    sure=$("#sure"),
	    unit=$("#unit"),
	    register=$("#register"),
	    dactyl=$("#dactyl").children("em"),
      usernameflag=false,
      cardflag=false,
      numberflag=false,
      phoneflag=false,
      accountflag=false,
      passwordflag=false,
      sureflag=false;

    username.focus(function(){		
        $(this).next("p").css("display","inline-block").removeClass("right").show().text("请输入用户姓名")
    });
    username.blur(function(){
    	var usernameVal=$(this).val(),
    	    p=$(this).next(),
    	    reg=/^[\u4e00-\u9fa5]{0,}$/;  	    
      if(usernameVal==""){
      	p.css("display","inline-block").show().text("用户姓名不能为空");
       }
        else if(!reg.test(usernameVal)){
            p.css("display","inline-block").show().text("输入有误");
       } else{
       	    p.css("display","inline-block").show().addClass("right").text("");
       }

       if(p.text()!='请输入用户姓名' && p.text()!='用户姓名不能为空' && p.text()!='输入有误'){
        usernameflag=false;
       }else{
        usernameflag=true;
       }
          
    });


    card.focus(function(){	
        $(this).next("p").css("display","inline-block").removeClass("right").show().text("请输入身份证号码")
    });
    card.blur(function(){
    	var cardVal=$(this).val(),
    	    p=$(this).next(),
    	    reg=/^\d{17}[X\d]$/;
    if(cardVal==""){
    	p.css("display","inline-block").show().text("身份证号码不能为空");
   }
    else if(!reg.test(cardVal)){
        p.css("display","inline-block").show().text("输入有误");
   } else{
   	    p.css("display","inline-block").show().addClass("right").text("");
   }

   if(p.text()!='请输入身份证号码' && p.text()!='身份证号码不能为空' && p.text()!='输入有误'){
        cardflag=false;
       }else{
        cardflag=true;
       }
          
 });


    number.focus(function(){		
        $(this).next("p").css("display","inline-block").removeClass("right").show().text("请输入警员编号")
    });
    number.blur(function(){
    	var numberVal=$(this).val(),
    	    p=$(this).next(),
    	    reg=/^\d{6}$/;
    if(numberVal==""){
    	p.css("display","inline-block").show().text("警员编号不能为空");
   }
    else if(!reg.test(numberVal)){
        p.css("display","inline-block").show().text("输入有误");
   } else{
   	    p.css("display","inline-block").show().addClass("right").text("");
   }

    if(p.text()!='请输入警员编号' && p.text()!='警员编号不能为空' && p.text()!='输入有误'){
        numberflag=false;
       }else{
        numberflag=true;
       }
          
 });



    phone.focus(function(){		
        $(this).next("p").css("display","inline-block").removeClass("right").show().text("请输入手机号码")
    });
    phone.blur(function(){
    	var phoneVal=$(this).val(),
    	    p=$(this).next(),
    	    reg=/^1[34578]\d{9}$/;
    if(phoneVal==""){
    	p.css("display","inline-block").show().text("手机号码不能为空");
   }
    else if(!reg.test(phoneVal)){
        p.css("display","inline-block").show().text("输入有误");
   } else{
   	    p.css("display","inline-block").show().addClass("right").text("");
   }

   if(p.text()!='请输入手机号码' && p.text()!='手机号码不能为空' && p.text()!='输入有误'){
        phoneflag=false;
       }else{
        phoneflag=true;
       }
          
 });

    account.focus(function(){    
        $(this).next("p").css("display","inline-block").removeClass("right").show().text("请输入登录账号")
    });
    account.blur(function(){
      var accountVal=$(this).val(),
          p=$(this).next(),
          reg=/^\d{6}$/;
    if(accountVal==""){
      p.css("display","inline-block").show().text("登录账号不能为空");
   }
    else if(!reg.test(accountVal)){
        p.css("display","inline-block").show().text("输入有误");
   } else{
        p.css("display","inline-block").show().addClass("right").text("");
   }

   if(p.text()!='请输入登录账号' && p.text()!='登录账号不能为空' && p.text()!='输入有误'){
        accountflag=false;
       }else{
        accountflag=true;
       }
          
 });



    password.focus(function(){	
    	var dactyl=$("#dactyl").children("em");
    	    dactyl.removeClass("on");
            $(this).next("p").css("display","inline-block").removeClass("right").show().text("请设置您的密码,以字母开头,长度在6-18之间")
    });
    password.blur(function(){
    	var passwordVal=$(this).val(),
    	    p=$(this).next(),
    	    reg=/^[a-zA-Z]\w{5,17}$/;

    if(passwordVal==""){
    	p.css("display","inline-block").show().text("密码不能为空");
   }
    else if(!reg.test(passwordVal)){
        p.css("display","inline-block").show().text("输入有误");
   } else{
   	    p.css("display","inline-block").show().addClass("right").text("");
   }

   if(p.text()!='请设置您的密码,以字母开头,长度在6-18之间' && p.text()!='密码不能为空' && p.text()!='输入有误'){
        passwordflag=false;
       }else{
        passwordflag=true;
       }
          
 });
   password.keydown(function(){
    	var passwordVal=$(this).val(),
    	    dactyl=$("#dactyl").children("em");
    	if(passwordVal.length>1 && passwordVal.length<=4) {
    	      dactyl.eq(0).addClass("on").siblings().removeClass("on")
    	} else if(passwordVal.length>4  && passwordVal.length<=7){
              dactyl.eq(1).addClass("on").siblings().removeClass("on")
    	}else if(passwordVal.length>7 && passwordVal.length<=10){
              dactyl.eq(2).addClass("on").siblings().removeClass("on")
    	}
    });

     sure.focus(function(){	 	
        $(this).next("p").css("display","inline-block").removeClass("right").show().text("请再次输入登录密码")
    });
    sure.blur(function(){
    	var sureVal=$(this).val(),
    	    p=$(this).next(),
    	    passwordVal=$("#password").val();
    if(sureVal==""){
    	p.css("display","inline-block").show().text("密码不能为空");
   }
    else if(sureVal!=passwordVal){
        p.css("display","inline-block").show().text("请保证两次输入一致");
   } else{
   	    p.css("display","inline-block").show().addClass("right").text("");
    }

    if(p.text()!='请再次输入登录密码' && p.text()!='密码不能为空' && p.text()!='请保证两次输入一致'){
        sureflag=false;
       }else{
        sureflag=true;
       }
          
 });


    register.click(function(){
      username.blur();
      card.blur();
      number.blur();
      phone.blur();
      account.blur();
      password.blur();
      sure.blur();
      if(usernameflag==false && cardflag==false && numberflag==false && phoneflag==false && passwordflag==false && sureflag==false &&usernameflag==false){
        location.href="success.html"
      }else{     
        return false;
      }

    })





})(jQuery)