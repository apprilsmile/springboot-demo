package com.example.demo.util;

import java.text.SimpleDateFormat;

/**
 * Created By Night 2019/9/23
 */
public class TimeFormatterUtil {

    public static ThreadLocal<SimpleDateFormat> dateTimeformatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static ThreadLocal<SimpleDateFormat> specialVersionformatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMddHHmm"));

    public static ThreadLocal<SimpleDateFormat> ymd = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    public static ThreadLocal<SimpleDateFormat> ymdhms = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMddHHmmss"));

}
