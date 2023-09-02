CREATE TABLE IF NOT EXISTS `xxl_job_info` (
                                              `id` INTEGER PRIMARY KEY AUTOINCREMENT,
                                              `job_group` INTEGER NOT NULL,
                                              `job_desc` TEXT NOT NULL,
                                              `add_time` DATETIME DEFAULT NULL,
                                              `update_time` DATETIME DEFAULT NULL,
                                              `author` VARCHAR(64) DEFAULT NULL,
    `alarm_type` VARCHAR(64) DEFAULT NULL,
    `alarm_config` TEXT,
    `schedule_type` VARCHAR(50) NOT NULL DEFAULT 'NONE',
    `schedule_conf` VARCHAR(128) DEFAULT NULL,
    `misfire_strategy` VARCHAR(50) NOT NULL DEFAULT 'DO_NOTHING',
    `executor_route_strategy` VARCHAR(50) DEFAULT NULL,
    `executor_handler` VARCHAR(255) DEFAULT NULL,
    `executor_param` VARCHAR(512) DEFAULT NULL,
    `executor_block_strategy` VARCHAR(50) DEFAULT NULL,
    `executor_timeout` INTEGER NOT NULL DEFAULT '0',
    `executor_fail_retry_count` INTEGER NOT NULL DEFAULT '0',
    `glue_type` VARCHAR(50) NOT NULL,
    `glue_source` TEXT,
    `glue_remark` VARCHAR(128) DEFAULT NULL,
    `glue_updatetime` DATETIME DEFAULT NULL,
    `child_jobid` VARCHAR(255) DEFAULT NULL,
    `trigger_status` INTEGER NOT NULL DEFAULT '0',
    `trigger_last_time` INTEGER NOT NULL DEFAULT '0',
    `trigger_next_time` INTEGER NOT NULL DEFAULT '0'
    );

CREATE TABLE IF NOT EXISTS `xxl_job_log` (
                                             `id` INTEGER PRIMARY KEY AUTOINCREMENT,
                                             `job_group` INTEGER NOT NULL,
                                             `job_id` INTEGER NOT NULL,
                                             `executor_address` VARCHAR(255) DEFAULT NULL,
    `executor_handler` VARCHAR(255) DEFAULT NULL,
    `executor_param` VARCHAR(512) DEFAULT NULL,
    `executor_sharding_param` VARCHAR(20) DEFAULT NULL,
    `executor_fail_retry_count` INTEGER NOT NULL DEFAULT '0',
    `trigger_time` DATETIME DEFAULT NULL,
    `trigger_code` INTEGER NOT NULL,
    `trigger_msg` TEXT,
    `handle_time` DATETIME DEFAULT NULL,
    `handle_code` INTEGER NOT NULL,
    `handle_msg` TEXT,
    `alarm_status` INTEGER NOT NULL DEFAULT '0'
    );

CREATE TABLE IF NOT EXISTS `xxl_job_log_report` (
                                                    `id` INTEGER PRIMARY KEY AUTOINCREMENT,
                                                    `trigger_day` DATETIME DEFAULT NULL,
                                                    `running_count` INTEGER NOT NULL DEFAULT '0',
                                                    `suc_count` INTEGER NOT NULL DEFAULT '0',
                                                    `fail_count` INTEGER NOT NULL DEFAULT '0',
                                                    `update_time` DATETIME DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `xxl_job_logglue` (
                                                 `id` INTEGER PRIMARY KEY AUTOINCREMENT,
                                                 `job_id` INTEGER NOT NULL,
                                                 `glue_type` VARCHAR(50) DEFAULT NULL,
    `glue_source` TEXT,
    `glue_remark` VARCHAR(128) NOT NULL,
    `add_time` DATETIME DEFAULT NULL,
    `update_time` DATETIME DEFAULT NULL
    );

CREATE TABLE IF NOT EXISTS `xxl_job_registry` (
                                                  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
                                                  `registry_group` VARCHAR(50) NOT NULL,
    `registry_key` VARCHAR(255) NOT NULL,
    `registry_value` VARCHAR(255) NOT NULL,
    `update_time` DATETIME DEFAULT NULL
    );

CREATE TABLE IF NOT EXISTS `xxl_job_group` (
                                               `id` INTEGER PRIMARY KEY AUTOINCREMENT,
                                               `app_name` VARCHAR(64) NOT NULL,
    `title` VARCHAR(12) NOT NULL,
    `address_type` INTEGER NOT NULL DEFAULT '0',
    `address_list` TEXT,
    `update_time` DATETIME DEFAULT NULL
    );

CREATE TABLE IF NOT EXISTS `xxl_job_user` (
                                              `id` INTEGER PRIMARY KEY AUTOINCREMENT,
                                              `username` VARCHAR(50) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
    `role` INTEGER NOT NULL,
    `permission` VARCHAR(255) DEFAULT NULL
    );

CREATE TABLE IF NOT EXISTS `xxl_job_lock` (
    `lock_name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`lock_name`)
    );

INSERT INTO `xxl_job_group`(`id`, `app_name`, `title`, `address_type`, `address_list`, `update_time`) VALUES (1, 'xxl-job-executor-sample', '示例执行器', 0, NULL, '2018-11-03 22:21:31' );
INSERT INTO `xxl_job_info`(`id`, `job_group`, `job_desc`, `add_time`, `update_time`, `author`, `alarm_type`, `schedule_type`, `schedule_conf`, `misfire_strategy`, `executor_route_strategy`, `executor_handler`, `executor_param`, `executor_block_strategy`, `executor_timeout`, `executor_fail_retry_count`, `glue_type`, `glue_source`, `glue_remark`, `glue_updatetime`, `child_jobid`) VALUES (1, 1, '测试任务1', '2018-11-03 22:21:31', '2018-11-03 22:21:31', 'XXL', '', 'CRON', '0 0 0 * * ? *', 'DO_NOTHING', 'FIRST', 'demoJobHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2018-11-03 22:21:31', '');
INSERT INTO `xxl_job_user`(`id`, `username`, `password`, `role`, `permission`) VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 1, NULL);
INSERT INTO `xxl_job_lock` ( `lock_name`) VALUES ( 'schedule_lock');
