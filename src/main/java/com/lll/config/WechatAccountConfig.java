package com.lll.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 微信公众号 相关配置
 */
@Data
//@Configuration
@Component
@ConfigurationProperties(prefix = "wechat")
//@PropertySource(value = "classpath:application.yml", encoding = "utf-8")
public class WechatAccountConfig
{
    /**
     * 公众平台id
     */
    private String mpAppId;

    /**
     * 公众平台密钥
     */
    private String mpAppSecret;

}

