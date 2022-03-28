package com.ms.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ms.entity.Record;
import com.ms.entity.User;
import com.ms.entity.UserLevel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.mapper.UserLevelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserLevelService extends ServiceImpl<UserLevelMapper, UserLevel> {

    @Resource
    private UserLevelMapper userLevelMapper;

    public UserLevel getUserLevel(String userId,String levelId){
        LambdaQueryWrapper<UserLevel> queryWrapper = Wrappers.<UserLevel>lambdaQuery().eq(UserLevel::getUserId, userId).eq(UserLevel::getLevelId,levelId);
        return getOne(queryWrapper);
    }

}