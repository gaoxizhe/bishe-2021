package com.ms.service;

import com.ms.entity.QuestionLevel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.mapper.QuestionLevelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QuestionLevelService extends ServiceImpl<QuestionLevelMapper, QuestionLevel> {

    @Resource
    private QuestionLevelMapper questionLevelMapper;

}