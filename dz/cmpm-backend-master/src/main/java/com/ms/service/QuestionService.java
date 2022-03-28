package com.ms.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ms.entity.Question;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.entity.QuestionLevel;
import com.ms.entity.Record;
import com.ms.mapper.QuestionLevelMapper;
import com.ms.mapper.QuestionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionService extends ServiceImpl<QuestionMapper, Question> {

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private QuestionLevelMapper questionLevelMapper;


    @Transactional
    public void saveQuestion(Question question,String level){
        questionMapper.insert(question);
        QuestionLevel questionLevel = new QuestionLevel();
        questionLevel.setQuestionId(question.getId());
        questionLevel.setLevelId(level);
        questionLevelMapper.insert(questionLevel);
    }

    /**
     * 抽取某关的题目
     * @param levelId 关卡id
     * @param size 抽取条数
     * @return 问题id集合
     */
    public List<String> drawQuestions(String levelId, int size){
        LambdaQueryWrapper<QuestionLevel> queryWrapper = Wrappers.<QuestionLevel>lambdaQuery().eq(QuestionLevel::getLevelId, levelId);
        List<QuestionLevel> list = questionLevelMapper.selectList(queryWrapper);
        Collections.shuffle(list);
        List<String> questions = new ArrayList<>();
        if (list.size() > 0){
            for (int i = 0; i < size; i++) {
                questions.add(list.get(i).getQuestionId());
            }
        }
        return questions;
    }
}