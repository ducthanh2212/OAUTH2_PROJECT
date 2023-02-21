package com.oauth2_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com")
public class OAuth2ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuth2ProjectApplication.class, args);
    }

}
