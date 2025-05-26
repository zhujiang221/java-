package com.myexample.service;


import com.myexample.beans.MyQuestion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MyQusetionService {
    public void getInsert(MyQuestion question);
    public List<MyQuestion> getQuestion(int page);
}
