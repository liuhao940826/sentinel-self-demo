package com.self.sentinelbootdemo;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SentinelBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelBootDemoApplication.class, args);
    }





}
