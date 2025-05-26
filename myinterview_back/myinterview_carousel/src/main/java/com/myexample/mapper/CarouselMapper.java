package com.myexample.mapper;

import com.myexample.beans.Carousel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CarouselMapper {
    //增加录播数据的接口，这里调用大模型，后台实现
    @Insert("insert into carousel(pic,title) values (#{pic},#{title})")
    public int insertCarousel(Carousel carousel);
    //还需要一个前台查看
    @Select("select * from carousel limit 3")
    public List<Carousel> selectCarousel();
}
