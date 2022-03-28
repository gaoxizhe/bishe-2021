package com.ms.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ms.entity.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.mapper.TeamRankMapper;
import com.ms.utils.CacheUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class TeamRankService extends ServiceImpl<TeamRankMapper, TeamRank> {

    @Resource
    private TeamRankMapper teamRankMapper;

    @Resource
    LevelService levelService;

    public TeamRank getTeamRank(String levelId, String teamId){
        LambdaQueryWrapper<TeamRank> queryWrapper = Wrappers.<TeamRank>lambdaQuery()
                .eq(TeamRank::getLevelId,levelId)
                .eq(TeamRank::getTeamId,teamId);
        return teamRankMapper.selectOne(queryWrapper);
    }

    @Resource
    private UserRankService userRankService;

    @Resource
    private CacheUtil cacheUtil;

    @Resource
    private TeamService teamService;

    /**
     * 团队排序，（比较好性能，平时别用）
     * @param levelId
     */
    public List<TeamRank> rankAll(String levelId){
        List<TeamRank> teamRanks = getTeamRanksByLevel(levelId);

        //重新计算各团队数据
        for (TeamRank teamRank: teamRanks) {
            cacheUtil.reloadTeamUsers(teamRank.getTeamId());
            calculateTeam(teamRank,true);
        }

        //重新排序并更新至数据库
        rank(teamRanks);

        return teamRanks;
    }


    public List<TeamRank> getTeamRanksByLevel(String levelId){
        LambdaQueryWrapper queryWrapper = Wrappers.<TeamRank>lambdaQuery()
                .eq(TeamRank::getLevelId,levelId)
                .orderByAsc(TeamRank::getRankNum);
        return teamRankMapper.selectList(queryWrapper);
    }

    public TeamRank teamRankByUserLevel(String userId,String levelId){

        String teamId = cacheUtil.getUser(userId).getTeam();

        TeamRank teamRank = getTeamRank(levelId,teamId);

        if (teamRank == null){
            teamRank = new TeamRank();
            teamRank.setId(IdUtil.randomUUID());
            teamRank.setTeamId(teamId);
            teamRank.setLevelId(levelId);
            calculateTeam(teamRank,false);
        }else {
            calculateTeam(teamRank,true);
        }

        List<TeamRank> teamRanks = getTeamRanksByLevel(levelId);

        rank(teamRanks);

        return getTeamRank(levelId,teamId);
    }

    public void rank(List<TeamRank> teamRanks){
        teamRanks.sort(new Comparator<TeamRank>() {
            @Override
            public int compare(TeamRank o1, TeamRank o2) {
                Float avScore1 = o1.getAverageScore();
                Float avScore2 = o2.getAverageScore();
                Float partyRate1 = o1.getPartyRate();
                Float partyRate2 = o2.getPartyRate();

                Float avTime1 = o1.getAverageTime();
                Float avTime2 = o2.getAverageTime();

                if (avScore1.equals(avScore2)){
                    if (partyRate1.equals(partyRate2)){
                        if (avTime1.equals(avScore2)){
                            return 0;
                        }
                        return avTime1 > avTime2 ? 1 : -1;
                    }
                    return partyRate1 > partyRate2 ? -1 :1;
                }
                return avScore1 > avScore2 ? -1 : 1;
            }
        });

        for (int i = 0; i < teamRanks.size(); i++) {
            TeamRank team = teamRanks.get(i);
            team.setRankNum( i + 1);
        }

        this.updateBatchById(teamRanks);
    }



    /**
     * 计算团队各项指标
     * @param teamRank 团队记录
     * @param exist 是否已存在记录
     */
    public void calculateTeam(TeamRank teamRank,boolean exist){
        String teamId = teamRank.getTeamId();
        String levelId = teamRank.getLevelId();
        List<UserRank> userRanks = userRankService.getUserRankByTeam(levelId,teamId);
        Long total_score =  0L;
        Long total_time =  0L;

        for (UserRank userRank : userRanks) {
            total_score += userRank.getScore();
            total_time += userRank.getUsedTime();
        }

        List<User> teamUsers = cacheUtil.getTeamUsers(teamId);
        int total_user = teamUsers.size();

        //团队平均分
        Float averageScore = (float)total_score / total_user;

        //团队平均答题时间
        Float averageTime = (float) 0;
        if (userRanks.size() > 0){
            averageTime = (float) total_time / userRanks.size();
        }
        Float partyRate = (float) userRanks.size() / total_user;


        teamRank.setParticipants(userRanks.size());
        teamRank.setTotalUser(total_user);
        teamRank.setTotalScore(total_score);
        teamRank.setTotalTime(total_time);
        teamRank.setAverageScore(averageScore);
        teamRank.setAverageTime(averageTime);
        teamRank.setPartyRate(partyRate);

        if (exist){
            teamRankMapper.updateById(teamRank);
        }else {
            teamRankMapper.insert(teamRank);
        }

    }

    public List<Map<String, Object>> getTeamRankExcelData(){
        List<Map<String, Object>> list = new ArrayList<>();
        List<Level> levels = levelService.list();
        for (Level level : levels){
            list.addAll(getTeamRankExcelDataByLevel(level.getId()));
        }

        return list;
    }

    public  List<Map<String, Object>> getTeamRankExcelDataByLevel(String levelId){
        List<Map<String, Object>> list = CollUtil.newArrayList();
        LambdaQueryWrapper queryWrapper = Wrappers.<TeamRank>lambdaQuery()
                .eq(TeamRank::getLevelId,levelId);
        List<TeamRank> teamRanks = list(queryWrapper);
        for (TeamRank teamRank : teamRanks){
            Map<String,Object> row = new LinkedHashMap<>();
            Team team = teamService.getById(teamRank.getTeamId());
            row.put("队伍",team.getTeamName());
            Level level = cacheUtil.getLevelDetails(levelId);
            row.put("关卡",level.getTitle());
            row.put("总人数",teamRank.getTotalUser());
            row.put("已参与人数",teamRank.getParticipants());
            row.put("参与率",teamRank.getPartyRate());
            row.put("总分",teamRank.getTotalScore());
            row.put("平均分",teamRank.getAverageScore());
            row.put("总时间(s)",teamRank.getTotalTime());
            row.put("平均时间(s)",teamRank.getAverageTime());
            row.put("排名",teamRank.getRankNum());

            list.add(row);
        }

        return list;
    }



}