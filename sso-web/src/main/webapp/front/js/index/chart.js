/**
 * Created by Administrator on 2016/6/1.
 */

// 基于准备好的dom，初始化 柱状图
var myChart = echarts.init(document.getElementById('histrogram'));
//$('#histrogram').css('height','400px')
// 指定图表的配置项和数据
option = {
    title: {
        text: '2016各专业受理检材数量统计',
        subtext: '合计:71866件',
        x:'center'
    },
    tooltip: {
        trigger: 'item'
    },
    toolbox: {
        show: true,
    },
    grid: {
        borderWidth: 0,
        y: 80,
        y2: 60
    },
    xAxis: [
        {
            type: 'category',
            show: false,
            data: ['法医病理', '法医物证', '法医临床', '亲子鉴定', '痕迹勘鉴', '毒物毒品', '微量物证', '文件检验', '声像侦鉴', '电子物证']
        }
    ],
    yAxis: [
        {
            type: 'value',
            show:false,
        }
    ],
    series: [
        {
            name: '2016各专业受理检材数量统计',
            type: 'bar',
            itemStyle: {
                normal: {
                    color: function(params) {
                        // build a color map as your need.
                        var colorList = [
                            '#418BCA','#EFAD4D','#6AB5B0','#E7905B','#3BAEDA',
                            '#D9544F','#B9A3F6','#92C976','#9BE6C7','#7B92A4'
                        ];
                        return colorList[params.dataIndex]
                    },
                    label: {
                        show: true,
                        position: 'top',
                        formatter: '{b}\n{c}'
                    }
                }
            },
            data: [5331,18820,24215,2735,1925,12129,263,4763,375,1890],
        }
    ]
};
// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);

//<!--图表饼图-->
var myChart1 = echarts.init(document.getElementById('histrogram1'));
var option = {
    title : {
        text: '2016各专业受理检材数量统计',
        subtext: '合计:1916件',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    series : [
        {
            name: '访问来源',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            itemStyle: {
                normal: {
                    color: function(params) {
                        // build a color map as your need.
                        var colorList = [
                            '#418BCA','#EFAD4D','#6AB5B0','#E7905B','#3BAEDA',
                            '#D9544F','#B9A3F6','#92C976','#9BE6C7','#7B92A4'
                        ];
                        return colorList[params.dataIndex]
                    },
                    label: {
                        show: true,
                        position: 'top',
                        formatter: '{b}\n{c}'
                    }
                }
            },
            data:[
                {value:353, name:'法医务'},
                {value:252, name:'法医病理'},
                {value:51, name:'声像侦鉴'},
                {value:43, name:'电子物证'},
                {value:77, name:'微量物证'},
                {value:32, name:'文件检验'},
                {value:211, name:'毒物毒品'},
                {value:48, name:'痕迹勘鉴'},
                {value:382, name:'亲子鉴定'},
                {value:175, name:'法医临床'}
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                },
                normal: {
                    color: function(params) {
                        // build a color map as your need.
                        var colorList = [
                            '#418BCA','#EFAD4D','#6AB5B0','#E7905B','#3BAEDA',
                            '#D9544F','#B9A3F6','#92C976','#9BE6C7','#7B92A4'
                        ];
                        return colorList[params.dataIndex]
                    },
                    label: {
                        show: true,
                        position: 'top',
                        formatter: '{b}\n{c}'
                    }
                }
            }
        }
    ]
};
myChart1.setOption(option);
