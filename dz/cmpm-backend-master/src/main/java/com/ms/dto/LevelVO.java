package com.ms.dto;

import com.ms.entity.Level;
import lombok.Data;

/**
 * @author Ming
 */
@Data
public class LevelVO extends Level {

    public static int STATUS_CLOSED = 0;
    public static int STATUS_OPENED = 1;
    public static int STATUS_TIME_OUT = 2;

    public static int USER_PERMISSION_NORMAL = 0;
    public static int USER_PERMISSION_SUBMITTED = 1;
    public static int USER_PERMISSION_FAILED = 2;
    /**
     * 状态  0未开放；1开放；
     */
    private int status;

    /**
     * 用户权限 0 可挑战，1 已提交，2 已淘汰
     */
    private int userPermission;

    /**
     * 当前得分
     */
    private int score = 0;
}
