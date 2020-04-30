package com.example.demo.util;

import java.util.Random;

public class RandomUtil {
    /**
     * 生成N位随机数
     *
     */
    public static String getRandomCode(int n){
        String str="0123456789";
        StringBuilder sb=new StringBuilder(n);
        for(int i=0;i<n;i++)
        {
            char ch=str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        return sb.toString();
    }

    public static String getRandomString(int length) {
        String string = "abcdefghijklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer sb = new StringBuffer();
        int len = string.length();
        for (int i = 0; i < length; i++) {
            sb.append(string.charAt(getRandom(len - 1)));
        }
        return sb.toString();
    }

    private static int getRandom(int count) {
        return (int) Math.round(Math.random() * (count));
    }
}
