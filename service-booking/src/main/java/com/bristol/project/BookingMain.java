package com.bristol.project;


import com.bristol.project.interceptor.TokenRequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.bristol.project.openFeign")
public class BookingMain {

    public static void main(String[] args) {
        SpringApplication.run(BookingMain.class, args);
    }

    @Bean
    public TokenRequestInterceptor tokenRequestInterceptor(){
        return new TokenRequestInterceptor();
    }

}

