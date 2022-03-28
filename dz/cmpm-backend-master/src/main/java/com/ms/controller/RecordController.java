package com.ms.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.common.Result;
import com.ms.dto.AnswerVO;
import com.ms.dto.ResultVO;
import com.ms.entity.*;
import com.ms.service.RecordService;
import com.ms.service.TeamRankService;
import com.ms.service.UserRankService;
import com.ms.utils.CacheUtil;
import com.ms.utils.ExcelUtil;
import com.ms.utils.GmtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/record")
public class RecordController {
    @Resource
     private RecordService recordService;

    @Resource
    private CacheUtil cacheUtil;

    @PostMapping
    public Result<?> save(@RequestBody Record record) {
        return Result.success(recordService.save(record));
    }

    @PutMapping
    public Result<?> update(@RequestBody Record record) {
        return Result.success(recordService.updateById(record));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        recordService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Record> findById(@PathVariable Long id) {
        return Result.success(recordService.getById(id));
    }

    @GetMapping
    public Result<List<Record>> findAll() {
        return Result.success(recordService.list());
    }

    @GetMapping("/page")
    public Result<IPage<Record>> findPage(@RequestParam(required = false, defaultValue = "") String userId,
                                           @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                           @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return Result.success(recordService.page(new Page<>(pageNum, pageSize), Wrappers.<Record>lambdaQuery().like(Record::getUserId, userId)));
    }

    @GetMapping("/listByUser")
    public Result<List<Record>> listByUser(String userId){
        List<Record> records = recordService.listByUser(userId);
        return Result.success(records);
    }

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Resource
    private GmtUtil gmtUtil;

    /**
     * 开始挑战
     * @param userId
     * @param levelId
     * @return 剩余时间
     */
    @PostMapping("/start")
    public Result<Record> start(@RequestParam String userId,@RequestParam String levelId){
        Record record = recordService.getByUserLevel(userId,levelId);
        //用户还未开始挑战,创建初始化记录
        if (record == null){
            record = new Record();
            record.setUserId(userId);
            record.setLevelId(levelId);
            //从当前时间开始计时
            record.setStartTime(new Date());
            Level level = cacheUtil.getLevelDetails(levelId);
            record.setRetainTime(level.getRetainTime());
            record.setLevelTime(level.getRetainTime());
            record.setScore(0);
            record.setCurQuestion(1);
            record.setPass(-1);
            record.setStatus(-1);
            gmtUtil.setGmtCreate(record);
            recordService.save(record);
            cacheUtil.setRecord(userId,levelId,record);
        }else {
            record = cacheUtil.getRecord(userId,levelId);
        }
        return Result.success(record);
    }

    @GetMapping("/exportUserLevelByTeam")
    public void exportUserLevelRecord(HttpServletResponse response,@RequestParam String teamId,@RequestParam String levelId) throws IOException{
        List<Map<String, Object>> list = recordService.getUserLevelAnswerDetailsByTeam(teamId,levelId);
        ExcelUtil.exporExcel(list,"第"+ teamId + "队第"+ levelId +"关答题详情",response);
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = recordService.getRecordSummary();
        ExcelUtil.exporExcel(list,"答题记录汇总表",response);
    }




    @PostMapping("/cacheSubmit")
    public Result<Record> cacheSubmit(@RequestBody AnswerVO answer){
           Record record = cacheUtil.cacheSubmit(answer);
           return Result.success(record);
    }

    @PostMapping("/submitAnswer")
    public Result<ResultVO> submit(@RequestBody AnswerVO answer){
        TimeInterval timer = DateUtil.timer();
        Record record = cacheUtil.getRecord(answer.getUserId(),answer.getLevelId());
        record.setDetails(JSONUtil.parseArray(answer.getAnswers()).toJSONString(0));
        Long retainTime = cacheUtil.getRetainTime(record);
        record.setRetainTime(retainTime);
        record.setStatus(answer.getStatus());
        record.setCurQuestion(answer.getCurQuestion());
        //算分
        recordService.score(record,answer);
        gmtUtil.setGmtUpdate(record);
        recordService.updateById(record);

        //更新缓存
        cacheUtil.setRecord(answer.getUserId(),answer.getLevelId(),record);

        ResultVO result = new ResultVO();
        result.setScore(record.getScore());
        result.setCorrectRate(record.getCorrectRate());
        Long totalUsedTime = record.getLevelTime() - retainTime;
        result.setTotalUsedTime(totalUsedTime);
        //排名
        rank(record,result);

        log.info("提交执行时间{}ms", timer.intervalRestart());

        return Result.success(result);
    }

    @Resource
    private UserRankService userRankService;

    @Resource
    private TeamRankService teamRankService;

    private void rank(Record record,ResultVO result) {
      userRank(record,result);
      teamRank(record,result);
    }

    private void teamRank(Record record, ResultVO result) {
        String teamId = cacheUtil.getUser(record.getUserId()).getTeam();
        String levelId = record.getLevelId();
        TeamRank teamRank = teamRankService.getTeamRank(levelId,teamId);
        if (teamRank == null){
            teamRank = new TeamRank();
            teamRank.setId(IdUtil.randomUUID());
            teamRank.setTeamId(teamId);
            teamRank.setLevelId(levelId);
            teamRankService.calculateTeam(teamRank,false);
        }else {
            teamRankService.calculateTeam(teamRank,true);
        }

        List<TeamRank> teamRanks = teamRankService.getTeamRanksByLevel(levelId);
        teamRankService.rank(teamRanks);
        TeamRank team = teamRankService.getTeamRank(levelId,teamId);
        result.setRank(team.getRankNum());
    }

    private void userRank(Record record,ResultVO result){
        UserRank userRank = userRankService.getUserRank(record.getLevelId(),record.getUserId());
        boolean isNew = false;
        if (userRank == null){
            userRank = new UserRank();
            userRank.setId(IdUtil.randomUUID());
            isNew = true;
        }
        userRank.setLevelId(record.getLevelId());
        userRank.setUserId(record.getUserId());
        userRank.setUsedTime(result.getTotalUsedTime());
        userRank.setScore(record.getScore());
        User u = cacheUtil.getUser(record.getUserId());
        userRank.setTeamId(u.getTeam());

        if (isNew) {
            userRankService.save(userRank);
        } else {
            userRankService.updateById(userRank);
        }


        //重新排序
        userRankService.rankAll(record.getLevelId());

        userRank = userRankService.getUserRank(record.getLevelId(),record.getUserId());
        result.setRank(userRank.getRankNum());
    }


}