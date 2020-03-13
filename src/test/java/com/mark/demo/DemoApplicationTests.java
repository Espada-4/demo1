package com.mark.demo;

import com.mark.demo.mapper.UserMapper;
import com.mark.demo.service.QuestionService;
import com.mark.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        questionService.list(6,5);
    }


}
