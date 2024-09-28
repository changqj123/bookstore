package org.bookstore.demo.service;

import org.bookstore.demo.entity.User;

public interface UserService {
    void register(User user);
    User login(String userName, String password);
    User viewPersonalInfoById(Long userId);
    User viewPersonalInfoByName(String userName);
}
