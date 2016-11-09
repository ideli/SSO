var currsearch = '<div id="searchTerm" class="fade tab-pane hiddenover active in">'+
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
                                        '<div class="clearfix">'+
                                            '<div class="col-sm-2">'+
                                              '<input type="text" name="fromDate" id="fromDate" placeholder="From date..." class="float-left mrg10R form-control">'+
                                            '</div>'+
                                            '<div class="col-sm-2">'+
                                             '<input type="text" name="toDate" id="toDate" placeholder="To date..." class="float-left form-control">'+
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
var histsearch ='<div id="searchhisty" class="tab-pane fade hiddenover">'+
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
