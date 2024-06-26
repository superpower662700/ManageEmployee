package com.example.springrestapi;

import com.example.springrestapi.filter.logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringrestapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringrestapiApplication.class, args);
    }
    @Bean
    public FilterRegistrationBean<logger> loggingFilterRegistration() {
        FilterRegistrationBean<logger> registration = new FilterRegistrationBean<>();
        registration.setFilter(new logger());
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        return registration;
    }
}
