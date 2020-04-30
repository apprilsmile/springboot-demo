package com.example.demo.util;

import java.util.Collection;
import java.util.Map;

/**
 * 空工具..
 * Created By Night 2018/1/29
 */
public final class Nulls {

    /**
     * 是否为空..
     *
     * @param val ..
     * @return ..
     */
    public static boolean isEmpty(final String val) {
        return (val == null || val.isEmpty());
    }

    public static boolean isAllEmpty(final String... val) {
        for (String s : val) {
            if (hasValue(s)) {
                return false;
            }
        }
        return true;
    }

    public static boolean hasAnyValue(final String... val) {
        for (String s : val) {
            if (hasValue(s)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAnyEmpty(final String... val) {
        for (String s : val) {
            if (isEmpty(s)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasAllValue(final String... val) {
        for (String s : val) {
            if (isEmpty(s)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否有数据.
     *
     * @param val .
     * @return .
     */
    public static boolean hasValue(final String val) {
        return !isEmpty(val);
    }

    /**
     * 是否是空的..
     *
     * @param collection ..
     * @return ..
     */
    public static boolean isEmpty(final Collection collection) {
        return (collection == null || collection.size() == 0);
    }

    public static <T> boolean isEmpty(final T[] array) {
        return null == array || array.length == 0;
    }

    public static <T> boolean hasValue(final T[] array) {
        return !isEmpty(array);
    }

    /**
     * 是否有数据.
     *
     * @param collection .
     * @return .
     */
    public static boolean hasValue(final Collection collection) {
        return !isEmpty(collection);
    }

    /**
     * 是否是空的..
     *
     * @param map ..
     * @return ..
     */
    public static boolean isEmpty(final Map map) {
        return (map == null || map.keySet().size() == 0);
    }

    /**
     * 是否有数据..
     *
     * @param map ..
     * @return ..
     */
    public static boolean hasValue(final Map map) {
        return !isEmpty(map);
    }

    /**
     * private..
     */
    private Nulls() {
    }

    public static Object nullToSpace(Object str) {
        if (str == null) {
            return "";
        }
        return str;
    }
}
