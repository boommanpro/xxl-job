function home() {
    $('#home_tab').addClass('active').siblings().removeClass('active');
    $('#home_box').css('display', 'block').siblings().css('display', 'none');
}

function jobinfo(name) {
    if ($('#jobinfo_tab').length <= 0) {
        $(".tabs").append('<li id="jobinfo_tab"><span>' + I18n.jobinfo_name + '</span><span class="ion-close-circled tab-close" onclick="tabClose(\'jobinfo\')" ></span></li>');
        $(".boxs").append('<div class="tabs-box" id="jobinfo_box"><iframe id="jobinfo_iframe" height="100%" width="100%" src="' + name + '/jobinfo" style="border: 0"></iframe></div>');
    }
    $('#jobinfo_tab').addClass('active').siblings().removeClass('active');
    $('#jobinfo_box').css('display', 'block').siblings().css('display', 'none');
}

function joblog(name) {
    if ($('#joblog_tab').length <= 0) {
        $(".tabs").append('<li id="joblog_tab"><span>' + I18n.joblog_name + '</span><span class="ion-close-circled tab-close" onclick="tabClose(\'joblog\')" ></span></li>');
        $(".boxs").append('<div class="tabs-box" id="joblog_box"><iframe height="100%" width="100%" src="' + name + '/joblog" style="border: 0"></iframe></div>');
    }
    $('#joblog_tab').addClass('active').siblings().removeClass('active');
    $('#joblog_box').css('display', 'block').siblings().css('display', 'none');
}

function joblogLink(href) {
    var joblogTab = $(window.parent.document.getElementById('joblog_tab'));
    var joblogBox = $(window.parent.document.getElementById('joblog_box'));
    var tabs = $(window.parent.document.getElementsByClassName('tabs'));
    var boxs = $(window.parent.document.getElementsByClassName('boxs'));
    if (joblogTab.length > 0) {
        joblogTab.remove();
        joblogBox.remove();
    }
    tabs.append('<li id="joblog_tab"><span>' + I18n.joblog_name + '</span><span class="ion-close-circled tab-close" onclick="tabClose(\'joblog\')" ></span></li>');
    boxs.append('<div class="tabs-box" id="joblog_box"><iframe height="100%" width="100%" src="' + href + '" style="border: 0"></iframe></div>');
    joblogTab = $(window.parent.document.getElementById('joblog_tab'));
    joblogBox = $(window.parent.document.getElementById('joblog_box'));
    joblogTab.addClass('active').siblings().removeClass('active');
    joblogBox.css('display', 'block').siblings().css('display', 'none');
}

function jobgroup(name) {
    if ($('#jobgroup_tab').length <= 0) {
        $(".tabs").append('<li id="jobgroup_tab"><span>' + I18n.jobgroup_name + '</span><span class="ion-close-circled tab-close" onclick="tabClose(\'jobgroup\')" ></span></li>');
        $(".boxs").append('<div class="tabs-box" id="jobgroup_box"><iframe height="100%" width="100%" src="' + name + '/jobgroup" style="border: 0"></iframe></div>');
    }
    $('#jobgroup_tab').addClass('active').siblings().removeClass('active');
    $('#jobgroup_box').css('display', 'block').siblings().css('display', 'none');
}

function users(name) {
    if ($('#user_tab').length <= 0) {
        $(".tabs").append('<li id="user_tab"><span>' + I18n.user_manage + '</span><span class="ion-close-circled tab-close" onclick="tabClose(\'user\')" ></span></li>');
        $(".boxs").append('<div class="tabs-box" id="user_box"><iframe height="100%" width="100%" src="' + name + '/user" style="border: 0"></iframe></div>');
    }
    $('#user_tab').addClass('active').siblings().removeClass('active');
    $('#user_box').css('display', 'block').siblings().css('display', 'none');
}

function help(name) {
    if ($('#help_tab').length <= 0) {
        $(".tabs").append('<li id="help_tab"><span>' + I18n.job_help + '</span><span class="ion-close-circled tab-close" onclick="tabClose(\'help\')" ></span></li>');
        $(".boxs").append('<div class="tabs-box" id="help_box"><iframe height="100%" width="100%" src="' + name + '/help" style="border: 0"></iframe></div>');
    }
    $('#help_tab').addClass('active').siblings().removeClass('active');
    $('#help_box').css('display', 'block').siblings().css('display', 'none');
}

function tabClose(param) {
    if ($('#' + param + '_tab').length > 0) {
        $('#' + param + '_tab').remove();
        $('#' + param + '_box').remove();
    }
    if(!$($(".tabs").children()).hasClass("active")) {
        var length = $('.tabs').children("li").length;
        $($(".tabs").children()[length - 1]).addClass("active").siblings().removeClass('active');
        $($(".boxs").children()[length - 1]).css('display', 'block').siblings().css('display', 'none');
    }
}

