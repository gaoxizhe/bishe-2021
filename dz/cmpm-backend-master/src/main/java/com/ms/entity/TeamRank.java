package com.ms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;


@Data
@TableName("t_team_rank")
public class TeamRank extends Model<TeamRank> {
    /**
      * 主键
      */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    private String levelId;

    private String teamId;

    /**
      * 已参与人数 
      */
    private Integer participants;

    private Integer totalUser;

    private Long totalScore;

    private Long totalTime;

    /**
     * 团队平均分等于团队所有答题总分除以团队人员总数
     */
    private Float averageScore;

    /**
     * 平均答题时间等于团队总时间除以参与人数
     */
    private Float averageTime;

    /**
      * 参与率等于参与人数除以团队人数
      */
    private  Float partyRate;


    private Integer rankNum;

    private int isOut;

}