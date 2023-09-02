package com.xxl.job.admin;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;
import org.sqlite.SQLiteConfig;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author xuxueli 2018-10-28 00:38:13
 */
@SpringBootApplication
public class XxlJobAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(XxlJobAdminApplication.class, args);
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	HikariDataSource dataSource(DataSourceProperties properties) {

		HikariDataSource dataSource = createDataSource(properties, HikariDataSource.class);
		if (StringUtils.hasText(properties.getName())) {
			dataSource.setPoolName(properties.getName());
		}
		Properties dataSourceProperties = new SQLiteConfig().toProperties();
		dataSourceProperties.setProperty(SQLiteConfig.Pragma.DATE_STRING_FORMAT.pragmaName, "yyyy-MM-dd HH:mm:ss");
		dataSource.setDataSourceProperties(dataSourceProperties);
		return dataSource;
	}

	protected static <T> T createDataSource(DataSourceProperties properties, Class<? extends DataSource> type) {
		return (T) properties.initializeDataSourceBuilder().type(type).build();
	}

}
