package com.ven2see.springth2see.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config implements WebMvcConfigurer{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
     WebMvcConfigurer.super.addResourceHandlers(registry);
     registry.addResourceHandler("/imagenes/**").addResourceLocations("classpath:/static/imagenes/");
    }
}
