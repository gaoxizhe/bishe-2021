package com.ms.dto;

import lombok.Data;

/**
 * @author Ming
 */
@Data
public class ResultVO {
    /**
     * 得分
     */
    private Integer score;

    /**
     * 正确率
     */
    private Float correctRate;

    /**
     * 排名
     */
    private Integer rank;

    /**
     * 总用时
     */
    private Long totalUsedTime;
}
