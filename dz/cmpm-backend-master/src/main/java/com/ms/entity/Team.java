package com.ms.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


@Data
@TableName("t_team")
public class Team extends Model<Team> {
    /**
      * 主键
      */
    @TableId(value = "id")
    private String id;

    /**
     * 团队名称
     */
    private String teamName;

    /**
     * 团队机构
     */
    private String teamOrg;

}