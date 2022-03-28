package com.ms.utils;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.ms.common.ErrorCode;
import com.ms.dto.AnswerVO;
import com.ms.dto.LevelVO;
import com.ms.entity.*;
import com.ms.exception.CustomException;
import com.ms.service.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Ming
 */
@Component
public class CacheUtil {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Resource
    private UserLevelService userLevelService;

    @Resource
    private QuestionService questionService;

    @Resource
    private LevelService levelService;

    @Resource
    private RecordService recordService;

    @Resource
    private UserService userService;



    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    public User getUser(String userId){
        String key = new StringBuffer("user-").append(userId).toString();
        User u = null;
        if (redisTemplate.hasKey(key)){
            u = (User) redisTemplate.opsForValue().get(key);
        }else {
            u = userService.getById(userId);
            if ( u != null){
                u.setPassword("");
                redisTemplate.opsForValue().set(key,u);
            }
        }
        return u;
    }

    public void reloadTeamUsers(String teamId){
        String key = getTeamUserKey(teamId);
        redisTemplate.delete(key);
        getTeamUsers(teamId);
    }

    private String getTeamUserKey(String teamId){
        return new StringBuffer("team-user-").append(teamId).toString();
    }

    public List<User> getTeamUsers(String teamId){
        String key = getTeamUserKey(teamId);
        List<User> users = new ArrayList<>();
        if (redisTemplate.hasKey(key)){
            users = (List<User>) redisTemplate.opsForValue().get(key);
        }else {
            users = userService.getUsersByTeam(teamId);
            redisTemplate.opsForValue().set(key,users);
        }
        return users;
    }

    /**
     * 删除用户在当前关卡的缓存
     * @param userId
     * @param levelId
     */
    public void resetUserLevel(String userId,String levelId){
        String permissionKey = getUserLevelPermissionKey(userId,levelId);
        redisTemplate.delete(permissionKey);
        String recordKey = getRecordKey(userId,levelId);
        redisTemplate.delete(recordKey);
        String userLevelKey = getUserLevelKey(userId,levelId);
        redisTemplate.delete(userLevelKey);

    }

    private String getUserLevelPermissionKey(String userId,String levelId){
        return new StringBuffer("level-permission-")
                .append(userId)
                .append("-").append(levelId).toString();
    }


    private String getLevelAllKey(){
        return new StringBuffer("level-all").toString();
    }

    public void reloadLevelList(){
        String key = getLevelAllKey();
        redisTemplate.delete(key);
        getLevelList();
    }

    /**
     * 获取所有关卡信息
     * @return
     */
    public List<Level> getLevelList(){
        String key = getLevelAllKey();
        List<Level> list = null;
        if (redisTemplate.hasKey(key)){
            list = (List<Level>) redisTemplate.opsForValue().get(key);
        }else {
            list = levelService.list();
            redisTemplate.opsForValue().set(key,list);
        }
        return list;
    }


    /**
     * 缓存提交
     * @param answer
     */
    public Record cacheSubmit(AnswerVO answer){
        String userId = answer.getUserId();
        String levelId = answer.getLevelId();
        Record record  = getRecord(userId,levelId);
        record.setDetails(JSONUtil.parseArray(answer.getAnswers()).toJSONString(0));
        Long retainTime = getRetainTime(record);
        record.setRetainTime(retainTime);
        record.setCurQuestion(answer.getCurQuestion());
        record.setStatus(AnswerVO.STATUS_CACHE_UNPOST);
        record.setScore(0);
        setRecord(userId,levelId,record);
        return record;
    }

    public Long getRetainTime(Record record){
        Date startTime = record.getStartTime();
        Date now = new Date();
        Long betweenTime = DateUtil.between(startTime,now, DateUnit.SECOND,false);
        Long levelTime = record.getLevelTime();

        return levelTime - betweenTime > 0 ? levelTime - betweenTime : 0;
    }