$(function(){

    $("#jobinfo_close").click(function () {
        alert(1122)
    });

	// logout
	$("#logoutBtn").click(function(){
		layer.confirm( I18n.logout_confirm , {
			icon: 3,
			title: I18n.system_tips ,
            btn: [ I18n.system_ok, I18n.system_cancel ]
		}, function(index){
			layer.close(index);

			$.post(base_url + "/logout", function(data, status) {
				if (data.code == "200") {
                    layer.msg( I18n.logout_success );
                    setTimeout(function(){
                        window.location.href = base_url + "/";
                    }, 500);
				} else {
					layer.open({
						title: I18n.system_tips ,
                        btn: [ I18n.system_ok ],
						content: (data.msg || I18n.logout_fail),
						icon: '2'
					});
				}
			});
		});

	});

	// slideToTop
	var slideToTop = $("<div />");
	slideToTop.html('<i class="fa fa-chevron-up"></i>');
	slideToTop.css({
		position: 'fixed',
		bottom: '20px',
		right: '25px',
		width: '40px',
		height: '40px',
		color: '#eee',
		'font-size': '',
		'line-height': '40px',
		'text-align': 'center',
		'background-color': '#222d32',
		cursor: 'pointer',
		'border-radius': '5px',
		'z-index': '99999',
		opacity: '.7',
		'display': 'none'
	});
	slideToTop.on('mouseenter', function () {
		$(this).css('opacity', '1');
	});
	slideToTop.on('mouseout', function () {
		$(this).css('opacity', '.7');
	});
	$('.wrapper').append(slideToTop);
	$(window).scroll(function () {
		if ($(window).scrollTop() >= 150) {
			if (!$(slideToTop).is(':visible')) {
				$(slideToTop).fadeIn(500);
			}
		} else {
			$(slideToTop).fadeOut(500);
		}
	});
	$(slideToTop).click(function () {
		$("html,body").animate({		// firefox ie not support body, chrome support body. but found that new version chrome not support body too.
			scrollTop: 0
		}, 100);
	});

	// left menu status v: js + server + cookie
	$('.sidebar-toggle').click(function(){
		var xxljob_adminlte_settings = $.cookie('xxljob_adminlte_settings');	// on=open，off=close
		if ('off' == xxljob_adminlte_settings) {
            xxljob_adminlte_settings = 'on';
		} else {
            xxljob_adminlte_settings = 'off';
		}
		$.cookie('xxljob_adminlte_settings', xxljob_adminlte_settings, { expires: 7 });	//$.cookie('the_cookie', '', { expires: -1 });
	});

	// left menu status v1: js + cookie
	/*
	 var xxljob_adminlte_settings = $.cookie('xxljob_adminlte_settings');
	 if (xxljob_adminlte_settings == 'off') {
	 	$('body').addClass('sidebar-collapse');
	 }
	 */


    // update pwd
    $('#updatePwd').on('click', function(){
        $('#updatePwdModal').modal({backdrop: false, keyboard: false}).modal('show');
    });
    var updatePwdModalValidate = $("#updatePwdModal .form").validate({
        errorElement : 'span',
        errorClass : 'help-block',
        focusInvalid : true,
        rules : {
            password : {
                required : true ,
                rangelength:[4,50]
            }
        },
        messages : {
            password : {
                required : '请输入密码'  ,
                rangelength : "密码长度限制为4~50"
            }
        },
        highlight : function(element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        success : function(label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },
        errorPlacement : function(error, element) {
            element.parent('div').append(error);
        },
        submitHandler : function(form) {
            $.post(base_url + "/user/updatePwd",  $("#updatePwdModal .form").serialize(), function(data, status) {
                if (data.code == 200) {
                    $('#updatePwdModal').modal('hide');

                    layer.msg( I18n.change_pwd_suc_to_logout );
                    setTimeout(function(){
                        $.post(base_url + "/logout", function(data, status) {
                            if (data.code == 200) {
                                window.location.href = base_url + "/";
                            } else {
                                layer.open({
                                    icon: '2',
                                    content: (data.msg|| I18n.logout_fail)
                                });
                            }
                        });
                    }, 500);
                } else {
                    layer.open({
                        icon: '2',
                        content: (data.msg|| I18n.change_pwd + I18n.system_fail )
                    });
                }
            });
        }
    });
    $("#updatePwdModal").on('hide.bs.modal', function () {
        $("#updatePwdModal .form")[0].reset();
        updatePwdModalValidate.resetForm();
        $("#updatePwdModal .form .form-group").removeClass("has-error");
    });
	
});
