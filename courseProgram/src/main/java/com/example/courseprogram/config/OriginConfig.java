package com.example.courseprogram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class OriginConfig {
    @Bean
    public CorsFilter filter() {
        CorsConfiguration fig = new CorsConfiguration();  //过滤器对象
        fig.addAllowedHeader("token");
        fig.addAllowedHeader("Content-Type");
        fig.addAllowedMethod("GET");
        fig.addAllowedMethod("POST");
        fig.addAllowedOrigin("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",fig);  //封装
        return new CorsFilter(source);
    }
}
