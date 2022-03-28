package com.ms.utils;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

/**
 * @author Ming
 */
public class EncryptUtil {
    private static String key = "1234123412341234";
    /**
     * 密码加密
     * @param content 密码
     * @return 加密后密文
     */
    public static String encryptPassword(String content){
        SymmetricCrypto sm4 = SmUtil.sm4(key.getBytes());
        return sm4.encryptHex(content);
    }

    /**
     * 密码解密
     * @param content 密文
     * @return 明文密码
     */
    public static String decryptPassword(String content){
        SymmetricCrypto sm4 = SmUtil.sm4(key.getBytes());
        return sm4.decryptStr(content);
    }
}
