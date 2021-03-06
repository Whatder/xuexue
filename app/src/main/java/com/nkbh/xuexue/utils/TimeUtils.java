package com.nkbh.xuexue.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by User on 2018/3/30.
 */

public class TimeUtils {
    public static String stamp2String(long stamp) {
        String time = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        time = simpleDateFormat.format(new Date(stamp * 1000));
        return time;
    }

    public static long string2Stamp(String timeStr) {
        long stamp = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date;
        try {
            date = simpleDateFormat.parse(timeStr);
            stamp = date.getTime() / 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stamp;
    }
}
