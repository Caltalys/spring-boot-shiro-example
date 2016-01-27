package com.yiyun.examples.service;


import com.yiyun.examples.model.User;
import com.yiyun.examples.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public static char [] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String toHex(byte[] bytes) {
        StringBuilder res = new StringBuilder(bytes.length * 2);
        for (byte aByte : bytes) {
            res.append(hex[(aByte & 0xf0) >> 4]);
            res.append(hex[aByte & 0x0f]);
        }
        return res.toString();
    }

    public boolean verifyUser(String username, String password) {

        User res = findByUsername(username);
        if (res == null) return false;

        if (password == null || password.length() == 0) return false;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(password.getBytes("UTF-8"));
            byte[] b = md5.digest();
            password = toHex(b);
            return res.getPassword().equals(password);
        } catch (Exception e) {
            return false;
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
