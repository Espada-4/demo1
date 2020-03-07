package com.mark.demo.service;

import com.mark.demo.mapper.UserMapper;
import com.mark.demo.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    UserMapper userMapper;

    public void insert(User user){
        userMapper.insert(user);
    }

    public List<User> getAll() {
        return userMapper.getAll();
    };
}
