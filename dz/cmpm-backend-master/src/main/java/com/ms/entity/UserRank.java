package com.ms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;


@Data
@TableName("t_user_rank")
public class UserRank extends Model<UserRank> {
    /**
      * 主键
      */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    private String levelId;

    private String userId;

    private Integer score;

    private Long usedTime;

    private String teamId;

    private Integer rankNum;

}