var meun = /*'<aside id="sidebar_left" class="nano nano-light affix">'+
    '<div class="sidebar-left-content nano-content">'+*/
    '<div class="person-top">'+
                '<img src="<%=img%>">'+
                /*'<span id="toggle_sidemenu_l" class="glyphicons glyphicons-show_lines"></span>'+*/
                '<p><%=name%></p>'+
                '<p><%=auth%></p>'+
                '<div class="person-cont">'+
                    '<p class="person-l"><span class="glyphicons glyphicons-shopping_cart"></span><span>个人中心</span></p>'+
                    '<p class="person-r"><span class="glyphicons glyphicons-shopping_cart"></span><span>系统设置</span></p>'+
                '</div>'+
                '<div style="clear:both"></div>'+
            '</div>'+

            '<ul class="nav sidebar-menu">'+
                '<%for(var i = 0; i < result.length; i++) {%>'+
                    '<%if(result[i].set){%>'+
                        '<li>'+
                            '<a href="<%=result[i].src%>" class="accordion-toggle"  target="v">'+
                                '<span class="<%=result[i].thumb%>"></span>'+
                                '<span class="sidebar-title"><%=result[i].name%></span>'+
                                '<span class="caret"></span>'+
                            '</a>'+
                            '<ul class="nav sub-nav">'+
                               '<%for(var j = 0; j < result[i].set.length; j++) {%>'+ 
                                    '<li>'+
                                        '<a href="<%=result[i].set[j].src%>" target="v">'+
                                            '<span class="glyphicons glyphicons-book"></span>'+
                                            '<span><%=result[i].set[j].name%></span>'+
                                        '</a>'+
                                    '</li>'+
                               '<%}%>'+
                            '</ul>'+
                        '</li>'+
                    '<%} else {%>'+
                        '<li>'+
                            '<a href="<%=result[i].src%>"  target="v">'+
                                '<span class="<%=result[i].thumb%>"></span>'+
                                '<span class="sidebar-title"><%=result[i].name%></span>'+
                            '</a>'+
                        '</li>'+
                    '<%}%>'+
                '<%}%>'+
            '</ul>'/*+
        '</div>'+
        '</aside>'*/;
    /*'<div class="sidebar-toggle-mini">'+
    '<a href="#">'+
    '<span class="fa fa-sign-out"></span>'+
    '</a>'+
    '</div>';*/