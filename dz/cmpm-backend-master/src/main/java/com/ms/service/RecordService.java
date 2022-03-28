package com.ms.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.dto.AnswerVO;
import com.ms.dto.QuestionVO;
import com.ms.entity.Level;
import com.ms.entity.Question;
import com.ms.entity.Record;
import com.ms.entity.User;
import com.ms.mapper.RecordMapper;
import com.ms.utils.CacheUtil;
import com.mysql.cj.result.Row;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecordService extends ServiceImpl<RecordMapper, Record> {

    @Resource
    private RecordMapper recordMapper;

    @Resource
    private CacheUtil cacheUtil;

    /**
     * 通过用户id查询
     * @param userId
     * @return
     */
    public List<Record> listByUser(String userId){
        LambdaQueryWrapper<Record> queryWrapper = Wrappers.<Record>lambdaQuery().eq(Record::getUserId, userId);

        return recordMapper.selectList(queryWrapper);
    }

    public List<Map<String, Object>> getRecordSummary(){
        List<Map<String, Object>> list = CollUtil.newArrayList();

        List<Record> all = list();
        for (Record record : all) {
            Map<String, Object> row = new LinkedHashMap<>();
            User u = cacheUtil.getUser(record.getUserId());
            row.put("账号", u.getUsername());
            row.put("姓名",u.getNick());

            Level level = cacheUtil.getLevelDetails(record.getLevelId());
            row.put("关卡",level.getTitle());

            int pass = record.getPass();
            String passStr = "关卡未结束";
            if (pass == 0){
                passStr = "通过";
            }else if (pass == 1){
                passStr = "淘汰";
            }
            row.put("淘汰情况", passStr);

            row.put("正确率", record.getCorrectRate());
            row.put("得分",record.getScore());
            row.put("开始答题时间", DateUtil.formatDate(record.getStartTime()));

            long totalUsedTime = record.getLevelTime() - record.getRetainTime();
            row.put("答题时间(s)",totalUsedTime);

            list.add(row);
        }

        return list;
    }

    @Resource
    private UserService userService;

    public List<Map<String, Object>> getUserLevelAnswerDetailsByTeam(String teamId,String levelId){
         List<User> users = userService.getUsersByTeam(teamId);
        List<Map<String, Object>> list = CollUtil.newArrayList();
        for (User user : users){
            list.addAll(getUserLevelAnswerDetails(user,levelId));
        }
        return list;
    }

    public  List<Map<String, Object>> getUserLevelAnswerDetails(User user,String levelId){
        List<Map<String, Object>> list = CollUtil.newArrayList();
        Record record = getByUserLevel(user.getId(),levelId);

        if (record != null && record.getStatus() != AnswerVO.STATUS_UNPOST){
            JSONArray array = JSONUtil.parseArray(record.getDetails());
            for (int i = 0; i < array.size(); i++) {
                Map<String, Object> row = new LinkedHashMap<>();
                row.put("账号",user.getUsername());
                row.put("姓名",user.getNick());
                JSON json = JSONUtil.parse(array.get(i));
                Question question =  json.getByPath("question",Question.class);
                row.put("题目",question.getContent());
                row.put("分数",question.getScore());
                row.put("答案",json.getByPath("answer"));
                row.put("正确答案",json.getByPath("rightAnswer"));
                String right = "错误";
                if ((Boolean)json.getByPath("right")){
                    right = "正确";
                }
                row.put("是否正确",right);

                JSONArray options = JSONUtil.parseArray(question.getOptions());
                for (int j = 0; j < options.size(); j++) {
                    JSON json1 = JSONUtil.parse(options.get(j));
                    String key = (String) json1.getByPath("key");
                    String value = (String) json1.getByPath("value");
                    row.put(key,value);
                }
                list.add(row);
            }

        }else {
            Map<String, Object> row1 = new LinkedHashMap<>();
            row1.put("账号",user.getUsername());
            row1.put("姓名",user.getNick());
            list.add(row1);
        }

        return list;

    }

    /**
     *  计算得分和正确率
     * @param record
     * @param answerVo
     */
    public void score(Record record,AnswerVO answerVo){
        List<QuestionVO> answers = answerVo.getAnswers();
        int totalScore = 0;
        int correctCount = 0;
        JSONArray jsonArray = JSONUtil.createArray();
        for (QuestionVO answer : answers){
            Question question = cacheUtil.getQuestionDetails(answer.getId());
            char[] rightAns = question.getAnswer().trim().toUpperCase().toCharArray();
           //按字母排序
            Arrays.sort(rightAns);
            char[] ans = answer.getAnswer().trim().toUpperCase().toCharArray();
            Arrays.sort(ans);
            int score = question.getScore();
            boolean right = false;
            if (Arrays.equals(rightAns, ans)){
                totalScore += score;
                correctCount ++;
                right = true;
            }

            //写入解析详情
            JSON json = JSONUtil.createObj();
            json.putByPath("question",question);
            json.putByPath("right",right);
            json.putByPath("answer",ans);
            json.putByPath("rightAnswer",rightAns);
            jsonArray.add(json);
        }

        DecimalFormat df = new DecimalFormat("0.00");
        Float correctRate = (float) correctCount / answers.size();

        record.setScore(totalScore);
        record.setCorrectRate(correctRate);
        record.setDetails(jsonArray.toJSONString(0));

    }

    /**
     * 获取用户某关的记录
     * @param userId
     * @param levelId
     * @return
     */
    public Record getByUserLevel(String userId,String levelId){
        LambdaQueryWrapper<Record> queryWrapper = Wrappers.<Record>lambdaQuery()
                .eq(Record::getUserId, userId)
                .eq(Record::getLevelId,levelId);

        return getOne(queryWrapper);
    }

}