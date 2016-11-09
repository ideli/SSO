jQuery(document).ready(function() {
    "use strict";

    // Init Theme Core    
    Core.init();

    // Init Theme Core    
    Demo.init();

    /*$('input').on('click',function() {
        alert($(this).prop('checked'));
    });*/

    // Init custom page animation
    setTimeout(function() {
        $('.custom-nav-animation li').each(function(i, e) {
            var This = $(this);
            var timer = setTimeout(function() {
                This.addClass('animated animated-short zoomIn');
            }, 50 * i);
        });
    }, 500);

    // Init tray navigation smooth scroll
    $('.tray-nav a').smoothScroll({
        offset: -145
    });

    // Form Switcher
    $('#form-switcher > button').on('click', function() {
        var btnData = $(this).data('form-layout');
        var btnActive = $('#form-elements-pane .admin-form.active');

        // Remove any existing animations and then fade current form out
        btnActive.removeClass('slideInUp').addClass('animated fadeOutRight animated-shorter');
        // When above exit animation ends remove leftover classes and animate the new form in
        btnActive.one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function() {
            btnActive.removeClass('active fadeOutRight animated-shorter');
            $('#' + btnData).addClass('active animated slideInUp animated-shorter')
        });
    });

    // Cache some dom elements
    var adminForm = $('.admin-form');
    var options = adminForm.find('.option');
    var switches = adminForm.find('.switch');
    var buttons = adminForm.find('.button');

    var Panel = $('#p1');

    // Form Skin Switcher
    $('#skin-switcher a').on('click', function() {
        var btnData = $(this).data('form-skin');

        $('#skin-switcher a').removeClass('item-active');
        $(this).addClass('item-active')

        adminForm.each(function(i, e) {
            var skins = 'theme-primary theme-info theme-success theme-warning theme-danger theme-alert theme-system theme-dark'
            var panelSkins = 'panel-primary panel-info panel-success panel-warning panel-danger panel-alert panel-system panel-dark'
            $(e).removeClass(skins).addClass('theme-' + btnData);
            Panel.removeClass(panelSkins).addClass('panel-' + btnData);
        });

        $(options).each(function(i, e) {
            if ($(e).hasClass('block')) {
                $(e).removeClass().addClass('block mt15 option option-' + btnData);
            } else {
                $(e).removeClass().addClass('option option-' + btnData);
            }
        });
        $(switches).each(function(i, ele) {
            if ($(ele).hasClass('switch-round')) {
                if ($(ele).hasClass('block')) {
                    $(ele).removeClass().addClass('block mt15 switch switch-round switch-' + btnData);
                } else {
                    $(ele).removeClass().addClass('switch switch-round switch-' + btnData);
                }
            } else {
                if ($(ele).hasClass('block')) {
                    $(ele).removeClass().addClass('block mt15 switch switch-' + btnData);
                } else {
                    $(ele).removeClass().addClass('switch switch-' + btnData);
                }
            }

        });
        buttons.removeClass().addClass('button btn-' + btnData);
    });


    setTimeout(function() {
        adminForm.addClass('theme-primary');
        Panel.addClass('panel-primary');

        $(options).each(function(i, e) {
            if ($(e).hasClass('block')) {
                $(e).removeClass().addClass('block mt15 option option-primary');
            } else {
                $(e).removeClass().addClass('option option-primary');
            }
        });
        $(switches).each(function(i, ele) {

            if ($(ele).hasClass('switch-round')) {
                if ($(ele).hasClass('block')) {
                    $(ele).removeClass().addClass('block mt15 switch switch-round switch-primary');
                } else {
                    $(ele).removeClass().addClass('switch switch-round switch-primary');
                }
            } else {
                if ($(ele).hasClass('block')) {
                    $(ele).removeClass().addClass('block mt15 switch switch-primary');
                } else {
                    $(ele).removeClass().addClass('switch switch-primary');
                }
            }
        });
        buttons.removeClass().addClass('button btn-primary');
    }, 2200);


});