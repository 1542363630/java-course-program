package com.example.courseprogram.config;

import com.example.courseprogram.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/user/**","/photo/**","/wall/**","/check/**")
                .excludePathPatterns("/user/login","/user/register","/user/sdu/login","/user/sdu/register");

    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//        //配置资源映射：设置虚拟路径，访问绝对路径下资源：访问 http://localhost:8080/view/images/xxx.png访问D:/view/Photos/下的资源
//        registry.addResourceHandler("/view/images/**") //虚拟路径
//                .addResourceLocations("file:" + "D:/view/Photos/"); //绝对路径
//    }
}
