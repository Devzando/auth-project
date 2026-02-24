package com.appdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.appdev")
@EnableScheduling
public class ApiApplication {
     static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
