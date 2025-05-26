package com.myexample.service;

import com.myexample.beans.Carousel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarouselService {

    public int insertCarousel(Carousel carousel);
    public List<Carousel> selectCarousel();
}
