package com.myexample.controller;

import com.myexample.beans.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class MyLoginCustomerController {
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/newlogin")
    public String getInfo(MyUser myuser){
        return restTemplate.getForObject("http://MyLoginService/login?username="
                +myuser.getUsername()+"&password="+myuser.getPassword(),
                String.class);
    }
}









