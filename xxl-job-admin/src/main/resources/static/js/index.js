/**
 * Created by xuxueli on 17/4/24.
 */
$(function () {

    $('.tabs').on('click', 'li', function () {
        // 给当前选中的li添加一个选中的样式，除了点击当前的这个样式其他的删除样式
        $(this).addClass('active').siblings().removeClass('active');
        // 第一种写法
        // $('.boxs > div').hide().eq($('.tabs li').index(this)).show();
        // 第二种写法
        // siblings:除自己以外
        // 当前指向的上级父元素的下一个子元素的索引下标出现，让其他的消失
        $(this).parent().next().children().eq($(this).index()).css('display', 'block').siblings().css('display', 'none');
    });

    setTimeout(() => {
        var ele = document.getElementsByClassName("boxs")[0]
        if (ele) {
            ele.style.height = window.innerHeight - 141 + 'px'
        }
    }, 0)
    window.addEventListener("resize", function () {
        setTimeout(() => {
            var ele = document.getElementsByClassName("boxs")[0]
            if (ele) {
                ele.style.height = window.innerHeight - 141 + 'px'
            }
        }, 0)
    });


});
