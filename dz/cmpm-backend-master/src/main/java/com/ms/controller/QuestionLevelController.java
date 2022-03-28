package com.ms.controller;

import com.ms.common.Result;
import com.ms.entity.QuestionLevel;
import com.ms.service.QuestionLevelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/questionLevel")
public class QuestionLevelController {

    @Resource
    private QuestionLevelService questionLevelService;

    @PostMapping
    public Result<?> save(@RequestBody QuestionLevel questionLevel) {
        return Result.success(questionLevelService.save(questionLevel));
    }

    @PutMapping
    public Result<?> update(@RequestBody QuestionLevel questionLevel) {
        return Result.success(questionLevelService.updateById(questionLevel));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        questionLevelService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<QuestionLevel> findById(@PathVariable Long id) {
        return Result.success(questionLevelService.getById(id));
    }

    @GetMapping
    public Result<List<QuestionLevel>> findAll() {
        return Result.success(questionLevelService.list());
    }



}