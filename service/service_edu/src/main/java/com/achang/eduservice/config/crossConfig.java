package com.achang.eduservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/******
 @author 阿昌
 @create 2021-02-26 21:04
 *******
 */
//@Configuration
public class crossConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET","POST","DELETE","PUT","PATCH","OPTIONS")
                .maxAge(3500);
    }
}
