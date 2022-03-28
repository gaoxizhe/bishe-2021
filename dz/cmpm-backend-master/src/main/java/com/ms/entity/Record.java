package com.ms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_record")
public class Record extends Model<Record> {
    /**
      * 主键
      */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
      * 用户id 
      */
    private String userId;

    /**
      * 关卡id 
      */
    private String levelId;

    /**
      * 淘汰状态， -1 未到关卡淘汰，0 通过，1 淘汰
      */
    private Integer pass;

    /**
     * 正确率
     */
    private Float correctRate;

    /**
      * 关卡得分 
      */
    private Integer score;

    /**
      * 答题详情 
      */
    private String details;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 闯关时间
     */
    private Long levelTime;

    /**
      * 剩余时间 
      */
    private Long retainTime;

    /**
     * 当前答题索引
     */
    private Integer curQuestion;

    /**
      * 提交状态 -1未提交， 0 正常提交， 1缓存未提交， 2 缓存提交 3 时间到强制提交
      */
    private Integer status;

    /**
      * 创建时间 
      */
    private Date gmtCreateTime;

    /**
      * 创建人 
      */
    private String gmtCreate;

    /**
      * 更新时间 
      */
    private Date gmtUpdateTime;

    /**
      * 更新人 
      */
    private String gmtUpdate;

}