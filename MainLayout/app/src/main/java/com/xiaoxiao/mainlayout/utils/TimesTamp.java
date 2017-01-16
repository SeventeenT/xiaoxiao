package com.xiaoxiao.mainlayout.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xuwenting 2016/5/4.
 */
public class TimesTamp {

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014-06-14-16-09-00"） 时间戳转换成字符窜
     *
     * @param time
     * @return
     */
    public static String timesOne(long time) {
//        Date d = new Date(time * 1000); //返回10位的 *1000
        Date d = new Date(time);
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd");
        return sdr.format(d);
    }

    public static String timesTwo(long time) {
//        Date d = new Date(time * 1000); //返回10位的 *1000
        Date d = new Date(time);
        SimpleDateFormat sdr = new SimpleDateFormat("MM-dd");
        return sdr.format(d);
    }

    public static String timesMin(long time) {
//        Date d = new Date(time * 1000); //返回10位的 *1000
        Date d = new Date(time);
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdr.format(d);
    }

    public static String timesSec(long time) {
//        Date d = new Date(time * 1000); //返回10位的 *1000
        Date d = new Date(time);
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdr.format(d);
    }

    /**
     * 将字符串转为时间戳--精确到日期
     *
     * @param time
     * @return
     */
    public static long getLongDate(String time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        return date.getTime() / 1000;
        return date.getTime();
    }

    /**
     * 获取系统当前时间
     */
    public static String getCurrentTime () {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        return sf.format(curDate);
    }

}
