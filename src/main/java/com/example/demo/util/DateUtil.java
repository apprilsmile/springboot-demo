/*
 * DateUtil.java Copyright Bejing Passion Tech Co.,Ltd. All Rights Reserved.
 */
package com.example.demo.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具
 *
 * @author yangting
 *
 */
public class DateUtil {
    /**
     * 按照pattern转换时间为字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String date2Text(Date date, String pattern) {
        String result = org.apache.commons.lang3.StringUtils.EMPTY;

        if (date == null)
            return result;
        if (StringUtils.isBlank(pattern))
            pattern = "yyyy-MM-dd HH:mm:ss";
        DateFormat format = new SimpleDateFormat(pattern);
        result = format.format(date);
        return result;
    }


    public static String AddDay(String strDate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        /**
         * 加一天
         */
        try {
            Date dd = df.parse(strDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dd);
            calendar.add(Calendar.DAY_OF_MONTH, 1);//加一天
            //System.out.println("增加一天之后：" + df.format(calendar.getTime()));
            return df.format(calendar.getTime());
        }   catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 按照pattern转换字符串为日期
     *
     * @param text
     * @param pattern
     * @return
     */
    public static Date text2date(String text, String pattern) {
        Date result = null;
        if (text == null)
            return result;
        if (StringUtils.isBlank(pattern))
            pattern = "yyyy-MM-dd HH:mm:ss";

        DateFormat format = new SimpleDateFormat(pattern);
        try {
            result = format.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        //result = java.sql.Date.valueOf(text); 
        return result;
    }

    /**
     * 获取0点时间
     *
     * @param date
     * @return
     */
    public static Date getDate0(Date date) {
        String zero = " 00:00:00";
        String text = date2Text(date, "yyyy-MM-dd");
        return text2date(text + zero, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取24点时间（即第二天0点）
     *
     * @param date
     * @return
     */
    public static Date getDate24(Date date) {
        Date zeroTime = getDate0(date);
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTime(zeroTime);
        c.add(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    /**
     * 获取上一天时间
     *
     * @param date
     * @return
     */
    public static Date getLastDay(Date date) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.getTime();
    }

    /**
     * 获取日期所在年度开始时间（本年度一月一日零时）
     *
     * @param date 空则取当前时间
     * @return
     */
    public static Date getYearBegin(Date date) {
        Date d = (Date) date.clone();
        if (d == null)
            d = new Date();
        String begin = date2Text(d, "yyyy");
        Date beginTime = text2date(begin + "-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        return beginTime;
    }

    /**
     * 获取日期所在年度结束时间（下一年度一月一日零时）
     *
     * @param date
     * @return
     */
    public static Date getYearEnd(Date date) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTime(getYearBegin(date));
        c.add(Calendar.YEAR, 1);
        return c.getTime();
    }

    /**
     *  
     * 验证签名 
     *
     * @param signature  验证签名的数据
     * @param timestamp  参数
     * @param nonce      参数
     * @return  boolean
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        //TOKEN//与token 比较
        String[] arr = new String[]{"TG20190306", timestamp, nonce};
        // 将token、timestamp、nonce三个参数进行字典排序  
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        content = null;
        // 将sha1加密后的字符串可与signature对比  
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    /**
     *  
     *    * 将字节数组转换为十六进制字符串 
     *    *  
     *    * @param byteArray 
     *    * @return  String
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     *  
     * 将字节转换为十六进制字符串 
     *  
     *
     * @param mByte 
     * @return  String
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }

    // 获取上周的开始时间
    public static String getLastWeekStartDay() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, 2);
        c.add(Calendar.DATE, -7);
        SimpleDateFormat startSdf = new SimpleDateFormat("yyyy-MM-dd");
        return startSdf.format(c.getTime());
    }

    // 获取上周的结束时间
    public static String getLastWeekEndDay() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, 2);
        c.add(Calendar.DATE, -1);
        SimpleDateFormat endSdf = new SimpleDateFormat("yyyy-MM-dd");
        return endSdf.format(c.getTime());
    }


    public static Date getBeginDayOfLastWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek - 7);
        return getDayStartTime(cal.getTime());
    }

    // 获取上周的结束时间
    public static Date getEndDayOfLastWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfLastWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }


    // 获取本周的开始时间
    public static Date getBeginDayOfWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayStartTime(cal.getTime());
    }

    public static String thisWeekStart() {
        SimpleDateFormat startSdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = startSdf.format(getBeginDayOfWeek());
        return time;
    }

    // 获取本周的结束时间
    public static Date getEndDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }


    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d)
            calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    public static String thisWeekEnd() {
        SimpleDateFormat startSdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = startSdf.format(getEndDayOfWeek());
        return time;
    }

    // 获取某个日期的结束时间
    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d)
            calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }

    public static Date stringToDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String dateToStringNow(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 获取当前月第一天
     * @return
     */
    public static String getCurMonthFirstDay(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String first = format.format(c.getTime());
        return first;
    }

    /**
     * 获取当前月最后一天
     * @return
     */
    public static String getCurMonthLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyy-MM-dd");
        return simpleFormate.format(calendar.getTime());
    }

}