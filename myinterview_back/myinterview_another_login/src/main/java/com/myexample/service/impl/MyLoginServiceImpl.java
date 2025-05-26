package com.myexample.service.impl;

import com.myexample.beans.MyUser;
import com.myexample.mapper.MyLoginMapper;
import com.myexample.service.MyLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyLoginServiceImpl implements MyLoginService {
    @Autowired
    private MyLoginMapper mylogin;
    @Override
    public MyUser login(MyUser myuser){
        return mylogin.login(myuser);
    }
}
