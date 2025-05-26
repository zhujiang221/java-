package com.myexample.beans;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyUser {
    private int id;
    private String username;
    private String password;
    private int port=8083;

    @Override
    public String toString(){
        return JSONObject.toJSONString(this);
    }

}
