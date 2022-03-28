package com.ms.config.shiro;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Ming
 */
@Data
@Component
@ConfigurationProperties(prefix = "shiro")
public class ShiroProperties {
    private String anonUrl;
    private String unauthorizedUrl;
    private String successUrl;
    private String loginUrl;
    private long timeOut = 18000000;
    private boolean deleteInvalidSessions = true;
}
