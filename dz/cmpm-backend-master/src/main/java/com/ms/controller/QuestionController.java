package com.ms.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.common.Result;
import com.ms.entity.Question;
import com.ms.entity.UserLevel;
import com.ms.service.QuestionService;
import com.ms.service.UserLevelService;
import com.ms.utils.CacheUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Resource
     private QuestionService questionService;

    @PostMapping
    public Result<?> save(@RequestBody Question question) {
        return Result.success(questionService.save(question));
    }

    @PutMapping
    public Result<?> update(@RequestBody Question question) {
        return Result.success(questionService.updateById(question));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        questionService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Question> findById(@PathVariable Long id) {
        return Result.success(questionService.getById(id));
    }

    @GetMapping
    public Result<List<Question>> findAll() {
        return Result.success(questionService.list());
    }

    @GetMapping("/page")
    public Result<IPage<Question>> findPage(@RequestParam(required = false, defaultValue = "") String content,
                                           @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                           @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return Result.success(questionService.page(new Page<>(pageNum, pageSize), Wrappers.<Question>lambdaQuery().like(Question::getContent, content)));
    }

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Resource
    private UserLevelService userLevelService;

    @Resource
    private CacheUtil cacheUtil;

    @GetMapping("/drawQuestions")
    public Result<List<Question>> drawQuestions(String userId,String levelId,@RequestParam(defaultValue = "25")int size){

        //获取该用户抽题
        UserLevel userLevel = cacheUtil.getUserLevel(userId,levelId,size);

        //获得题目详情
        JSONArray qs = JSONUtil.parseArray(userLevel.getQuestions());
        List<Question> questions = cacheUtil.getQuestionListDetails(qs.toList(String.class));


        return Result.success(questions);
    }

}