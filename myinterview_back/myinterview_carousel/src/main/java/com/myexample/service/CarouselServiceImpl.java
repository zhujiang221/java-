package com.myexample.service;


import com.myexample.beans.Carousel;
import com.myexample.mapper.CarouselMapper;
import com.myexample.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public int insertCarousel(Carousel carousel) {
        return carouselMapper.insertCarousel(carousel);
    }

    @Override
    public List<Carousel> selectCarousel() {
        return carouselMapper.selectCarousel();
    }
}
