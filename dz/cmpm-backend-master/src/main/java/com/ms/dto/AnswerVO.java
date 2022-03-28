package com.ms.dto;

import com.ms.entity.Question;
import lombok.Data;

import java.util.List;

/**
 * @author Ming
 */
@Data
public class AnswerVO {
    public static final int STATUS_UNPOST = -1;
    public static final int STATUS_NORMAL = 0;
    public static final int STATUS_CACHE_UNPOST = 1;
    public static final int STATUS_CACHE = 2;
    public static final int STATUS_TIMEOUT = 3;
    private String userId;
    private String levelId;
    private Integer curQuestion;
    private List<QuestionVO> answers;
    /**
     * 提交状态 0 正常提交， 1缓存提交， 2 时间到强制提交
     */
    private int status;
}
