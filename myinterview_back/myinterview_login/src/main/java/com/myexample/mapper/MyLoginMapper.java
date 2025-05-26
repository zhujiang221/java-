package com.myexample.mapper;

import com.myexample.beans.MyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface MyLoginMapper {
    @Select("select * from myuser where username=#{username} and password=#{password}")
    public MyUser login(MyUser myuser);
}
