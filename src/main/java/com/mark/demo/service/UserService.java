package com.mark.demo.service;

import com.mark.demo.mapper.UserMapper;
import com.mark.demo.model.User;
import com.mark.demo.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    UserMapper userMapper;

    public List<User> findByToken(String token) {
        UserExample example = new UserExample();
        example.createCriteria()
                .andTokenEqualTo(token);
        return userMapper.selectByExample(example);

    }

    public void createOrUpdateUser(User user) {
        UserExample example = new UserExample();
        example.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> dbUsers = userMapper.selectByExample(example);
        if (dbUsers.size() == 0) {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        } else {
            User dbUser = dbUsers.get(0);
            User updateUser = new User();
            updateUser.setGmtCreate(System.currentTimeMillis());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());
            example.createCriteria()
                    .andAccountIdEqualTo(dbUser.getAccountId());
            userMapper.updateByExampleSelective(updateUser,example);
        }
    }
}
