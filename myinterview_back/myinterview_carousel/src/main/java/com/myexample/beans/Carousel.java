package com.myexample.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carousel {

    private int id;

    private String pic;

    private String title;

}
