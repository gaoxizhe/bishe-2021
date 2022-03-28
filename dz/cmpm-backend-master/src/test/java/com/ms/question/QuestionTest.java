package com.ms.question;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.ms.entity.Question;
import com.ms.entity.QuestionLevel;
import com.ms.entity.User;
import com.ms.service.QuestionLevelService;
import com.ms.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.Console;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ming
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class QuestionTest {

    @Resource
    QuestionService questionService;

    @Resource
    QuestionLevelService questionLevelService;
    private JSON op;

    @Test
    public void initQuestions() {
        List<String> errorList = new ArrayList<>();
        String file = "question.xlsx";
        //第一关
        for (int i = 0; i < 4; i++) {
            ExcelReader reader = ExcelUtil.getReader(file, i);
            List<List<Object>> readerList = reader.read(1);
            for (int j = 0; j < readerList.size(); j++) {
                List<Object> row = readerList.get(j);
                String type = (String) row.get(0);
                String content = (String) row.get(1);
                String answer = (String) row.get(2);

                String A = null, B = null, C = null, D = null;
                try {
                    A = (String) row.get(3);
                    B = (String) row.get(4);
                    C = (String) row.get(5);
                    D = (String) row.get(6);
                } catch (Exception e) {
                    if (!e.getMessage().equals("Index: 6, Size: 6")) {
                        log.error("读取第{}sheet第{}行选项错误:{}", i, (j + 1), e.getMessage());
                        errorList.add("读取第" + i + "sheet第" + (j + 1) + "行选项错误:" + e.getMessage());
                    }

//                    e.printStackTrace();
                }

//                log.info("-->{}",row);
                Question question = new Question();
                question.setId(IdUtil.randomUUID());
                question.setContent(content.trim());
                question.setAnswer(answer.trim());

                try {
                    if (type.trim().equals("单选")) {
                        question.setType(1);
                    } else {
                        question.setType(2);
                    }
                } catch (Exception e) {

                    log.error("读取第{}sheet第{}行题目类型错误:{}", i, (j + 3), e.getMessage());
                    errorList.add("读取第" + i + "sheet第" + (j + 3) + "行题目类型错误:" + e.getMessage());


//                    e.printStackTrace();
                }

                JSONArray option = JSONUtil.createArray();
                if (!StrUtil.isBlankIfStr(A)) {
                    JSON op = JSONUtil.createObj();
                    op.putByPath("key", "A");
                    op.putByPath("value", A);
                    option.add(op);
                }
                if (!StrUtil.isBlankIfStr(B)) {
                    JSON op = JSONUtil.createObj();
                    op.putByPath("key", "B");
                    op.putByPath("value", B);
                    option.add(op);
                }
                if (!StrUtil.isBlankIfStr(C)) {
                    JSON op = JSONUtil.createObj();
                    op.putByPath("key", "C");
                    op.putByPath("value", C);
                    option.add(op);
                }
                if (!StrUtil.isBlankIfStr(D)) {
                    JSON op = JSONUtil.createObj();
                    op.putByPath("key", "D");
                    op.putByPath("value", D);
                    option.add(op);
                }
                question.setOptions(option.toJSONString(0));
                question.setScore(4);

//                log.info("question:=====>{}", question);
                String level = "" + i;
                if (i > 3) {
                    level = "4";
                }
                    questionService.saveQuestion(question, level);
            }

        }
        log.error("错误信息：{}", errorList);
    }
}
