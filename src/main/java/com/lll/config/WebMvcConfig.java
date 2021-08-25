package com.lll.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置静态资源映射
 * 静态资源配置类
 * 参考：https://www.jianshu.com/p/cbe6465b62ec
 */
public class WebMvcConfig extends WebMvcConfigurerAdapter
{
    /**
     * {@inheritDoc}
     * <p>This implementation is empty.
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将所有/static/** 访问都映射到classpath:/static/ 目录下
        //否则会访问不到
        registry.addResourceHandler("/static/**").addResourceLocations
                ("classpath:/static/");
    }
}
