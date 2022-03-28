package com.ms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;


@Data
@TableName("t_user_level")
public class UserLevel extends Model<UserLevel> {
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
      * 问题id集合 
      */
    private String questions;



}