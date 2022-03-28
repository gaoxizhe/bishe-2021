package com.ms.utils;

import com.ms.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

/**
 * @author Ming
 */
public class SessionUtil {

    public static User getUser(){
        Subject subject = SecurityUtils.getSubject();
       return (User) subject.getSession().getAttribute("user");
    }

    public static void login(User user){
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute("user",user);
    }
}
