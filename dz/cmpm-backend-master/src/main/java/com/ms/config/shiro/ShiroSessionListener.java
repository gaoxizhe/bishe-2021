package com.ms.config.shiro;

import com.ms.utils.CacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import javax.annotation.Resource;

/**
 * @author Ming
 */
@Slf4j
public class ShiroSessionListener implements SessionListener {

    @Resource
    private CacheUtil cacheUtil;

    /**
     * 会话创建触发
     */
    @Override
    public void onStart(Session session) {
        cacheUtil.onlineCount(1);
        log.info("doStartSession=====>sessionId={}",session.getId());
    }

    /**
     * 退出会话时触发
     */
    @Override
    public void onStop(Session session) {
        cacheUtil.onlineCount(-1);
        log.info("doStopSession=====>sessionId={}",session.getId());
    }

    /**
     * 会话过期时触发
     */
    @Override
    public void onExpiration(Session session) {
        cacheUtil.onlineCount(-1);
        log.info("doExpirationSession=====>sessionId={}",session.getId());
    }
}
