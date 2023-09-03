package com.xxl.job.admin.config;


import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class DatabaseConfig {

    /**
     * 自动识别使用的数据库类型
     * <p>
     * properties的key与数据库类型对应,为固定写法;
     * properties的value与*mapper.xml中的databaseId的值对应,可以自定义;
     * 如果*mapper.xml中没有databaseId选择则说明该sql适用所有数据库。
     */
    @Bean
    public DatabaseIdProvider getDatabaseIdProvider() {
        Properties properties = new Properties();
        properties.setProperty("Oracle", "oracle");
        properties.setProperty("MySQL", "mysql");
        properties.setProperty("DB2", "db2");
        properties.setProperty("Derby", "derby");
        properties.setProperty("H2", "h2");
        properties.setProperty("HSQL", "hsql");
        properties.setProperty("Informix", "informix");
        properties.setProperty("MS-SQL", "ms-sql");
        properties.setProperty("PostgreSQL", "postgresql");
        properties.setProperty("Sybase", "sybase");
        properties.setProperty("Hana", "hana");
        properties.setProperty("SQLite", "sqlite");

        DatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
        databaseIdProvider.setProperties(properties);
        return databaseIdProvider;
    }
}

