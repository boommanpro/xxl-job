package com.xxl.job.admin.config;

import com.xxl.job.core.config.XxlJobExecutorConfig;
import com.xxl.job.core.executor.impl.XxlJobSimpleExecutor;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * xxl-job config
 *
 * @author xuxueli 2017-04-28
 */
@Slf4j
@Configuration
@ConditionalOnProperty(value = "xxl.job.executor.enable", havingValue = "true")
public class XxlJobConfig {


    @Bean
    public SampleXxlJob sampleXxlJob() {
        return new SampleXxlJob();
    }

    @Bean
    @ConfigurationProperties(prefix = "xxl.job")
    public XxlJobExecutorConfig xxlJobExecutorConfig() {
        return new XxlJobExecutorConfig();
    }


    @Bean
    public XxlJobSpringExecutor xxlJobExecutor(XxlJobExecutorConfig xxlJobExecutorConfig) {
        log.info(">>>>>>>>>>> xxl-job config init.");
        return new XxlJobSpringExecutor(xxlJobExecutorConfig);
    }

    /**
     * 针对多网卡、容器内部署等情况，可借助 "spring-cloud-commons" 提供的 "InetUtils" 组件灵活定制注册IP；
     *
     *      1、引入依赖：
     *          <dependency>
     *             <groupId>org.springframework.cloud</groupId>
     *             <artifactId>spring-cloud-commons</artifactId>
     *             <version>${version}</version>
     *         </dependency>
     *
     *      2、配置文件，或者容器启动变量
     *          spring.cloud.inetutils.preferred-networks: 'xxx.xxx.xxx.'
     *
     *      3、获取IP
     *          String ip_ = inetUtils.findFirstNonLoopbackHostInfo().getIpAddress();
     */


}
