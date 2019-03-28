package com.self.sentinelbootdemo.config;

import com.alibaba.csp.sentinel.adapter.servlet.CommonFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: liuhao
 * @Description:
 * @Date: Create in 11:36 AM 2019/3/28
 */
@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean sentinelFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CommonFilter());
        //请根据实际情况配置要拦截的 url-pa    tern
        registration.addUrlPatterns("*.json","*.hml","*.html","/demo/*");
        registration.setName("sentinelCommonFilter");
        registration.setOrder(1);
        return registration;
    }
}