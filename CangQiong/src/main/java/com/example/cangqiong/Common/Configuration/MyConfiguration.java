package com.example.cangqiong.Common.Configuration;

import com.example.cangqiong.Common.Filter.MyFilter;
import com.example.cangqiong.Common.Jwt.JwtUtil;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    //配置过滤器
    @Bean
    FilterRegistrationBean<MyFilter> myFilterFilterRegistrationBean(JwtUtil jwtUtil){
        FilterRegistrationBean<MyFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new MyFilter(jwtUtil));
        bean.setOrder(1);
        bean.addUrlPatterns("/*");
        bean.setName("myFilter1");
        return bean;
    }
}
