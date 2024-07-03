package com.smart.prod.common.utils;

import cn.hutool.core.date.DateUnit;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public final static SimpleDateFormat FORMAT_ONE = new SimpleDateFormat("yyyy-MM-dd");
    public final static SimpleDateFormat FORMAT_TWO = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public final static SimpleDateFormat FORMAT_THERE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public final static SimpleDateFormat FORMAT_FOUR = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public final static SimpleDateFormat FORMAT_FIVE = new SimpleDateFormat("yyyy-MM");
    public final static SimpleDateFormat MMDDHHMM = new SimpleDateFormat("MM/dd HH:mm");
    public final static Calendar calendar = Calendar.getInstance();

    /**
     * 获取年份
     *
     * @return
     */
    public static String year(Date date) {
        return new SimpleDateFormat("yyyy").format(date);
    }

    /**
     * 获取月份
     *
     * @return
     */
    public static String month(Date date) {
        return new SimpleDateFormat("MM").format(date);
    }

    public static Date parseNoYear(Date date) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        String s = sdf1.format(date);
        return sdf1.parse(s);
    }

    /**
     * String类型转Date类型
     * yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static Date getDate(String date, SimpleDateFormat simpleDateFormat) {
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * String类型转Date类型
     * yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static Date getDate1(String date) {
        try {
            return FORMAT_ONE.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * String类型转Date类型
     * yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static Date getDate3(String date) {
        try {
            return FORMAT_THERE.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * String类型转化为Date类型
     * yyyy/MM/dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static Date getDate2(String date) {
        try {
            return FORMAT_TWO.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Date转String类型
     * yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String getString1(Date date) {
        try {
            return FORMAT_ONE.format(date);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * Date转String类型
     * yyyy-MM-dd  HH:mm
     *
     * @param date
     * @return
     */
    public static String getString4(Date date) {
        try {
            return FORMAT_FOUR.format(date);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * Date转String类型
     * yyyy-MM
     */
    public static String getString5(Date date) {
        try {
            return FORMAT_FIVE.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 12/01 17:32
     * @param date
     * @return
     */
    public static String getMMDDMM(Date date) {
        try {
            return MMDDHHMM.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Date类型转String类型
     * yyyy/MM/dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String getString2(Date date) {
        try {
            return FORMAT_TWO.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Date类型转String类型
     * yyyy/MM/dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String getString3(Date date) {
        try {
            return FORMAT_THERE.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * date类型添加天数
     *
     * @param date
     * @param number
     * @return
     */
    public static Date addDate(Date date, int number) {
        calendar.setTime(date==null?new Date():date);
        calendar.add(Calendar.DATE, number);
        return calendar.getTime();
    }

    /**
     * date类型减少分钟数
     * @param date
     * @param number
     * @return
     */
    public static Date subMinute(Date date, int number) {
        calendar.setTime(date==null?new Date():date);
        calendar.add(Calendar.MINUTE, -number);
        return calendar.getTime();
    }

    /**
     * date类型减少小时数
     * @param date
     * @param number
     * @return
     */
    public static Date subHour(Date date, int number) {
        calendar.setTime(date==null?new Date():date);
        calendar.add(Calendar.HOUR, -number);
        return calendar.getTime();
    }

    /**
     * date类型添加分钟数
     *
     * @param date
     * @param number
     * @return
     */
    public static Date addMinute(Date date, int number) {
        calendar.setTime(date==null?new Date():date);
        calendar.add(Calendar.MINUTE, number);
        return calendar.getTime();
    }

    /**
     * date类型添加小时数
     *
     * @param date
     * @param number
     * @return
     */
    public static Date addHour(Date date, int number) {
        calendar.setTime(date==null?new Date():date);
        calendar.add(Calendar.HOUR, number);
        return calendar.getTime();
    }

    /**
     * date类型添加月数
     *
     * @param date
     * @param args
     * @return
     */
    public static Date addMonth(Date date, int... args) {
        calendar.setTime(date==null?new Date():date);
        for (int arg : args) {
            calendar.add(Calendar.MONTH, arg);
        }
        return calendar.getTime();
    }

    /**
     * date类型添加年数
     *
     * @param date
     * @param args
     * @return
     */
    public static Date addYear(Date date, int... args) {
        calendar.setTime(date==null?new Date():date);
        for (int arg : args) {
            calendar.add(Calendar.YEAR, arg);
        }
        return calendar.getTime();
    }

    /**
     * double类型转日期
     *
     * @param d
     * @return
     */
    public static Date doubleToDate(Double d) {
        Date oDate = new Date();
        @SuppressWarnings("deprecation")
        long localOffset = oDate.getTimezoneOffset() * 60000; //系统时区偏移 1900/1/1 到 1970/1/1 的 25569 天
        oDate.setTime((long) ((d - 25569) * 24 * 3600 * 1000 + localOffset));

        return oDate;
    }

    /**
     * 日期类型转double
     *
     * @param date
     * @return
     */
    public static double dateToDouble(Date date) {
        @SuppressWarnings("deprecation")
        long localOffset = date.getTimezoneOffset() * 60000;
        double dd = (double) (date.getTime() - localOffset) / 24 / 3600 / 1000 + 25569.0000000;
        DecimalFormat df = new DecimalFormat("#.0000000000");//先默认保留10位小数

        return Double.valueOf(df.format(dd));
    }

    public static String getYear(Object o){
        if(o instanceof Date){
            return DateUtil.getString1((Date) o).substring(0, 4);
        }

        if(o instanceof String){
            try {
                int i = Integer.parseInt(((String) o).substring(0, 4));
                if(i > 0 && i < 5000){
                    return ((String) o).substring(0, 4);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 将日期格式变为  yyyy-mm
     * @param time
     * @return
     */
    public static String getYearAndMonth(String time) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(DateUtil.getDate1(time));
        Integer year = instance.get(Calendar.YEAR);
        Integer month = instance.get(Calendar.MONTH) + 1;
        return year + "-" + month;
    }

    /**
     * 将日期格式变为  yyyy-mm
     * @param date
     * @return
     */
    public static String getYearAndMonth(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(DateUtil.getDate1(DateUtil.getString1(date)));
        Integer year = instance.get(Calendar.YEAR);
        Integer month = instance.get(Calendar.MONTH) + 1;
        return year + "-" + month;
    }

    /**
     * 计算两个日期相差的时间戳
     * @param minute 被减数
     * @param minus 减数
     * @return
     */
    public static Long poor(Date minute, Date minus) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(minute);
        long timeInMillis = instance.getTimeInMillis();
        instance.setTime(minus);
        long timeInMillis1 = instance.getTimeInMillis();
        return timeInMillis - timeInMillis1;
    }

    /**
     * 计算两个日期相差的小时数
     * @param beginTime
     * @param endTime
     * @return
     */
    public static Long calculateHour(Date beginTime, Date endTime) {
        LocalDateTime begin = transformation(beginTime);
        LocalDateTime end = transformation(endTime);
        return Math.abs(ChronoUnit.HOURS.between(begin, end));
    }
    /**
     * 计算两个日期相差的分钟数
     * @param beginTime
     * @param endTime
     * @return
     */
    public static Long calculateMinute(Date beginTime, Date endTime) {
        LocalDateTime begin = transformation(beginTime);
        LocalDateTime end = transformation(endTime);
        return Math.abs(ChronoUnit.MINUTES.between(begin, end));
    }

    /**
     * 计算两个日期相差的秒数
     * @param beginTime
     * @param endTime
     * @return
     */
    public static Long calculateSeconds(Date beginTime, Date endTime) {
        LocalDateTime begin = transformation(beginTime);
        LocalDateTime end = transformation(endTime);
        return Math.abs(ChronoUnit.SECONDS.between(begin, end));
    }

    /**
     * 计算两个日期相差的天数
     * 超过一秒也算一天向上取整
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public static Integer subDay(Date beginTime, Date endTime) {
        long begin = beginTime.getTime();
        long end = endTime.getTime();
        double sub = Arith.sub(begin, end);
        double div = Arith.div(sub, (24 * 60 * 60 * 1000));
        return Arith.ceil(div);
    }

    /**
     * Date 转换 LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime transformation(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * 获取今天开始时间
     */
    public static Date getTodayStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    /**
     * 获取今天结束时间
     */
    public static Date getTodayEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    /**
     * 获取日期开始时间
     */
    public static Date getDateStartTime(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        return instance.getTime();
    }

    /**
     * 获取日期结束时间
     */
    public static Date getDateEndTime(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(Calendar.HOUR_OF_DAY, 23);
        instance.set(Calendar.MINUTE, 59);
        instance.set(Calendar.SECOND, 59);
        instance.set(Calendar.MILLISECOND, 999);
        return instance.getTime();
    }

    /**
     * 对比日期是否相等
     * @param date1
     * @param date2
     * @return
     */
    public static boolean equals(Date date1, Date date2) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTime(date1);
        calendar2.setTime(date2);
        return calendar.equals(calendar2);
    }

    /**
     * 获取间隔分钟，最少为1
     * @param begin
     * @param end
     * @return
     */
    public static Integer getUseMin(Date begin,Date end){
        if(begin==null || end==null){
            return null;
        }
        long wms= cn.hutool.core.date.DateUtil.between(begin,end, DateUnit.MS);
        Integer workUsedTime= Arith.ceil(Arith.div(Arith.div(wms,1000),60));
        return workUsedTime;
    }
    /**
     * 获取开始日期
     */
    public static Date getBeginTime(Date date){
        String string1 = getString1(date);
        string1 += " 00:00:00";
        return getDate3(string1);
    }
    /**
     * 获取结束日期
     */
    public static Date getEndTime(Date date){
        String string1 = getString1(date);
        string1 += " 23:59:59";
        return getDate3(string1);
    }

    public static String convertSecondsToDuration(long seconds) {
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long remainingSeconds = seconds % 60;
        return String.format("%02d时%02d分%02d秒", hours, minutes, remainingSeconds);
    }
}
