package com.ms.controller;

import com.ms.common.Result;
import com.ms.entity.TeamRank;
import com.ms.service.TeamRankService;
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
@RequestMapping("/api/teamRank")
public class TeamRankController {
    @Resource
     private TeamRankService teamRankService;

    @PostMapping
    public Result<?> save(@RequestBody TeamRank teamRank) {
        return Result.success(teamRankService.save(teamRank));
    }

    @PutMapping
    public Result<?> update(@RequestBody TeamRank teamRank) {
        return Result.success(teamRankService.updateById(teamRank));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        teamRankService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<TeamRank> findById(@PathVariable Long id) {
        return Result.success(teamRankService.getById(id));
    }

    @GetMapping
    public Result<List<TeamRank>> findAll() {
        return Result.success(teamRankService.list());
    }

    @GetMapping("/page")
    public Result<IPage<TeamRank>> findPage(@RequestParam(required = false, defaultValue = "") String teamId,
                                           @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                           @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return Result.success(teamRankService.page(new Page<>(pageNum, pageSize), Wrappers.<TeamRank>lambdaQuery().like(TeamRank::getTeamId, teamId)));
    }

    @Resource
    private CacheUtil cacheUtil;

    @GetMapping("/rank")
    public Result rank(@RequestParam String levelId){
        teamRankService.rankAll(levelId);
        return Result.success();
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = teamRankService.getTeamRankExcelData();
        ExcelUtil.exporExcel(list,"团队排名汇总",response);
    }

}