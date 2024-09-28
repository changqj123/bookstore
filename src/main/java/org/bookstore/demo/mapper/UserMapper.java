package org.bookstore.demo.mapper;


import org.apache.ibatis.annotations.Param;
import org.bookstore.demo.entity.User;

public interface UserMapper {
    void addUser(User user);
    User selectUserById(Long userId);
    User selectUserByName(String userName);
    User selectUser(@Param("userName") String userName, @Param("password") String password);
}
