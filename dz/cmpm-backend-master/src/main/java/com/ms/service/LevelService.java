package com.ms.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ms.common.ErrorCode;
import com.ms.entity.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.exception.CustomException;
import com.ms.mapper.LevelMapper;
import com.ms.utils.CacheUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class LevelService extends ServiceImpl<LevelMapper, Level> {

    @Resource
    private LevelMapper levelMapper;

    @Resource
    private CacheUtil cacheUtil;

    @Resource
    private UserLevelService userLevelService;

    @Resource
    private RecordService recordService;

    @Resource
    private UserRankService userRankService;

    @Resource
    private TeamRankService teamRankService;

    @Resource
    private UserService userService;

    @Resource
    private LevelService levelService;

    /**
     * 重置某用户在某关的记录
     * @param userId
     * @param levelId
     */
    public void resetUserLevel(String userId,String levelId){
        cacheUtil.resetUserLevel(userId,levelId);
        //删除抽题
        UserLevel userLevel = userLevelService.getUserLevel(userId,levelId);
        if (userLevel != null){
            userLevelService.removeById(userLevel.getId());
        }

        //删除答题记录
        Record record = recordService.getByUserLevel(userId,levelId);
        if (record != null){
            recordService.removeById(record.getId());
        }

        //删除个人排名
        UserRank userRank = userRankService.getUserRank(levelId,userId);
        if (userRank != null){
            userRankService.removeById(userRank.getId());
        }

        //重新排名
        userRankService.rankAll(levelId);
        teamRankService.rankAll(levelId);
    }

    /**
     * 获取当前关卡
     * @return
     */
    public Level getCurrent(){
        LambdaQueryWrapper<Level> queryWrapper = Wrappers.<Level>lambdaQuery()
                .eq(Level::getIsCurrent,1);
        return getOne(queryWrapper);
    }

    public void setCurrent(String levelId){
        List<Level>  all = list();
        for (Level level: all){
            if (!level.getId().equals(levelId)){
                level.setIsCurrent(0);
            }else {
                level.setIsCurrent(1);
            }
        }
        updateBatchById(all);
        cacheUtil.reloadLevelList();
    }


    /**
     * 结束某关卡
     * @param levelId 关卡id
     * @param outCount 关卡淘汰人数
     */
    public void levelOver(String levelId,int outCount) {
        //结算当前关卡个人排名
        userRankService.rankAll(levelId);
        //结算团队排名
        teamRankService.rankAll(levelId);

        //只排名
        if (outCount == 0){
            return;
        }

        int levelCount = levelService.count();
        //已是最后一关，那么只是重新排序，通过导出报表，获取排名
        if (Integer.parseInt(levelId) == levelCount){
            return;
        }

        //淘汰后outCount个队伍
        List<TeamRank> teams = teamRankService.getTeamRanksByLevel(levelId);
        int rankSize = teams.size();
        if (rankSize < outCount){
            throw new CustomException(ErrorCode.BUSINESS_ERR,"淘汰数量大于总队伍数");
        }


        int outIndex = rankSize - outCount;
        //teams已是正向排序
        for (int i = 0; i < rankSize; i++) {
            String teamId = teams.get(i).getTeamId();
            int out = 1;
            //通过的队伍
            if ( i < outIndex){
               out = 0;
            }
            outUsersByTeam(teamId,levelId,out);
        }

        //设置当前关卡为下一关 这里是定制化的规则
        String currentLevelId = String.valueOf((Integer.parseInt(levelId) + 1));
        setCurrent(currentLevelId);
    }

    /**
     * 设置用户淘汰状态
     * @param teamId
     * @param out 0-通过  1-淘汰
     */
    public void outUsersByTeam(String teamId,String levelId, int out){
        //淘汰团队
        TeamRank teamRank = teamRankService.getTeamRank(levelId,teamId);
        if (teamRank != null){
            teamRank.setIsOut(out);
            teamRankService.updateById(teamRank);
        }


        //获取团队所有用户
        List<User> users = userService.getUsersByTeam(teamId);
        List<Record> records = new ArrayList<>();
        for (User user:users){
            Record record = recordService.getByUserLevel(user.getId(),levelId);
            if (record != null){
                record.setPass(out);
                records.add(record);
                cacheUtil.resetUserLevel(user.getId(),levelId);
            }

        }
        if (records.size() > 0){
            recordService.updateBatchById(records);
        }

    }

}