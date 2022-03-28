package com.ms.controller;

import cn.hutool.core.date.DateUtil;
import com.ms.common.ErrorCode;
import com.ms.common.Result;
import com.ms.entity.SystemParam;
import com.ms.entity.User;
import com.ms.exception.CustomException;
import com.ms.service.SystemParamService;
import com.ms.service.UserService;
import com.ms.utils.CacheUtil;
import com.ms.utils.SessionUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ming
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    @Resource
    private UserService userService;

    @Resource
    private CacheUtil cacheUtil;

    @Resource
    private SystemParamService systemParamService;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public Result<Object> login(@RequestParam String username,@RequestParam String password) {

        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            usernamePasswordToken.setRememberMe(false);
            subject.login(usernamePasswordToken);
        } catch (AuthenticationException e) {
            throw new CustomException(ErrorCode.LOGIN_ERR,"账号或密码错误");
        }



        Map<String, String> tokenMap = new HashMap<String, String>(1);
        User u = userService.getUserByUserName(username);
        SessionUtil.login(u);
        tokenMap.put("token", u.getId());
        return Result.success(tokenMap);
    }


    @PostMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.success();
    }

    @GetMapping("/sessionOut")
    public Result sessionOut() {
        return Result.error(ErrorCode.SESSION_OUT, "登录超时，请重新登录！");
    }

    @GetMapping("/error")
    public Result error() {
        return Result.error(ErrorCode.SHIRO_ERR, "shiro error");
    }


    /**
     * 注册
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result<User> register(@RequestBody User user) {
        User dbUser = userService.register(user);
        return Result.success(dbUser);
    }

    @GetMapping("/registerEnd")
    public Result<Long> registerEnd(){
        SystemParam param = systemParamService.getParamByKey("registerEnd");
        if (param != null){
           Date end =  DateUtil.parse(param.getParamValue(),"yyyy-MM-dd");
            return Result.success(end.getTime());
        }
        return Result.success(-1L);
    }

    @GetMapping("/onlineCount")
    public Result<Integer> onlineCount(){
        return Result.success(cacheUtil.getOnlineCount());
    }

}
