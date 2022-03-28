package com.ms.utils;

import java.util.Random;

/**
 * @Author newhonor
 * @Date 2021/1/22 21:45
 * @Version 1.0
 **/
public class SaltUtils {
    /**
     * 生成salt的静态方法
     * @param n
     * @return
     */
    public static String getSalt(int n){
        char[] chars = "abcdefghijklmnopqrstuvwxzABCDEFGHIJKLMNOPQRSTUVWXZ0123456789!@#$%^&*()".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char aChar = chars[new Random().nextInt(chars.length)];
            stringBuilder.append(aChar);
        }
        return stringBuilder.toString();
    }

//    public static void main(String[] args) {
//        String salt = getSalt(8);
//        System.out.println(salt);
//    }
}
