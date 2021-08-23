package com.lll.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 构造授权url
 */

@Configuration  //指示一个类声明一个或多个@Bean方法，并且可以由Spring容器处理，以便在运行时为这些bean生成BeanDefinition和服务请求
@Component    //标记为spring容器中的一个Bean
public class WechatMpConfig
{
    @Autowired
    private WechatAccountConfig accountConfig;


    @Bean
    public WxMpService wxMpService()
    {
        WxMpService wxMpService = new WxMpServiceImpl();
        // void setWxMpConfigStorage(WxMpConfigStorage var1);
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage()
    {
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId(accountConfig.getMpAppId());
        wxMpConfigStorage.setSecret(accountConfig.getMpAppSecret());
        return wxMpConfigStorage;
    }



}
