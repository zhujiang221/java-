package com.myexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MyMain5 {
    public static void main(String[] args){
        SpringApplication.run(MyMain5.class,args);
    }
}
