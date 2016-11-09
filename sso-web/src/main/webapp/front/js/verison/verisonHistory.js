
;(function($){

  //HTML元素灌注
  function Ajax(){
    $.ajax({
      type: 'post',
      url: '../../json/versionHistory.json',
      data:'',
      dataType:'json',
      jsonp:'jsonpcallback',
      success: function (data) {
        console.log(data);
        var tpl = document.getElementById('tpl').innerHTML;
        $('#timeList').html(template(tpl, data));
      },
      error: function (xhr,type) {
        console.log('ajax失败'+xhr+'错误类型'+type);
        console.log(xhr);
      }
    })
  }
  Ajax();
  //HTML元素灌注

  //风琴效果-简易
  $('#timeList').delegate('.total','click', function () {
    if($(this).hasClass('open')){
      console.log('1');
      $(this).removeClass('open');
      $(this).parent().children('ul').hide();
    }else{
      console.log('2');
      $(this).addClass('open');
      $(this).parent().children('ul').show();
    }
  });
  //风琴效果-简易

})(jQuery);