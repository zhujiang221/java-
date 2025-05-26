package com.myexample.mapper;


import com.myexample.beans.MyQuestion;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MyQuestiopnMapper {

    // MyQuestiopnMapper.java
    @Insert("insert into myquestion (title,aoption,boption,coption,doption,answer,description) " +
            "values (#{title},#{aoption},#{boption},#{coption},#{doption},#{answer},#{description})")
    public void getInsert(MyQuestion myQuestion);


    @Select("select * from myquestion limit 10")
    public List<MyQuestion> getQuestion();
}
