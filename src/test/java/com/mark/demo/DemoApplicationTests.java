package com.mark.demo;

import com.mark.demo.mapper.UserMapper;
import com.mark.demo.service.QuestionService;
import com.mark.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;

    @Test
    void contextLoads() {
        System.out.println(new Date(15552000));
        System.out.println(new Date(new Date().getTime() + 15552000));
        System.out.println(new Date( 15552000));
    }


}
