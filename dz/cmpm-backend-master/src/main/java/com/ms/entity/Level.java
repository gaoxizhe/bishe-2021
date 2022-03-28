package com.ms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_level")
public class Level extends Model<Level> {
    /**
      * 主键
      */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
      * 闯关标题 
      */
    private String title;

    /**
      * 闯关内容 
      */
    private String content;

    /**
     * 封面
     */
    private String cover;

    /**
      * 开始时间 
      */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;

    /**
      * 结束时间 
      */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;

    /**
     * 挑战时间
     */
    private Long retainTime;


    /**
     * 是否为当前关卡 不是-0  是-1
     */
    private int isCurrent;
}