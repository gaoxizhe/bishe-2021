package com.ms.controller;

import com.ms.common.Result;
import com.ms.entity.UserPosition;
import com.ms.service.UserPositionService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/userPosition")
public class UserPositionController {
    @Resource
     private UserPositionService userPositionService;

    @PostMapping("/submitPosition")
    public Result submitPosition(@RequestBody UserPosition userPosition){
        userPositionService.submitPosition(userPosition);
        return Result.success();
    }


    @PostMapping
    public Result<?> save(@RequestBody UserPosition userPosition) {
        return Result.success(userPositionService.save(userPosition));
    }

    @PutMapping
    public Result<?> update(@RequestBody UserPosition userPosition) {
        return Result.success(userPositionService.updateById(userPosition));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        userPositionService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<UserPosition> findById(@PathVariable Long id) {
        return Result.success(userPositionService.getById(id));
    }

    @GetMapping
    public Result<List<UserPosition>> findAll() {
        return Result.success(userPositionService.list());
    }

    @GetMapping("/page")
    public Result<IPage<UserPosition>> findPage(@RequestParam(required = false, defaultValue = "") String username,
                                           @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                           @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return Result.success(userPositionService.page(new Page<>(pageNum, pageSize), Wrappers.<UserPosition>lambdaQuery().like(UserPosition::getUsername, username)));
    }

}