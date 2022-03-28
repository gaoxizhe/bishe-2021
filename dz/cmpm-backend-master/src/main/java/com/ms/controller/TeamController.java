package com.ms.controller;

import com.ms.common.Result;
import com.ms.entity.Team;
import com.ms.service.TeamService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamController {
    @Resource
     private TeamService teamService;

    @PostMapping
    public Result<?> save(@RequestBody Team team) {
        return Result.success(teamService.save(team));
    }

    @PutMapping
    public Result<?> update(@RequestBody Team team) {
        return Result.success(teamService.updateById(team));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        teamService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Team> findById(@PathVariable Long id) {
        return Result.success(teamService.getById(id));
    }

    @GetMapping
    public Result<List<Team>> findAll() {
        List<Team> list = teamService.list();
        list.sort(new Comparator<Team>() {
            @Override
            public int compare(Team o1, Team o2) {
                int id1 = Integer.parseInt(o1.getId());
                int id2 = Integer.parseInt(o2.getId());
                if (id1 == id2){
                    return 0;
                }
                return id1 < id2 ? -1 : 1;
            }
        });
        return Result.success(list);
    }

    @GetMapping("/page")
    public Result<IPage<Team>> findPage(@RequestParam(required = false, defaultValue = "") String teamName,
                                           @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                           @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return Result.success(teamService.page(new Page<>(pageNum, pageSize), Wrappers.<Team>lambdaQuery().like(Team::getTeamName, teamName)));
    }

}