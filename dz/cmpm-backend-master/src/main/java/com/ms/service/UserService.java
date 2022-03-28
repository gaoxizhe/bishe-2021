package com.ms.service;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.common.ErrorCode;
import com.ms.entity.*;
import com.ms.exception.CustomException;
import com.ms.mapper.UserMapper;
import com.ms.utils.CacheUtil;
import com.ms.utils.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class UserService extends ServiceImpl<UserMapper, User> {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RecordService recordService;

    @Resource
    private UserRankService userRankService;

    @Resource
    private UserLevelService userLevelService;

    public User getUserByUserName(String  username){
        LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery().eq(User::getUsername, username);
        return getOne(queryWrapper);
    }

    public void changePassword(String username,String password){
        LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery().eq(User::getUsername, username);
        User one = getOne(queryWrapper);
        if (one == null){
            throw new CustomException(ErrorCode.USER_NOT_FOUND, "用户不存在");
        }
        one.setPassword(EncryptUtil.encryptPassword(password));
        userMapper.updateById(one);
    }

    public void changePassword(String username,String password,String passwordOld){
        String encryptPass = EncryptUtil.encryptPassword(passwordOld);
        LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery().eq(User::getUsername, username).eq(User::getPassword,encryptPass);
        User one = getOne(queryWrapper);
        if (one == null){
            log.error("修改密码失败:{}-原密码错误",username);
            throw new CustomException(ErrorCode.CHANGE_PWD_ERR, "原密码错误");
        }
        one.setPassword(EncryptUtil.encryptPassword(password));
        userMapper.updateById(one);
    }

    public User login(String username,String password){
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        return login(u);
    }

    public User login(User user) {
        String encryptPass = EncryptUtil.encryptPassword(user.getPassword());
        LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()).eq(User::getPassword,encryptPass);
        return getOne(queryWrapper);
    }

    @Resource
    private CacheUtil cacheUtil;

    public User register(User user) {
        User one = getOne((Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername())));
        if (one != null) {
            throw new CustomException(ErrorCode.ALREADY_EXIST, "用户已注册");
        }
        user.setId(IdUtil.randomUUID());
        if (user.getPassword() == null) {
            user.setPassword(EncryptUtil.encryptPassword("888888"));
        }else {
            String encryptPwd = EncryptUtil.encryptPassword(user.getPassword());
            user.setPassword(encryptPwd);
        }
        save(user);

        cacheUtil.reloadTeamUsers(user.getTeam());
        User u = getOne((Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername())));
        u.setPassword("");
        return u;
    }

    public List<User> getUsersByTeam(String teamId){
        LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery().eq(User::getTeam,teamId);
        return userMapper.selectList(queryWrapper);
    }

    public void deleteByUserName(String username){
        LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery().eq(User::getUsername,username);
        User one = getOne(queryWrapper);
        if (one == null){
            throw  new CustomException(ErrorCode.USER_NOT_FOUND,"用户不存在");
        }

        //清除答题记录,清除缓存
        cacheUtil.reloadTeamUsers(one.getTeam());
        List<Level> levels = cacheUtil.getLevelList();
        for (Level level:levels){
            UserLevel userLevel = userLevelService.getUserLevel(one.getId(),level.getId());
            if (userLevel != null){
                userLevelService.removeById(userLevel.getId());
            }

            Record record = recordService.getByUserLevel(one.getId(),level.getId());
            if (record != null){
                recordService.removeById(record.getId());
            }

            UserRank userRank = userRankService.getUserRank(level.getId(),one.getId());
            if (userRank != null){
                userRankService.removeById(userRank.getId());
            }

            cacheUtil.resetUserLevel(one.getId(),level.getId());
        }

        removeById(one.getId());

    }

}
