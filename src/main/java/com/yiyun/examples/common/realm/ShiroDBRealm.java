package com.yiyun.examples.common.realm;

import com.yiyun.examples.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroDBRealm extends AuthorizingRealm{
    @Autowired
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken)token;
        String username = upToken.getUsername();
        String password = String.valueOf(upToken.getPassword());
        SimpleAuthenticationInfo info = null;
        if (userService.verifyUser(username, password)) {
            info = new SimpleAuthenticationInfo(userService.findByUsername(username), password.toCharArray(), this.getName());
        }
        else
            throw new IncorrectCredentialsException("Incorrect Credentials");
        return info;
    }
}
