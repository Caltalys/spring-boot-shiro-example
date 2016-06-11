package com.yiyun.examples.service;


import com.yiyun.examples.model.User;
import com.yiyun.examples.repository.UserRepository;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public boolean verifyUser(String username, String password) {
        User res = findByUsername(username);
        if (res == null) return false;
        return res.getPassword().equals(new Md5Hash(password).toHex());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
