package com.myapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
	registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
	registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
	registry.addResourceHandler("/templates/**").addResourceLocations("classpath:/templates/");
    }
}