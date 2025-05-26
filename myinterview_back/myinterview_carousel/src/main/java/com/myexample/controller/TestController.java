package com.myexample.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@RestController
@RequestMapping("/")
public class TestController {
    private String mystrs="[图片](https://s.coze.cn/t/P9mgHL9Eh64/)" +
                          "[图片](https://s.coze.cn/t/VCwcoNxEaYE/)" +
                          "[图片](https://s.coze.cn/t/QCZ3i3cD-OQ/)" +
                          "[图片](https://s.coze.cn/t/XhwiPQnY1cQ/)";

    @RequestMapping("/handler_message")
    public String handler_message(){
        //这里正则^开始  $结束 \d整型数
        //这里使用图片切割

        this.mystrs=this.mystrs.replace("[","").replace("]","");

        String[]  mypics=this.mystrs.split("图片");

        //if(this.mystrs.matches( "^[图片\d]"))
        System.out.println("--------------------------------------");

        System.out.println(mypics);

        //创建词汇表列表，存储图片地址
        List<String> myimages=new ArrayList<>();

        //便利所有字符数组
        for(String mypic:mypics)
        {
            String[] myones=mypic.split("");
            List<String> mylistones=Arrays.asList(myones);
            int len=mylistones.size();
             //从0开始到2，2开始用，len-1结束，中间用
            List<String> mysubone=mylistones.subList(2,len-1);
            myimages.add(String.join("",mysubone));
        }

        myimages=myimages.subList(1,myimages.size());

        return myimages.toString();
    }

}
