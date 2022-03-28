package com.ms.controller;

import com.ms.common.Result;
import com.ms.entity.UserLevel;
import com.ms.service.UserLevelService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/userLevel")
public class UserLevelController {
    @Resource
     private UserLevelService userLevelService;

    @PostMapping
    public Result<?> save(@RequestBody UserLevel userLevel) {
        return Result.success(userLevelService.save(userLevel));
    }

    @PutMapping
    public Result<?> update(@RequestBody UserLevel userLevel) {
        return Result.success(userLevelService.updateById(userLevel));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        userLevelService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<UserLevel> findById(@PathVariable Long id) {
        return Result.success(userLevelService.getById(id));
    }

    @GetMapping
    public Result<List<UserLevel>> findAll() {
        return Result.success(userLevelService.list());
    }

    @GetMapping("/page")
    public Result<IPage<UserLevel>> findPage(@RequestParam(required = false, defaultValue = "") String userId,
                                           @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                           @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return Result.success(userLevelService.page(new Page<>(pageNum, pageSize), Wrappers.<UserLevel>lambdaQuery().like(UserLevel::getUserId, userId)));
    }

}