package com.ms.service;

import cn.hutool.db.DaoTemplate;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ms.entity.SystemParam;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.mapper.SystemParamMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SystemParamService extends ServiceImpl<SystemParamMapper, SystemParam> {

    @Resource
    private SystemParamMapper systemParamMapper;

    public SystemParam getParamByKey(String paramKey){
        LambdaQueryWrapper<SystemParam> queryWrapper = Wrappers.<SystemParam>lambdaQuery()
                .eq(SystemParam::getParamKey,paramKey.trim());
        return getOne(queryWrapper);
    }


}