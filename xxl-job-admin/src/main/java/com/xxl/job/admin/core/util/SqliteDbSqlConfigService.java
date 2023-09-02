package com.xxl.job.admin.core.util;

import org.springframework.stereotype.Service;

@Service
public class SqliteDbSqlConfigService implements XxlJobSqlConfigService{
    @Override
    public String getLockSql() {
        return "select * from xxl_job_lock where lock_name = 'schedule_lock'";
    }
}
