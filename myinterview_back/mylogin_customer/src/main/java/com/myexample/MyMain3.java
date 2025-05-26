package com.myexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MyMain3 {
    public static void main(String[] args){
        SpringApplication.run(MyMain3.class,args);
    }
}
