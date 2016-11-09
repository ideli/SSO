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
    /*搜索条件切换*/
    $('.search-tj li:eq(0) a').tab('show');
    /*菜单栏数据加载*/
    $.ajax({
        type: 'get',
        url: 'http://192.168.40.48:8812/alimsService/test/getRsp',
        dataType:'jsonp',
        jsonp:'jsonpcallback',
        data:{
            "data":"E:\\serach.json"
        },
        success  : function(data) {  
            alert(data);  
        },  
        error : function() {  
            alert('fail');  
        }  
    })
    /*搜索数据加载*/
    $.ajax({
        type: 'post',
        url: '../json/search.json',
        cache: false,
        dataType:'json',
        data:'',
        success: function(data){
            $(".defin-search").append(template(currsearch,data));
        },
        error:function(){
            
        }
    })
     /*历史搜索数据加载*/
    $.ajax({
        type: 'post',
        url: '../json/historysearch.json',
        cache: false,
        dataType:'json',
        timeout : 120000,
        data:'',
        success: function(data){
            $(".defin-search").append(template(histsearch,data));
        },
        error:function(){
            
        }
    })/*头部数据获取*/
     $.ajax({
        type: 'post',
        url: '../json/alltop.json',
        cache: false,
        dataType:'json',
        data:'',
        success: function(data){
            $(".top-left").append(template(topData,data));
        },
        error:function(){
            
        }
    })
    /*搜索选择*/
    $(".defin-search").on("click",".search-px li",function(){
        $(this).parent().children().removeClass("Class-click");
        $(this).addClass("Class-click");
    })
    /*单击重置*/
    $(".btn-default").click(function(){
        $(".search-px li").removeClass("Class-click");
    })
    /*单击搜索下拉展开*/
    $(".dropLine").click(function(){
        var curr=this;
        if($("#p1 .defin-search div.active").hasClass("hiddenover")){
            $("#p1 .defin-search div.active,#p1 .defin-search").removeClass("hiddenover");
            $(curr).children("i").removeClass("drop-down");
            $(curr).children("i").addClass("drop-up");
        }else{
            $("#p1 .defin-search div.active,#p1 .defin-search").addClass("hiddenover");
            $(curr).children("i").removeClass("drop-up");
            $(curr).children("i").addClass("drop-down");
        }
    })
    $("#toggle_sidemenu_l,.fa-sign-out").click(function(){
       if($(".nano-content .person-top").hasClass("click-show")){
            $(".nano-content .person-top").removeClass("click-show");
       }else{
            $(".nano-content .person-top").addClass("click-show");
       }
    })
    /*给新追加的日期函数增加日期时间*/
    
    $("#dock-content").on("focus","#fromDate,#toDate",function(){
        $("#fromDate").datepicker({
        defaultDate: "",
        changeMonth: true,
        numberOfMonths: 1,
        onClose: function(selectedDate) {
            $("#toDate").datepicker("option", "minDate", selectedDate);
            }
        });
        $("#toDate").datepicker({
            defaultDate: "",
            changeMonth: true,
            numberOfMonths: 1,
            onClose: function(selectedDate) {
                $("#fromDate").datepicker("option", "maxDate", selectedDate);
            }
        });
    })
    /*表格数据加载*/
    $('#table').bootstrapTable({
        url: '../json/searchResult.json',
        method: 'post',      //请求方式（*）
        contentType: "application/x-www-form-urlencoded",
        toolbar: '#toolbar',    //工具按钮用哪个容器
        striped: true,      //是否显示行间隔色
        cache: false,      //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,     //是否显示分页（*）
        sortable: false,      //是否启用排序
        queryParams: 'queryParams',//传递参数（*）
        sidePagination: "server",   //分页方式：client客户端分页，server服务端分页（*）
        pageNumber:1,      //初始化加载第一页，默认第一页
        pageSize: 5,      //每页的记录行数（*）
        pageList: [5,10, 25, 50, 100],  //可供选择的每页的行数（*）
        search: false,      //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,
        showColumns: false,     //是否显示所有的列
        showRefresh: false,     //是否显示刷新按钮
      /*  showExport : true,
       exportTypes : ['csv', 'txt', 'excel'], */
        minimumCountColumns: 2,    //最少允许的列数
        clickToSelect: true,    //是否启用点击选中行
      // height: 500,      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "ID",      //每一行的唯一标识，一般为主键列
        showToggle:false,     //是否显示详细视图和列表视图的切换按钮
        cardView: false,     //是否显示详细视图
        detailView: false,     //是否显示父子表
        columns: [
            {
                field: "state",
                checkbox: true
            },
            {
                field: 'num',
                title: '序号'
            }, 
            {
                field: 'frozenDate',
                title: '委托时间'
            }, 
            {
                field: 'accountNo',
                title: '委托编号'
            },
            {
                field: 'caseName',
                title: '案件名称'
            },
            {
                field: 'client',
                title: '委托单位'
            },

            {
                field: 'profession',
                title: '专业'
            },
            {
                field: 'advice',
                title: '意见'
            },
            {
                field: 'identificationStatus',
                title: '鉴定状态'
            },
            {
                field: 'accountSource',
                title: '操作',
                align: 'center',
                /*events: operateEvents,*/
                formatter: operateFormatter
            }
        ]
    });
    /*操作按钮事件*/
     /*window.operateEvents = {
        'click .like': function (e, value, row, index) {
            var id="";
            $.each(row, function (key, value) {
                if(key=='id'){
                    id=value;
                }
            });    
           window.location='/dzpt/caseInfo/updatePageView.html?id='+id;    
        },
        'click .caseRelation': function (e, value, row, index) {
            window.open("/dzpt/caseRelation/pageView.html?caseId="+row.id);
        }
    };*/
    /*操作加载*/
    function operateFormatter(value, row, index) {
        return [
            '<a class="like" href="javascript:void(0)" title="修订">',
            '<span class="imoon imoon-pencil"></span>',
            '</a>  &nbsp;&nbsp;',
            '<a class="caseRelation" href="javascript:void(0)" title="关系图">',
            '<span class="glyphicons glyphicons-playing_dices"></span>',
            '</a>&nbsp;&nbsp;',
            '<a class="remove" href="javascript:void(0)" title="金流图">',
            '<span class="fa fa-file-text __web-inspector-hide-shortcut__"></span>',
            '</a>',
        ].join('');
    }
    //得到查询的参数
    queryParams = function (params) {
        var temp = { //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit, //页面大小
            offset: params.offset, //页码
            'accountNo' : $("#accountNo_filter").val(),
            'belongBank' : $("#belongBank_filter").val(),
            'frozenDateBeg' : $("#frozenDateBeg_filter").val(),
            'frozenDateEnd' : $("#frozenDateEnd_filter").val(),
            'frozenStatus' : $("#frozenStatus_filter").val()
        };
      return temp;
    };
    /*表格重新加载*/
    var checkBT = function (){
        $('#table').bootstrapTable('refresh',{url: '../json/searchResult.json'});
    }
    $(".btn-check").click(function(){
        checkBT();
    })
    /*删除弹窗*/
    $(".table-delete").click(function(){
        $("#table tbody tr").each(function(){
        })
        if($("#table tbody tr").hasClass("selected")){
            layer.confirm('你确定要删除该信息吗？', {
                skin: 'layui-layer-delet',
                btn: ['确定','取消'] //按钮
            }, function(){
              $("#table tbody tr.selected").remove();
              layer.msg('删除成功', {icon: 1});
            })
        }else{
            layer.msg('请选择要删除的信息');
        }
    })
    /*案件审核*/
    $(".case-check").click(function(){
        layer.confirm('你确定要审核该案件吗？', {
          btn: ['审核通过','审核不通过'] //按钮
        })
    }) 
   
})