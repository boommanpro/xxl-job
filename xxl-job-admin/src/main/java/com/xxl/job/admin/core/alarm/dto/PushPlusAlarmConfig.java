package com.xxl.job.admin.core.alarm.dto;

import lombok.Data;

import java.util.List;

@Data
public class PushPlusAlarmConfig {
    private String desc;

    private List<Single> configs;

    @Data
    public static class Single {
        private String token;

        private String topic;
    }
}
