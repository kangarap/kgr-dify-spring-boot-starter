package com.kgr.dify.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author kgr
 * @date 2023-08-22 16:12
 */
@Component
@ConfigurationProperties(prefix = "kgr.dify")
@Data
public class KgrDifyProperties {

    private Workflow workflow;
}