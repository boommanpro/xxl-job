package com.xxl.job.admin.core.alarm.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.xxl.job.admin.core.alarm.JobAlarm;
import com.xxl.job.admin.core.alarm.dto.PushPlusAlarmConfig;
import com.xxl.job.admin.core.alarm.dto.PushPlusResponseDto;
import com.xxl.job.admin.core.alarm.dto.PushPlusSendDto;
import com.xxl.job.admin.core.conf.XxlJobAdminConfig;
import com.xxl.job.admin.core.model.XxlJobGroup;
import com.xxl.job.admin.core.model.XxlJobInfo;
import com.xxl.job.admin.core.model.XxlJobLog;
import com.xxl.job.admin.core.alarm.type.AlarmTypeEnum;
import com.xxl.job.admin.core.util.I18nUtil;
import com.xxl.job.core.biz.model.ReturnT;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

/**
 * @author chen
 * @date 2022/1/4
 */
@Slf4j
@Component
@Order(1)
public class PushPlushJobAlarm implements JobAlarm {


    public PushPlushJobAlarm() {

    }

    /**
     * fail alarm
     *
     * @param jobLog
     */
    @Override
    public boolean doAlarm(XxlJobInfo info, XxlJobLog jobLog) {
        boolean alarmResult = true;

        if (info != null && AlarmTypeEnum.PUSH_PLUSH == info.getAlarmType() && !StringUtil.isNullOrEmpty(info.getAlarmConfig())) {

            // alarmContent
            String alarmContent = "Alarm Job LogId=" + jobLog.getId();
            if (jobLog.getTriggerCode() != ReturnT.SUCCESS_CODE) {
                alarmContent += "<br>TriggerMsg=<br>" + jobLog.getTriggerMsg();
            }
            if (jobLog.getHandleCode() > 0 && jobLog.getHandleCode() != ReturnT.SUCCESS_CODE) {
                alarmContent += "<br>HandleCode=" + jobLog.getHandleMsg();
            }

            XxlJobGroup group = XxlJobAdminConfig.getAdminConfig().getXxlJobGroupDao().load(Integer.valueOf(info.getJobGroup()));

            String title = I18nUtil.getString("jobconf_monitor");
            String content = MessageFormat.format(loadEmailJobAlarmTemplate(),
                    group != null ? group.getTitle() : "null",
                    info.getId(),
                    info.getJobDesc(),
                    alarmContent);

            PushPlusAlarmConfig alarmConfig = JSONUtil.toBean(info.getAlarmConfig(), PushPlusAlarmConfig.class);
            for (PushPlusAlarmConfig.Single config : alarmConfig.getConfigs()) {
                try {
                    PushPlusSendDto pushplusSendDto = new PushPlusSendDto();
                    pushplusSendDto.setTitle(title);
                    pushplusSendDto.setContent(content);
                    pushplusSendDto.setToken(config.getToken());
                    pushplusSendDto.setTopic(config.getTopic());
                    sendPushplus(pushplusSendDto);
                } catch (Exception e) {
                    log.error(">>>>>>>>>>> xxl-job, job fail alarm pushplus send error, JobLogId:{}", jobLog.getId(), e);

                    alarmResult = false;
                }
            }


        }

        return alarmResult;
    }

    /**
     * load pushplus job alarm template
     *
     * @return
     */
    private static final String loadEmailJobAlarmTemplate() {
        StringBuilder mailBody = new StringBuilder();
        mailBody.append("<h5 style=\"text-align: center;\">").append(I18nUtil.getString("jobconf_monitor_detail")).append("</h5>");
        mailBody.append("<div>")
                .append("<ul style=\"padding: 0px 0px 0px 5px;list-style:none;\">")
                .append("<li><span style=\"font-weight: bold;\">").append(I18nUtil.getString("jobinfo_field_jobgroup")).append("：</span>").append("{0}</li>\n")
                .append("<li><span style=\"font-weight: bold;\">").append(I18nUtil.getString("jobinfo_field_id")).append("：</span>").append("{1}</li>\n")
                .append("<li><span style=\"font-weight: bold;\">").append(I18nUtil.getString("jobinfo_field_jobdesc")).append("：</span>").append("{2}</li>\n")
                .append("<li><span style=\"font-weight: bold;\">").append(I18nUtil.getString("jobconf_monitor_alarm_title")).append("：</span>").append(I18nUtil.getString("jobconf_monitor_alarm_type")).append("</li>\n")
                .append("<li><span style=\"font-weight: bold;\">").append(I18nUtil.getString("jobconf_monitor_alarm_content")).append("：</span>\n").append("{3}</li>\n")
                .append("</ul>")
                .append("</div>");

        return mailBody.toString();
    }

    private static final Boolean sendPushplus(PushPlusSendDto pushplusSendDto) {
        Boolean result = false;

        String url = "https://www.pushplus.plus/send/";
        String body = JSONUtil.toJsonStr(pushplusSendDto);

        try {
            String response = HttpUtil.post(url, body);
            if (!StringUtil.isNullOrEmpty(response)) {
                PushPlusResponseDto pushplusResponseDto = JSONUtil.toBean(response, PushPlusResponseDto.class);
                if (pushplusResponseDto != null && pushplusResponseDto.getCode() == 200) {
                    result = true;
                }
            }
        } catch (Exception ex) {
            log.error("发送pushplus告警通知异常。异常内容:" + ex.getMessage());
        }

        return result;
    }
}
