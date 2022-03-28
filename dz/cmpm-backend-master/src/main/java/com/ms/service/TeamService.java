package com.ms.service;

import com.ms.entity.Team;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.mapper.TeamMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TeamService extends ServiceImpl<TeamMapper, Team> {

    @Resource
    private TeamMapper teamMapper;

}