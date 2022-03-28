package com.ms.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ms.entity.UserPosition;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.mapper.UserPositionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserPositionService extends ServiceImpl<UserPositionMapper, UserPosition> {

    @Resource
    private UserPositionMapper userPositionMapper;

    public void submitPosition(UserPosition userPosition){
        LambdaQueryWrapper queryWrapper = Wrappers.<UserPosition>lambdaQuery()
                .eq(UserPosition::getUsername,userPosition.getUsername().trim());
        UserPosition one = getOne(queryWrapper);
        if (one != null){
            one.setLat(userPosition.getLat());
            one.setLng(userPosition.getLng());
            updateById(one);
        }else {
            save(userPosition);
        }
    }

}