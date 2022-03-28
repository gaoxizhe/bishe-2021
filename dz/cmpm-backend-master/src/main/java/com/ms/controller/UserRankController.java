package com.ms.controller;

import com.ms.common.Result;
import com.ms.dto.RankVO;
import com.ms.entity.TeamRank;
import com.ms.entity.User;
import com.ms.entity.UserRank;
import com.ms.service.TeamRankService;
import com.ms.service.UserRankService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.utils.CacheUtil;
import com.ms.utils.ExcelUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/userRank")
public class UserRankController {
    @Resource
     private UserRankService userRankService;

    @Resource
    private CacheUtil cacheUtil;

    @PostMapping
    public Result<?> save(@RequestBody UserRank userRank) {
        return Result.success(userRankService.save(userRank));
    }

    @PutMapping
    public Result<?> update(@RequestBody UserRank userRank) {
        return Result.success(userRankService.updateById(userRank));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        userRankService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<UserRank> findById(@PathVariable Long id) {
        return Result.success(userRankService.getById(id));
    }

    @GetMapping
    public Result<List<UserRank>> findAll() {
        return Result.success(userRankService.list());
    }

    @GetMapping("/page")
    public Result<IPage<UserRank>> findPage(@RequestParam(required = false, defaultValue = "") String userId,
                                           @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                           @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return Result.success(userRankService.page(new Page<>(pageNum, pageSize), Wrappers.<UserRank>lambdaQuery().like(UserRank::getUserId, userId)));
    }

    @Resource
    private TeamRankService teamRankService;

    @GetMapping("/my")
    public Result<RankVO> my(String levelId,String userId){
        UserRank userRank = userRankService.getUserRank(levelId,userId);
        int userRankValue = -1;
        RankVO rankVO = new RankVO();
        //用户已答题
        if (userRank != null){
            userRankValue = userRank.getRankNum();
        }

        rankVO.setUserRank(userRankValue);

        User u = cacheUtil.getUser(userId);
        TeamRank teamRank = teamRankService.getTeamRank(levelId,u.getTeam());

        int teamRankValue = -1;
        if (teamRank != null){
            teamRankValue = teamRank.getRankNum();
        }

        rankVO.setTeamRank(teamRankValue);

        return Result.success(rankVO);
    }



    @GetMapping("/rank")
    public Result rank(String levelId){
        userRankService.rankAll(levelId);
        return Result.success();
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = userRankService.getUserRankExcelData();
        ExcelUtil.exporExcel(list,"个人排名汇总表",response);
    }

}