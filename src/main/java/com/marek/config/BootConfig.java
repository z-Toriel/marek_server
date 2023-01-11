package com.marek.config;

import com.marek.intercepter.TokenIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BootConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenIntercepter())
        //需要拦截的路径
        .addPathPatterns("/**")
        // 不需要拦截的路径
        .excludePathPatterns("/fans/login","/","/fans/register","/fans/**")
        ;
    }
}
