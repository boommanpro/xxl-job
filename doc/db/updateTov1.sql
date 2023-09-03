-- 改表语句
ALTER TABLE xxl_job_info
    CHANGE COLUMN alarm_email alarm_type varchar(64) DEFAULT 'EMAIL' COMMENT '报警类型',
    ADD COLUMN alarm_config text COMMENT '报警配置';