    /**
     * 获取缓存的记录
     * @param userId
     * @param levelId
     * @return
     */
    public Record getRecord(String userId,String levelId){
        String key = getRecordKey(userId,levelId);
        Record record = null;
        if (redisTemplate.hasKey(key)){
             record = (Record) redisTemplate.opsForValue().get(key);
        }else {
            record = recordService.getByUserLevel(userId,levelId);
        }
        if (record != null){
            record.setRetainTime(getRetainTime(record));
            setRecord(userId,levelId,record);
        }

        return record;

    }

    private String getRecordKey(String userId,String levelId){
        return  new StringBuffer("record-")
                .append(levelId)
                .append("-")
                .append(userId).toString();
    }


    /**
     * 设置缓存的记录
     * @param userId
     * @param levelId
     * @param record
     */
    public void setRecord(String userId,String levelId,Record record){
        String key = getRecordKey(userId,levelId);
        redisTemplate.opsForValue().set(key,record);
    }


    private String getUserLevelKey(String userId,String levelId){
        return new StringBuffer("level-").append(levelId).append("-").append(userId).toString();
    }
    /**
     * 获取用户某关的抽题
     * @param userId
     * @param levelId
     * @param size
     * @return
     */
    public  UserLevel getUserLevel(String userId, String levelId, int size){
       String userLevelKey = getUserLevelKey(userId,levelId);
        UserLevel userLevel = null;
        if (redisTemplate.hasKey(userLevelKey)){
            userLevel = (UserLevel) redisTemplate.opsForValue().get(userLevelKey);
        }else {
            userLevel = userLevelService.getUserLevel(userId,levelId);
            if (userLevel == null){
                List<String> questionIds = questionService.drawQuestions(levelId,size);
                userLevel = new UserLevel();
                userLevel.setId(IdUtil.randomUUID());
                userLevel.setUserId(userId);
                userLevel.setLevelId(levelId);
                JSONArray questions = JSONUtil.createArray();
                questions.addAll(questionIds);
                userLevel.setQuestions(questions.toJSONString(0));
                userLevelService.save(userLevel);
            }
            //向缓存写入该用户的抽题
            redisTemplate.opsForValue().set(userLevelKey,userLevel);
        }
        return userLevel;
    }

    /**
     * 获取关卡详情
     * @param levelId
     * @return
     */
    public  Level getLevelDetails(String levelId){
        String key = new StringBuffer("level-").append(levelId).toString();
        Level level = null;
        if (redisTemplate.hasKey(key)){
            level = (Level) redisTemplate.opsForValue().get(key);
        }else {
            level = levelService.getById(levelId);
            redisTemplate.opsForValue().set(key,level);
        }
        return level;
    }

    /**
     * 获取问题列表详情
     * @param questionIds
     * @return
     */
    public List<Question> getQuestionListDetails(List<String> questionIds){
        List<Question> questions = new ArrayList<>();
        for (String questionId : questionIds) {
            Question question = getQuestionDetails(questionId);
            question.setAnswer("");
            questions.add(question);
        }
        return questions;
    }

    /**
     * 获取问题详情
     * @param questionId
     * @return
     */
    public Question getQuestionDetails(String questionId){
        Question question = null;
        String key = new StringBuffer("question-").append(questionId).toString();
        //从缓存获取问题详情
        if (redisTemplate.hasKey(key)) {
            question = (Question) redisTemplate.opsForValue().get(key);
        } else {
            question = questionService.getById(questionId);
            redisTemplate.opsForValue().set(key, question);
        }
        return question;
    }


    private String onlineCountKey = "online-count";
    /**
     *
     * @param type 1-增加，-1减少
     */
    public void onlineCount(int type){
        if (!redisTemplate.hasKey(onlineCountKey)){
            redisTemplate.opsForValue().set(onlineCountKey,0);
        }
        if (type == -1){
            redisTemplate.opsForValue().decrement(onlineCountKey);
        }else {
            redisTemplate.opsForValue().increment(onlineCountKey);
        }
    }

    public int getOnlineCount(){
        int count = 0;
        if (!redisTemplate.hasKey(onlineCountKey)){
            redisTemplate.opsForValue().set(onlineCountKey,count);
        }else {
            count = (int) redisTemplate.opsForValue().get(onlineCountKey);
        }
        return count;
    }



}
