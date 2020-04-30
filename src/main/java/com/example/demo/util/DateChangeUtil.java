package com.example.demo.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateChangeUtil {
    public static Date stringToDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String timeStamp2Date(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//要转换的时间格式
        Date date;
        try {
            date = sdf.parse(sdf.format(time));
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String dateToString(Date time) {
        if(time == null){
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(time);
    }


    public static String dateToStringNow(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    /**
     * @return java.util.List<java.lang.String> [2019-01-01, 2019-01-02, 2019-01-03, 2019-01-04, 2019-01-05]
     * @Author ly
     * @Description 获取时间段内的每一天，返回list
     * @Date 2019-03-11 16:23
     * @Param [begintTime, endTime]
     **/
    public static List<String> findDaysStr(String begintTime, String endTime) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dBegin = null;
        Date dEnd = null;
        try {
            dBegin = sdf.parse(begintTime);
            dEnd = sdf.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //存放每一天日期String对象的daysStrList
        List<String> daysStrList = new ArrayList<String>();
        //放入开始的那一天日期String
        daysStrList.add(sdf.format(dBegin));

        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);

        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);

        // 判断循环此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，给定的日历字段增加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            String dayStr = sdf.format(calBegin.getTime());
            daysStrList.add(dayStr);
        }

        //打印测试数据
        //System.out.println("#####################：" + daysStrList);

        return daysStrList;
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
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    // 获取年月 字符串. 例如 : 201903
    public static String dateFormatStr(String format){
        String dateStr = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        dateStr = sdf.format(new Date());
        return dateStr;
    }
    //获取某一月的最后一天
    public static String getLastDayOfMonth(int year,int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());

        return lastDayOfMonth;
    }
    /**
     *
     * @param minDate 最小时间  2015-01
     * @param maxDate 最大时间 2015-10
     * @return 日期集合 格式为 年-月
     * @throws Exception
     */
    public static List<String> getMonthBetween(String minDate, String maxDate) throws Exception {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(minDate));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(sdf.parse(maxDate));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return result;
    }

    public static List<String> getWeekBetween(String minDate, String maxDate)  throws Exception {
        Set<String> result = new TreeSet<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-w");//格式化为年月
        Date tempDate = DateUtil.stringToDate(minDate, "yyyy-MM-dd");
        Date maxDateD = DateUtil.stringToDate(maxDate, "yyyy-MM-dd");

        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(tempDate);

        while (tempDate.before(maxDateD)) {
            int weekOfYear = c.get(Calendar.WEEK_OF_YEAR);
            if (c.get(Calendar.MONTH)>=11 && weekOfYear<=1 ){
                weekOfYear +=52;
            }
            String format = c.get(Calendar.YEAR)+"-"+weekOfYear;

            if(format.length()==6){
                format = format.replace("-","-0");
            }
            DateUtil.getBeginDayOfWeek();
            result.add(format);

            c.add(Calendar.DATE, 1);
            tempDate = c.getTime();
        }

        c.setTime(maxDateD);
        int weekOfYear = c.get(Calendar.WEEK_OF_YEAR);
        if (c.get(Calendar.MONTH)>=11 && weekOfYear<=1 ){
            weekOfYear +=52;
        }
        String format = c.get(Calendar.YEAR)+"-"+weekOfYear;
        if(format.length()==6){
            format = format.replace("-","-0");
        }
        result.add(format);
        return null;
    }

    public static List<String> findHour() {
        ArrayList<String> result = new ArrayList<String>();
        result.add("00:00");
        result.add("01:00");
        result.add("02:00");
        result.add("03:00");
        result.add("04:00");
        result.add("05:00");
        result.add("06:00");
        result.add("07:00");
        result.add("08:00");
        result.add("09:00");
        result.add("10:00");
        result.add("11:00");
        result.add("12:00");
        result.add("13:00");
        result.add("14:00");
        result.add("15:00");
        result.add("16:00");
        result.add("17:00");
        result.add("18:00");
        result.add("19:00");
        result.add("20:00");
        result.add("21:00");
        result.add("22:00");
        result.add("23:00");
        return result;
    }
}
