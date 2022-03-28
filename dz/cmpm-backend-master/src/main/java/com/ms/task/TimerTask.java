package com.ms.task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Ming
 */
//@Configuration
//@EnableScheduling
public class TimerTask {

    @Value("${task.timer}")
    private boolean taskTimer;

    /**
     * 每天凌晨3点
     */
//    @Scheduled(cron = "0 0 3 * * ?")
    @Scheduled(cron = "0 0 3 * * ?")
    public void overLevel() {
        if (taskTimer){
            //获取当前关卡

            //当前关卡是否过期

            //已过期到下一关卡
        }
    }
}
