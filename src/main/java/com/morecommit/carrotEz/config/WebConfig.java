package com.morecommit.carrotEz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    // CORS 허용
    @Bean
    public WebMvcConfigurer corsConfig() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")   // 모든 경로
                        .allowedMethods("*")           // 모든 method
                        .allowedOrigins("http://localhost:3000"); // 접근 가능 출처(리액트)
            }
        };
    }
}