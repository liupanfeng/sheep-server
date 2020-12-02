package com.demo.sheep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SheepApplication {

    public static void main(String[] args) {
        SpringApplication.run(SheepApplication.class, args);
    }

}
