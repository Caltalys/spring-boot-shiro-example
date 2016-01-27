package com.yiyun.examples.controller;

import com.yiyun.examples.vo.Auth;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;

@Controller
@RequestMapping("/api/security")
public class SecurityController {

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    @ResponseBody
    public ResponseEntity login(@RequestBody Auth auth) {

        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(auth.getUsername(), auth.getPassword());
        token.setRememberMe(false);
        try {
            currentUser.login(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("");
        }
        return ResponseEntity.ok("");
    }

    @RequestMapping("/logout")
    @ResponseBody
    public ResponseEntity logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/views/login.html")).body("");
    }
}
