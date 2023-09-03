package com.xxl.job.admin.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.sqlite.SQLiteConfig;

import javax.sql.DataSource;
import java.util.Properties;

@Slf4j
@Configuration
public class DataSourceTypeConfiguration {


    @Bean
    @ConditionalOnProperty(value = "mybatis.configuration.database-id", havingValue = "sqlite")
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariDataSource dataSource(DataSourceProperties properties) {
        HikariDataSource dataSource = createDataSource(properties, HikariDataSource.class);
        if (StringUtils.hasText(properties.getName())) {
            dataSource.setPoolName(properties.getName());
        }
        Properties dataSourceProperties = new SQLiteConfig().toProperties();
        dataSourceProperties.setProperty(SQLiteConfig.Pragma.DATE_STRING_FORMAT.pragmaName, "yyyy-MM-dd HH:mm:ss");
        dataSource.setDataSourceProperties(dataSourceProperties);
        log.info("load datasource for sqlite type");
        return dataSource;
    }

    protected static <T> T createDataSource(DataSourceProperties properties, Class<? extends DataSource> type) {
        return (T) properties.initializeDataSourceBuilder().type(type).build();
    }
}
