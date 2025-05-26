package com.myexample.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyResult {
    private List<MyQuestion> myQuestions;
    private int page;
    private long total;
}
