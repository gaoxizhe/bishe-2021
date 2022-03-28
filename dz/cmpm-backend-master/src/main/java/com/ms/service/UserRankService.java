package com.ms.service;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.entity.Level;
import com.ms.entity.TeamRank;
import com.ms.entity.User;
import com.ms.entity.UserRank;
import com.ms.mapper.UserRankMapper;
import com.ms.utils.CacheUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserRankService extends ServiceImpl<UserRankMapper, UserRank> {

    @Resource
    private UserRankMapper userRankMapper;

    /**
     * 获取用户个人排名
     * @param levelId 关卡id
     * @param userId  用户id
     * @return
     */
    public UserRank getUserRank(String levelId, String userId){
        LambdaQueryWrapper<UserRank> queryWrapper = Wrappers.<UserRank>lambdaQuery()
                .eq(UserRank::getLevelId,levelId)
                .eq(UserRank::getUserId,userId);
         return userRankMapper.selectOne(queryWrapper);
    }

    /**
     * 获取某团队下所有人员得分
     * @param levelId
     * @param teamId
     * @return
     */
    public List<UserRank> getUserRankByTeam(String levelId, String teamId){
        LambdaQueryWrapper<UserRank> queryWrapper = Wrappers.<UserRank>lambdaQuery()
                .eq(UserRank::getLevelId,levelId)
                .eq(UserRank::getTeamId,teamId);
        return userRankMapper.selectList(queryWrapper);
    }

    public void rankAll(String levelId){
        LambdaQueryWrapper<UserRank> queryWrapper = Wrappers.<UserRank>lambdaQuery()
                .eq(UserRank::getLevelId,levelId);
        List<UserRank> userRanks = userRankMapper.selectList(queryWrapper);
        userRanks.sort(new Comparator<UserRank>() {
            @Override
            public int compare(UserRank o1, UserRank o2) {
                int score1 = o1.getScore();
                int score2 = o2.getScore();

                if (score1 == score2) {
                    //若分数相同，按答题剩余时间多的为大
                    long usedTime1 = o1.getUsedTime();
                    long usedTime2 = o2.getUsedTime();
                    if (usedTime1 == usedTime2) {
                        return 0;
                    }
                    return usedTime1 > usedTime2 ? 1 : -1;
                }

                //默认分数重大到小
                return score1 > score2 ? -1 : 1;
            }
        });

        //更新榜单
        for (int i = 0; i < userRanks.size(); i++) {
            UserRank rankItem = userRanks.get(i);
            rankItem.setRankNum(i + 1);
        }
        if (userRanks.size() > 0){
            this.updateBatchById(userRanks);
        }

    }

    @Resource
    private LevelService levelService;

    public  List<Map<String, Object>> getUserRankExcelData(){
        List<Map<String, Object>> list = new ArrayList<>();
        List<Level> levels = levelService.list();
        for (Level level : levels){
          list.addAll(getUserRankExcelDataByLevel(level.getId()));
        }

        return list;
    }

    @Resource
    private CacheUtil cacheUtil;

    @Resource
    private UserService userService;

    public  List<Map<String, Object>> getUserRankExcelDataByLevel(String levelId){
        List<Map<String, Object>> list = CollUtil.newArrayList();
        List<User> allUsers = userService.list();
        for (User user : allUsers){
            UserRank userRank = getUserRank(levelId,user.getId());
            Map<String,Object> row = new LinkedHashMap<>();
            row.put("账号",user.getUsername());
            row.put("姓名",user.getNick());
            Level level = cacheUtil.getLevelDetails(levelId);
            row.put("关卡",level.getTitle());
            if (userRank != null){
                row.put("得分",userRank.getScore());
                row.put("用时（s）",userRank.getUsedTime());
                row.put("队伍","第"+ user.getTeam() + "队");
                row.put("排名",userRank.getRankNum());
            }else {
                row.put("得分","-");
                row.put("用时（s）","-");
                row.put("队伍","第"+ user.getTeam() + "队");
                row.put("排名","-");
            }

            list.add(row);
        }
        return list;
    }

}