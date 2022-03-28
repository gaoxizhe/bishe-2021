package com.ms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;


@Data
@TableName("t_user_position")
public class UserPosition extends Model<UserPosition> {
    /**
      * 主键
      */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
      * 姓名 
      */
    private String username;

    /**
      * 纬度 
      */
    private  String lat;

    /**
      * 经度 
      */
    private  String lng;

}