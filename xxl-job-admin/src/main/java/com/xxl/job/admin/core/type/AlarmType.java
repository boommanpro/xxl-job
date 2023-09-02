package com.xxl.job.admin.core.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AlarmType {

    EMAIL("email", "通过邮件发送告警信息"),

    PUSH_PLUSH("push_plush", "通过PushPlus发送告警信息"),

    ;

    private final String value;

    private final String desc;
}
