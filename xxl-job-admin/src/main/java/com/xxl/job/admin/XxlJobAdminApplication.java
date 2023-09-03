package com.xxl.job.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.ApplicationListener;

/**
 * @author xuxueli 2018-10-28 00:38:13
 */
@Slf4j
@SpringBootApplication
public class XxlJobAdminApplication implements ApplicationListener<WebServerInitializedEvent> {


    public static void main(String[] args) {
        SpringApplication.run(XxlJobAdminApplication.class, args);
    }


    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        WebServer webServer = event.getWebServer();
        WebServerApplicationContext applicationContext = event.getApplicationContext();
        String contextPath = applicationContext.getEnvironment().getProperty("server.servlet.context-path");
        log.info("xxl-job admin start success, server is http://127.0.0.1:{}{}", webServer.getPort(), contextPath);
    }

}
