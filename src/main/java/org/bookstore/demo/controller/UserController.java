package org.bookstore.demo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bookstore.demo.entity.User;
import org.bookstore.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Log LOGGER = LogFactory.getLog(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            userService.register(user);
        } catch (Exception e) {
            LOGGER.error("Fail to register user for " + user.getUserId(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Fail to register user for " + user.getUserId() + ", please try later");
        }

        LOGGER.info("Register user for " + user.getUserId() + " successfully!");
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String userName, @RequestParam String password) {
        User user = null;
        try {
            user = userService.login(userName, password);
            if (Objects.isNull(user)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("UserName or password is not correct or user not registered!");
            }
        } catch (Exception e) {
            LOGGER.error("Fail to login for " + userName, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Fail to login for " + userName + ", please try later");
        }

        LOGGER.info("User " + userName + " login successfully!");
        return ResponseEntity.ok(user);
    }

    @PostMapping("/getUserById")
    public ResponseEntity<?> getUserById(@RequestParam Long userId) {
        User user = null;
        try {
            user = userService.viewPersonalInfoById(userId);
            if (Objects.isNull(user)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("User not exists, please check if the userId is correct!");
            }
        } catch (Exception e) {
            LOGGER.error("Fail to get user info for " + userId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Fail to get user info for " + userId + ", please try later");
        }

        LOGGER.info("Get user info for " + userId + " successfully");
        return ResponseEntity.ok(user);
    }

    @PostMapping("/getUserByName")
    public ResponseEntity<?> getUserByName(@RequestParam String userName) {
        User user = null;
        try {
            user = userService.viewPersonalInfoByName(userName);
            if (Objects.isNull(user)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("User not exists, please check if the userName is correct!");
            }
        } catch (Exception e) {
            LOGGER.error("Fail to get user info for " + userName, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Fail to get user info for " + userName + ", please try later");
        }

        LOGGER.info("Get user info for " + userName + " successfully");
        return ResponseEntity.ok(user);
    }
}
