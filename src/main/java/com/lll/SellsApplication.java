package com.lll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class SellsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SellsApplication.class, args);
    }

}
