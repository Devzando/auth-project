package com.appdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.appdev")
public class ApiApplication {
     static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
