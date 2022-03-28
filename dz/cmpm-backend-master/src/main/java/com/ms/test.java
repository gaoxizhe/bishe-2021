package com.ms;

import com.ms.utils.EncryptUtil;

public class test {
    public static void main(String[] args) {
        String s = EncryptUtil.encryptPassword("123456");
        System.out.println(s);
    }
}
