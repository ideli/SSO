var currsearch = '<div id="tab1" class="tab-pane active hiddenover">'+
                        '<%for(var i = 0; i < result.length; i++) {%>'+
                            '<%if(result[i].type==="time"){%>'+
                                '<div class="search-px">'+
                                    '<p><%=result[i].name%></p>'+
                                    '<ul>'+
                                        '<%for(var j = 0; j < result[i].set.length; j++) {%>'+ 
                                            '<li><%=result[i].set[j].search%></li>'+
                                        '<%}%>'+
                                    '</ul>'+
                                    '<div class="form-group">'+
                                        '<div class="col-md-2">'+
                                            '<div class="input-group date" id="datetimepicker2">'+
                                                '<span class="input-group-addon cursor">'+
                                                    '<i class="fa fa-calendar"></i>'+
                                                '</span>'+
                                                '<input type="text" class="form-control">'+
                                            '</div>'+
                                        '</div>'+
                                        '<div class="col-md-2">'+
                                            '<div class="input-group date" id="datetimepicker1">'+
                                                '<span class="input-group-addon cursor">'+
                                                    '<i class="fa fa-calendar"></i>'+
                                                '</span>'+
                                                '<input type="text" class="form-control">'+
                                            '</div>'+
                                        '</div>'+
                                    '</div>'+
                                '</div>'+
                            '<%} else {%>'+
                                '<div class="search-px">'+
                                    '<p><%=result[i].name%></p>'+
                                    '<ul>'+
                                        '<%for(var j = 0; j < result[i].set.length; j++) {%>'+ 
                                            '<li><%=result[i].set[j].search%></li>'+
                                        '<%}%>'+
                                    '</ul>'+
                                '</div>'+
                            '<%}%>'+
                        '<%}%>'+
                    '</div>';
var histsearch ='<div id="tab2" class="tab-pane">'+
                    '<%for(var i = 0; i < result.length; i++) {%>'+
                        '<div class="search-px">'+
                            '<p><%=result[i].name%></p>'+
                            '<ul>'+
                                '<%for(var j = 0; j < result[i].set.length; j++) {%>'+ 
                                    '<li><%=result[i].set[j].search%></li>'+
                                '<%}%>'+
                            '</ul>'+
                        '</div>'+
                    '<%}%>'+
                '</div>';
