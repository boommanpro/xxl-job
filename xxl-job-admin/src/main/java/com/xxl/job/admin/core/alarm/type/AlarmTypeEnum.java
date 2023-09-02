package com.xxl.job.admin.core.alarm.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AlarmTypeEnum {

    EMAIL("EMAIL", "邮件通知"),

    PUSH_PLUSH("PUSH_PLUSH", "PushPlush"),

    ;

    private final String value;

    private final String desc;
}
