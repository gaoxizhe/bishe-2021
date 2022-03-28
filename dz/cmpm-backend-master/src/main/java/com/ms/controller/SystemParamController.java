package com.ms.controller;

import cn.hutool.core.date.DateUtil;
import com.ms.common.Result;
import com.ms.entity.SystemParam;
import com.ms.service.SystemParamService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.utils.GmtUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/systemParam")
public class SystemParamController {
    @Resource
     private SystemParamService systemParamService;


    @Resource
    private GmtUtil gmtUtil;

    @PostMapping
    public Result<?> save(@RequestBody SystemParam systemParam) {
        gmtUtil.setGmtCreate(systemParam);
        return Result.success(systemParamService.save(systemParam));
    }

    @PutMapping
    public Result<?> update(@RequestBody SystemParam systemParam) {
        gmtUtil.setGmtUpdate(systemParam);
        return Result.success(systemParamService.updateById(systemParam));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        systemParamService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<SystemParam> findById(@PathVariable Long id) {
        return Result.success(systemParamService.getById(id));
    }

    @GetMapping
    public Result<List<SystemParam>> findAll() {
        return Result.success(systemParamService.list());
    }

    @GetMapping("/page")
    public Result<IPage<SystemParam>> findPage(@RequestParam(required = false, defaultValue = "") String paramKey,
                                           @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                           @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return Result.success(systemParamService.page(new Page<>(pageNum, pageSize), Wrappers.<SystemParam>lambdaQuery().like(SystemParam::getParamKey, paramKey)));
    }

}