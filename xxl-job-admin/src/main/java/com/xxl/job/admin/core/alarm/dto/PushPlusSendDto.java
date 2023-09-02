package com.xxl.job.admin.core.alarm.dto;

import lombok.Data;

@Data
public class PushPlusSendDto {

    private String token;

    private String title;

    private String content;

    private String topic;
}
