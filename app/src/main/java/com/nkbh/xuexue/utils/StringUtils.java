package com.nkbh.xuexue.utils;

/**
 * Created by User on 2018/3/20.
 */

public class StringUtils {
    public static boolean isNotBlank(String s) {
        return s != null && !"".equals(s.trim());
    }
}
