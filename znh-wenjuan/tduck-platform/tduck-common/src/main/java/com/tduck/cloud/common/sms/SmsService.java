package com.tduck.cloud.common.sms;

/**
 * @author : smalljop
 * @description : 短信服务
 * @create : 2020-12-15 10:30
 **/
public abstract class SmsService {

    public SmsPlatformProperties properties;

    /**
     * 发送短信
     *
     * @param phoneNumber    手机号
     * @param templateId     模板Id
     * @param templateParams 短信模板參數
     * @return
     */
    public abstract boolean sendSms(String phoneNumber, String templateId, String... templateParams);

    /**
     * 发送验证短信
     *
     * @param phoneNumber    手机号
     * @param templateParams 短信模板參數
     * @return
     */
    public abstract boolean sendValidateSms(String phoneNumber, String... templateParams);

    /**
     * 发送找回密码验证码
     *
     * @param phoneNumber
     * @param templateParams
     * @return
     */
    public abstract boolean sendRetrievePwdValidateSms(String phoneNumber, String... templateParams);

}
