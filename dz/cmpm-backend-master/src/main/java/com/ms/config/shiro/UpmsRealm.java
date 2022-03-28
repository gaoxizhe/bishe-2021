package com.ms.config.shiro;

import com.ms.common.ErrorCode;
import com.ms.entity.User;
import com.ms.exception.CustomException;
import com.ms.service.UserService;
import com.ms.utils.CacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Ming
 */
@Slf4j
public class UpmsRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //写入用户权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = (String) principalCollection.getPrimaryPrincipal();
        User user = userService.getUserByUserName(username);
        //目前没做细致的权限管理，随便写入一个
        authorizationInfo.addStringPermission("all");
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String  username = (String) authenticationToken.getPrincipal();
        String  password = null;
        User user = null;
        if ( authenticationToken instanceof UsernamePasswordToken){
            password = new String((char[])authenticationToken.getCredentials());
            user =userService.login(username,password);
            if (user == null) {
                log.error("{}-账户或密码错误",username);
            }

        }else {
            log.error("{}-用户登录失败-{}",username,"不支持的认证方式");
        }
        log.info("{}-用户登录成功",user.getUsername());
        return new SimpleAuthenticationInfo(username,password,user.getNick());
    }
}
