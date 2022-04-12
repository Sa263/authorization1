package com.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@Slf4j
@SpringBootApplication
public class AuthApplication {

    public static void main(String[] args) {
        log.info("Starting SpringBoot Application");
        SpringApplication.run(AuthApplication.class, args);
    }

}
