package com.kgr.dify.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
@ComponentScan("com.kgr.dify")
@EnableConfigurationProperties(KgrDifyProperties.class)
@ConditionalOnProperty(prefix = "kgr.dify", name = "workflow.apps")
@Slf4j
public class KgrDifyAutoConfiguration {

    public KgrDifyAutoConfiguration(KgrDifyProperties kgrDifyProperties) {
        System.out.println("kgr.dify.workflow.apps 已配置");
    }

}
