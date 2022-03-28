package com.ms.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


@Data
@TableName("t_question_level")
public class QuestionLevel extends Model<QuestionLevel> {
    /**
      * 问题id 
      */
    private String questionId;

    /**
      * 关卡id 
      */
    private String levelId;

}