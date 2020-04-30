package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonIDBuilder {
    private static String number = "0123456789";


    /**
     * 生成 person_code人员编号 R号
     *  R+12位公安机关组织机构代码+2位设备编号+8位年月日+4位顺序号
     * gajgdm : 12 位公安机构代码
     * sbbh : 2位设备编号
     * @return HM110000050100201903010001
     */
    public static String rybh(String gajgdm,String sbbh) {
        String nowStr = dateToStringNow("yyyyMMdd");
        String colNumber = String.format("R%s%s%s%s", gajgdm, sbbh, nowStr, getRandomNumber(4));
        return colNumber;
    }

    public static String dateToStringNow(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    private static int getRandom(int count) {
        return (int) Math.round(Math.random() * (count));
    }

    public static String getRandomNumber(int length) {
        StringBuffer sb = new StringBuffer();
        int len = number.length();
        for (int i = 0; i < length; i++) {
            sb.append(number.charAt(getRandom(len - 1)));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(rybh("123456789012","05"));
    }
}
