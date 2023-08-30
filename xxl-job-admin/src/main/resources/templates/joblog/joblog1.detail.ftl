<!DOCTYPE html>
<html>
<head>
    <#import "../common/common.macro.ftl" as netCommon>
    <@netCommon.commonStyle />
    <title>${I18n.admin_name}</title>
</head>
<body class="hold-transition skin-blue layout-top-nav">

<div class="wrapper">

    <header class="main-header">
        <nav class="navbar navbar-static-top">
            <div class="container"  style="width: 100%;">
                <#-- icon -->
                <div class="navbar-header">
                    <a class="navbar-brand"><b>${I18n.joblog_rolling_log}</b> Console</a>
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
                        <i class="fa fa-bars"></i>
                    </button>
                </div>

                <#-- left nav -->
                <div class="collapse navbar-collapse pull-left" id="navbar-collapse">
                    <ul class="nav navbar-nav">
                        <#--<li class="active" ><a href="javascript:;">任务：<span class="sr-only">(current)</span></a></li>-->
                    </ul>
                </div>

                <#-- right nav -->
                <div class="navbar-custom-menu">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="javascript:window.location.reload();" >
                                <i class="fa fa-fw fa-refresh" ></i>
                                ${I18n.joblog_rolling_log_refresh}
                            </a>
                        </li>
                        <li>
                            <a href="javascript:void(0);" >
                                <div class="flex" style="width: 45px;">
                                    <div class="joblog_detail_bg joblog_detail_bg_white" id="detail_bg_white"></div>
                                    <div class="joblog_detail_bg joblog_detail_bg_black" id="detail_bg_black"></div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="javascript:void(0);" >
                                <span class="ion-close-circled joblog_detail_close" ></span>
                            </a>
                        </li>
                    </ul>
                </div>

            </div>
        </nav>
    </header>

    <div class="content-wrapper" >
        <section class="content">
            <pre style="font-size:12px;position:relative;" class="detail_bg detail_bg_default">
                <div id="logConsole"></div>
                <li class="fa fa-refresh fa-spin" style="font-size: 20px;float: left;" id="logConsoleRunning" ></li>
            </pre>
        </section>
    </div>

</div>

<@netCommon.commonScript />
<script>
    // 参数
    var triggerCode = '${triggerCode}';
    var handleCode = '${handleCode}';
    var executorAddress = '${executorAddress!}';
    var triggerTime = '${triggerTime?c}';
    var logId = '${logId}';
</script>
<script src="${request.contextPath}/static/js/joblog.detail.1.js"></script>

</body>
</html>