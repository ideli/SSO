var topData = '<img src="<%=img%>" class="top-left-left"/>'+
        '<div class="top-left-right">'+
            '<%for(var i = 0; i < result.length; i++) {%>'+
                '<p><%=result[i].name%></p>'+
            '<%}%>'+
        '</div>';
var bottomdata = '<div class="bottom-container">'+
        '<img src="<%=img%>" class="bottom-left"/>'+
        '<div class="bottom-right">'+
            '<%for(var i = 0; i < result.length; i++) {%>'+
                '<p><%=result[i].name%></p>'+
            '<%}%>'+
        '</div>';
    var banner ='<div class="bd">'+
                    '<ul>'+
                        '<%for(var i = 0; i < result.length; i++) {%>'+
                            '<li>'+
                                '<a href="#" class="pic">'+
                                    '<img class="pic" src="<%=result[i].thumb%>" width="1600" height="600" alt="">'+
                                '</a>'+
                            '</li>'+
                        '<%}%>'+
                    '</ul>'+
                '</div>'+
                '<div class="slide_nav">'+
                    '<%for(var i = 0; i < result.length; i++) {%>'+
                        '<a href="javascript:;"><%=result[i].icon%></a>'+
                    '<%}%>'+
                '</div>';

               
