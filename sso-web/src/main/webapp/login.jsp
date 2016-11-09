<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <!-- Meta, title, CSS, favicons, etc. -->
	<meta charset="utf-8">
	<title>登陆页面</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!-- Theme CSS -->
    <link rel="stylesheet" type="text/css" href="${ctx}/front/vendor/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/front/css/theme/default/css/login.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/front/css/theme/default/css/common.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/front/vendor/bootstrap/bootstrap-combined.min.css">
    <!-- 轮播图 -->
    <link rel="stylesheet" type="text/css" href="${ctx}/front/css/theme/default/css/style.css">


   
</head>
<body>
	<div class="container-fluid">
	  	<div class="top">
	  		<div class="top-left">
	  		</div>
	  		<div class="top-right">
	  			<ul>
	  				<li class="right-btn  running_check"><a href="#"><i class="right-btn-write"></i></a></li>
	  				<li class="right-btn"><a href="#"><i class="right-btn-vedio"></i></a></li>
	  				<li class="right-btn"><a href="#"><i class="right-btn-three"></i></a></li>
	  				<li class="right-btn"><a href="#"><i class="right-btn-weixin"></i></a></li>
	  			</ul>
	  		</div>
	  		<div style="clear:both;"></div>
	  	</div>
	  	<div class="content">
	  		<div class="index_focus">
				<a href="javascript:;" class="index_focus_pre" title="上一张">上一张</a>
				<a href="javascript:;" class="index_focus_next" title="下一张">下一张</a>
			</div>
	  	 	<div class="content-login">
			  		<h2>用户登录</h2>
			  		<div class="login-all">
			  					<form action="postLogin.html" method="post" id="login-form">
			  						<input type="hidden" name="serviceUrl" value="${serviceUrl}">
			  			            <div class="user-All">
			  	  						<i class="user-img"></i><input type="text" name="username" class="login-user input-btn" placeholder="用户名"/>
			  	  					</div>
			  			  			<div class="pwd-All">
			  			  				<i class="pwd-img"></i><input type="password" name="password" class="login-pwd input-btn" placeholder="密码"/>
			  			  			</div>
			  			  			<p class="login-html" id="login-div"><a href="#" >立即登录</a></p>
			  			  		</form>
			  			  			<div class="content-intro">
			  			  				<span></span>
			  			  				<span>使用移动客户端</span>
			  			  				<a href="#" class="up-user">
			  			  					<span class="up-btn">下载使用</span>
			  			  					<i class="down-sj"></i></a>
			  			  			</div>
			  			  			<div class="content-add">
			  			  				<span>还没有账号？</span>
			  			  				<a href="../../reginster/register.html" class="resigter">立即注册</a>
			  			  			</div>
			  		</div>
			</div> 
	  		<div class="wrong-info">

	  		   <h1>平台不支持IE及其它浏览器，请先下载，安装Google浏览器并使用Google浏览器登录平台。	</h1>

	  		   <p>平台仅支持Google版本浏览器，为了达到最佳的使用效果，<span>点击下载Google浏览器。</span>
	  		   	
	  		   </p>
	  		</div>
	  	</div>

	  	<div class="bottom">
	  	</div>
		<div id="running_check">
			<div class="fl run-ch-l">
				<p class="d-ing">当前浏览器状态</p>
				<ul>
					<li>脚本语言支持<span class="trueF js-zc"><img src="${ctx}/front/css/images/login/dui.png" alt=""></span></li>
					<li>浏览器版本<span class="trueF brower-bb"><img src="${ctx}/front/css/images/login/dui.png" alt=""></li>
					<li>Cookie支持<span class="trueF cookie-zc"><img src="${ctx}/front/css/images/login/dui.png" alt=""></li>
					<li>Flash Player版本<span class="trueF fp-zc"><img src="${ctx}/front/css/images/login/dui.png" alt=""></li>
					<li>DNS查询刷新<span class="trueF dns-cx"><img src="${ctx}/front/css/images/login/dui.png" alt=""></li>
					<li>缓存清除<span class="trueF hc-qc"><img src="${ctx}/front/css/images/login/dui.png" alt=""></li>
				</ul>
				<input type="button" class="zc-check" value="启动">
			</div>
			<div class="fr run-ch-r">
				<div class="progress progress-striped active">
					<div class="bar">
					</div>
				</div>
				<div class="query-results">
					<ul id="js-zc">
						<li>脚本语言支持：</li>
						<li>脚本语言支持正常。<noscript>脚本语言支持异常。</noscript></li>
					</ul>
					<ul id="brower-bb">
						<li id="jsCheck_id">浏览器版本：</li>
						<li>浏览器版本正常。<span class="list-detail" id="useragent"></span><br/></li>
					</ul>
					<ul id="cookie-zc">
						<li id="bolCookie">Cookie支持：</li>
						<li>Cookie支持正常。</li>
					</ul>
					<ul id="fp-zc">
						<li id="bolFlash">Flash Player版本：</li>
						<li>Flash Player版本正常。<span class="list-detail">当前版本为：19</span></li>
					</ul>
					<ul id="dns-cx">
						<li>DNS查询刷新</li>
						<li>DNS查询刷新正常</li>
						<!-- <li><span class="list-detail">访问http://www.baidu.com超时</span></li> -->
					</ul>
					<ul id="hc-qc">
						<li>缓存清除：</li>
						<li>缓存清除正常。</li>
					</ul>
					<p class="error-red">您的网络目前稳定，建议联系网管进行检测。</p>
				</div>
			</div>
		</div>
	</div>
	<!-- jQuery -->
	<script type="text/javascript" src="${ctx}/front/js/common/jquery-2.0.0.js"></script>
	<!-- 轮播图 -->
	<script type="text/javascript" src="${ctx}/front/js/plugins/elements/jquery.SuperSlide.js"></script>
	<script type="text/javascript" src="${ctx}/front/js/login/login.js"></script>
	<!-- js模板引擎 -->
	<script type="text/javascript" src="${ctx}/front/vendor/template/template.js"></script> 
	<!-- 头部模板 -->
	<script type="text/javascript" src="${ctx}/front/js/tpl/templatetpl/top.tpl.js"></script>
	<script type="text/javascript" src="${ctx}/front/js/login/browserdetecter.js"></script>
	<script type="text/javascript" src="${ctx}/front/js/login/DD_belatedPNG.js"></script>
	<script type="text/javascript" src="${ctx}/front/vendor/layer/layer.js"></script>
	<script type="text/javascript" src ="${ctx}/front/js/tpl/main.js"></script>
	<script src="${ctx}/front/vendor/bootstrap/bootstrap.min.js"></script>


	<script type="text/javascript">

