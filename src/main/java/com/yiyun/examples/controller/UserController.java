package com.yiyun.examples.controller;

import com.yiyun.examples.model.User;
import com.yiyun.examples.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("users")
    public List<User> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users;
    }

    @RequestMapping("/users/currentUser")
    @RequiresAuthentication
    public User getCurrentUser() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        user.setPassword("");
        return user;
    }
}
