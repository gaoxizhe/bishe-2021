package com.ms.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


@Data
@TableName("t_question")
public class Question extends Model<Question> {
    /**
      * 主键
      */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
      * 题型 1 单选，2多选 
      */
    private Integer type;

    /**
      * 题目 
      */
    private String content;

    /**
      * 答案 
      */
    private String answer;

    /**
      * 选项 JSON存储 
      */
    private String options;

    /**
     * 分值
     */
    private int score;

}