$(function(){
    $(".mxScrollbar2>dl>dd").hide()
    $(".mxScrollbar2>dl>dt").on("click",function(){
        if($(this).parent().children("dd").is(":visible")){
            $(this).parent().children("dd").hide()
            $(this).children("span").removeClass("down")
        }
        else{$(this).parent().children("dd").show()
            $(this).parent().siblings().children("dd").hide()
            $(this).children("span").addClass("down")
            $(this).parent().siblings().children("dt").children("span").removeClass("down")
        }
        $(this).parent().addClass("gl").siblings().removeClass("gl")
    })
})
	 </script>
	<script type="text/javascript">

		$(document).ready(function(){

			//调整界面

			$(window).bind("resize",function() {

				resizeLayout();

			});

			resizeLayout();

			//检测浏览器

			$("#browserName").html($.browser.name);

			$("#browserVer").html($.browser.version);

			$("#engineName").html($.browser.engine);

			$("#engineVer").html($.browser.engineVersion);

			$("#OSName").html($.browser.OS);

			$("#OSLanguage").html($.browser.language);

			$("#useragent").html($.browser.userAgent);



			switch ($.browser.name) {

				case "unknown":

					$("#browserName").html("未知");

					$("#browerIcon").attr("class",$.browser.name);

					break;

				case "Internet Explorer":

					switch ($.browser.version) {

						case 6:

							$("#browerIcon").attr("class","IE6");

							break;

						case 7:

							$("#browerIcon").attr("class","IE7");

							break;

						case 8:

							$("#browerIcon").attr("class","IE8");

							break;

						case 9:

							$("#browerIcon").attr("class","IE9");

							break;

					}

					break;

				case "Tencent Traveler":

					$("#browserName").html("腾讯浏览器");

					$("#browerIcon").attr("class","TT");

					break;

				case "Tencent Traveler":

					$("#browserName").html("搜狗浏览器");

					$("#browerIcon").attr("class","Sougou");

					break;

				case "Maxthon":

					$("#browserName").html("遨游浏览器");

					$("#browerIcon").attr("class","Maxthon");

					break;

				case "The World":

					$("#browserName").html("世界之窗");

					$("#browerIcon").attr("class","World");

					break;

				case "360SE":

					$("#browserName").html("360浏览器");

					$("#browerIcon").attr("class","SE360");

					break;

				default:

					$("#browerIcon").attr("class",$.browser.name);

			}



			switch ($.browser.engine) {

				case "Trident":

					$("#engineName").html("Trident（IE内核）");

					if ($.browser.engineVersion =="4(IE6)") {

						fixPng();

					}

					if ($.browser.engineVersion =="4(IE6)" || $.browser.engineVersion =="4(IE7)") {

						showAlert();

					}

					break;

			}



			switch ($.browser.language) {

				case "zh-cn":

					$("#OSLanguage").html("中文");

					break;

			}



			//功能插件

			$("#bolCookie").addClass($.browser.cookieEnabled ? "true" : "false");

			$("#bolFlash").addClass($.browser.flashSupport ? "true" : "false");




		});



		function showAlert() {

			$("#alertWrap .alertMessage").html("<p>当前浏览器采用由微软公司发布的IE6/IE7浏览器内核，该内核发布近10年，存在众多安全性问题，效率低下且不支持网络新标准、新技术，微软官方已建议用户立即采用其他浏览器。</p><p>为保障正常申报，请采用其他现代标准浏览器。</p>");

			$("#overlay").show();

		}



		function closeAlert() {

			$("#overlay").hide();

		}



		function fixPng() {

			//修复IE6

			DD_belatedPNG.fix("#overlay");

			DD_belatedPNG.fix("#alertWrap");

			DD_belatedPNG.fix("#alertWrap a img");

		}



		//调整界面大小函数

		function resizeLayout () {

			window.parent.$("#mainFrame").height($("body div.pageMain").height()+40);

			$("#overlay").height($("body div.pageMain").height()+40);

		}

	</script>

</body>
</html>