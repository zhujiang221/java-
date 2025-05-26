package com.myexample.controller;

import com.myexample.beans.MyUser;
import com.myexample.service.MyLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MyLoginController {
    @Autowired
    private MyLoginService myservice;
    @Value("${server.port}")
    private  int port;
    @RequestMapping("/login")
    public String getLogin(MyUser myuser){
        MyUser myuserone=myservice.login(myuser);
        if(myuserone==null){
            MyUser myusertwo=new MyUser();
            myusertwo.setPort(port);
            return  myusertwo.toString();
        }else{
            myuserone.setPort(port);
            return myuserone.toString();
        }
    }
}
