package org.bookstore.demo.service.impl;

import org.bookstore.demo.entity.User;
import org.bookstore.demo.mapper.UserMapper;
import org.bookstore.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(User user) {
        userMapper.addUser(user);
    }

    @Override
    public User login(String userName, String password) {
        return userMapper.selectUser(userName, password);
    }

    @Override
    public User viewPersonalInfoById(Long userId) {
        return userMapper.selectUserById(userId);
    }

    @Override
    public User viewPersonalInfoByName(String userName) {
        return userMapper.selectUserByName(userName);
    }

}
