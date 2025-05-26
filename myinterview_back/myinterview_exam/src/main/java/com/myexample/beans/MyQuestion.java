package com.myexample.beans;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyQuestion {

    private int id;
    private String title;
    private String aoption;
    private String boption;
    private String coption;
    private String doption;
    private String answer;
    private String description;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
