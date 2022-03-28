package com.ms.utils;

import cn.hutool.core.bean.BeanUtil;
import com.ms.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author Ming
 */
@Component
@Slf4j
public class GmtUtil {

    @Resource
    private HttpSession session;

    public void setGmtCreate(Object bean){
        User u = (User) session.getAttribute("user");
        if ( u != null){
            try {
                BeanUtil.setProperty(bean,"gmtCreate",u.getUsername());
                BeanUtil.setProperty(bean,"gmtCreateTime",new Date());
            } catch (Exception e) {
                log.error("设置gmt错误:",e);
            }
        }
    }

    public void setGmtUpdate(Object bean){
        User u = (User) session.getAttribute("user");
        if ( u != null){
            try {
                BeanUtil.setProperty(bean,"gmtUpdate",u.getUsername());
                BeanUtil.setProperty(bean,"gmtUpdateTime",new Date());
            } catch (Exception e) {
                log.error("设置gmt错误:",e);
            }
        }
    }
}
