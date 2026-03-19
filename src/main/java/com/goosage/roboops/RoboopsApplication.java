package com.goosage.roboops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RoboopsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoboopsApplication.class, args);
    }
}