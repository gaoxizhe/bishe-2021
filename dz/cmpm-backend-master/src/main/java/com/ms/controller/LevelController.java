package com.ms.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.common.ErrorCode;
import com.ms.common.Result;
import com.ms.dto.AnswerVO;
import com.ms.dto.LevelVO;
import com.ms.entity.Level;
import com.ms.entity.Record;
import com.ms.entity.TeamRank;
import com.ms.entity.User;
import com.ms.service.LevelService;
import com.ms.service.TeamRankService;
import com.ms.service.UserRankService;
import com.ms.service.UserService;
import com.ms.utils.CacheUtil;
import com.ms.utils.SessionUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ming
 */
@RestController
@RequestMapping("/api/level")
public class LevelController {
    @Resource
    private LevelService levelService;

    @Resource
    private CacheUtil cacheUtil;

    @Resource
    private UserService userService;

    @Resource
    private UserRankService userRankService;

    @Resource
    private TeamRankService teamRankService;

    @PostMapping
    public Result<?> save(@RequestBody Level level) {
        return Result.success(levelService.save(level));
    }

    @PutMapping
    public Result<?> update(@RequestBody Level level) {
        return Result.success(levelService.updateById(level));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        levelService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Level> findById(@PathVariable Long id) {
        return Result.success(levelService.getById(id));
    }

    @GetMapping
    public Result<List<Level>> findAll() {
        return Result.success(levelService.list());
    }

    @GetMapping("/page")
    public Result<IPage<Level>> findPage(@RequestParam(required = false, defaultValue = "") String title,
                                         @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                         @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return Result.success(levelService.page(new Page<>(pageNum, pageSize), Wrappers.<Level>lambdaQuery().like(Level::getTitle, title)));
    }

    @GetMapping("/all")
    public Result<List<LevelVO>> getAllLevels() {
        List<Level> list = cacheUtil.getLevelList();
        List<LevelVO> voList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            Level level = list.get(i);
            //是否在有效期
            long now = new DateTime().getTime();
            long start = level.getStartTime().getTime();
            long end = level.getEndTime().getTime();
            LevelVO levelVO = new LevelVO();
            BeanUtil.copyProperties(level, levelVO);
            if (now >= start && now <= end) {
                levelVO.setStatus(LevelVO.STATUS_OPENED);
            } else if (end < now){
                levelVO.setStatus(LevelVO.STATUS_TIME_OUT);
            }else {
                levelVO.setStatus(LevelVO.STATUS_CLOSED);
            }
            User user = SessionUtil.getUser();
            //当前用户是否还有资格挑战
            int userPermission = LevelVO.USER_PERMISSION_NORMAL;

            Record record = cacheUtil.getRecord(user.getId(), level.getId());
            //是否已提交
            if (record != null &&
                    record.getStatus() != AnswerVO.STATUS_UNPOST &&
                    record.getStatus() != AnswerVO.STATUS_CACHE_UNPOST) {
                userPermission = LevelVO.USER_PERMISSION_SUBMITTED;
                levelVO.setScore(record.getScore());
            }
            //是否已淘汰
            if (record != null && record.getPass() == 1) {
                userPermission = LevelVO.USER_PERMISSION_FAILED;
            }

            //获取关卡被淘汰的队伍
            List<TeamRank> teamRanks = teamRankService.getTeamRanksByLevel(level.getId());
            List<String> outTeams = new ArrayList<>();
            for (TeamRank teamRank:teamRanks){
                if (teamRank.getIsOut() == 1){
                    outTeams.add(teamRank.getTeamId());
                }
            }

            if (outTeams.contains(user.getTeam())){
                userPermission = LevelVO.USER_PERMISSION_FAILED;
            }

            //判断前一关是否已被淘汰,若是则直接淘汰状态
            if (i > 0 && i< list.size() && voList.get(i-1).getUserPermission() == LevelVO.USER_PERMISSION_FAILED){
                userPermission = LevelVO.USER_PERMISSION_FAILED;
            }

            levelVO.setUserPermission(userPermission);

            voList.add(levelVO);
        }
        return Result.success(voList);
    }

    @GetMapping("/curLevel")
    public Result<Level> curLevel() {
        Level current = levelService.getCurrent();
        return Result.success(current);
    }

    @PostMapping("/setCurrent")
    public Result setCurrent(@RequestParam String levelId) {
        levelService.setCurrent(levelId);
        return Result.success();
    }

    @GetMapping("/resetUserLevel")
    public Result resetUserLevel(@RequestParam String username,@RequestParam String levelId){
        User user = userService.getUserByUserName(username);
        if (user != null){
            levelService.resetUserLevel(user.getId(),levelId);
        }else {
            return Result.error(ErrorCode.USER_NOT_FOUND,"无该用户");
        }
        return Result.success();
    }

    @PostMapping("/outTeam")
    public Result outTeam(@RequestParam String levelId,@RequestParam String teamId){
        levelService.outUsersByTeam(teamId,levelId,1);
        return Result.success();
    }

    @PostMapping("/levelOver")
    public Result levelOver(@RequestParam String levelId,@RequestParam int outCount){
        levelService.levelOver(levelId,outCount);
        return Result.success();
    }

    @GetMapping("/reloadLevelListCache")
    public Result reloadLevelListCache(){
        cacheUtil.reloadLevelList();
        return Result.success();
    }



}