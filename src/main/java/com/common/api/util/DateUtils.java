package com.common.api.util;

import org.apache.commons.lang.time.DateFormatUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 日期工具类
 * 默认使用 "yyyy-MM-dd HH:mm:ss" 格式化日期
 */
public final class DateUtils {
    /**
     * 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_SHORT = "yyyy-MM-dd";

    /**
     * 英文全称  如：2010-12-01 23:15:06
     */
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 中文简写  如：2010年12月01日
     */
    public static String FORMAT_SHORT_CN = "yyyy年MM月dd";

    /**
     * 中文全称  如：2010年12月01日  23时15分06秒
     */
    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";

    /**
     * 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

    /**
     * PACS系统中日期格式 yyyyMMDDhhmmss
     */
    public static String FORMAT_PACS_FROM = "yyyyMMddhhmmss";

    /**
     * PACS系统中生日日期格式 yyyyMMdd
     */
    public static String FORMAT_PACS_B_DAY = "yyyyMMdd";

    public static String FORMAT_HH_MM = "HHmm";

    public static String FORMAT_HH_MM_TIME = "HH:mm";

    /**
     * PACS系统中日期格式yyyy-MM-dd HH:mm
     */
    public static String FORMAT_PACS_TO = "yyyy-MM-dd HH:mm";

    /**
     * PACS系统中日期格式 MM-dd HH:mm
     */
    public static String FORMAT_PACS_MM_DD_HH_MM = "MM-dd HH:mm";

    /**
     * 不返回年份的日期 MM-dd
     */
    public static String FORMAT_PACS_MM_DD= "MM-dd";

    /**
     * 获得默认的 date pattern
     */
    public static String getDatePattern() {
        return FORMAT_LONG;
    }

    /**
     * 根据预设格式返回当前日期
     *
     * @return {@link String}
     */
    public static String getNow() {
        return format(new Date());
    }

    /**
     * 根据用户格式返回当前日期
     *
     * @param format
     * @return {@link String}
     */
    public static String getNow(String format) {
        return format(new Date(), format);
    }


    /**
     * 使用预设格式格式化日期
     *
     * @param date
     * @return {@link String}
     */
    public static String format(Date date) {
        return format(date, getDatePattern());
    }

    /**
     * 使用用户格式格式化日期
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return {@link Date}
     */
    public static String format(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }

    /**
     * 使用预设格式提取字符串日期
     *
     * @return {@link Date}
     */
    public static String format(Long longDate) {
        Date date = new Date();
        date.setTime(longDate);
        return format(date);
    }

    public static Date parse(Long longDate) {
        Date date = new Date();
        date.setTime(longDate);
        return date;
    }

    /**
     * 使用预设格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @return {@link Date}
     */
    public static Date parse(String strDate) {
        return parse(strDate, getDatePattern());
    }


    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return {@link Date}
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }


    /**
     * 在日期上增加数个整月
     *
     * @param date 日期
     * @param n    要增加的月数
     * @return {@link Date}
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    /**
     * 在日期上增加天数
     *
     * @param date 日期
     * @param n    要增加的天数
     * @return {@link Date}
     */
    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();
    }

    /**
     * 在日期上增加小时
     *
     * @param date 日期
     * @param n    要增加的小时数
     * @return {@link Date}
     */
    public static Date addHour(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, n);
        return cal.getTime();
    }

    /**
     * 在日期上增加分钟
     *
     * @param date 日期
     * @param n    要增加的分钟数
     * @return {@link Date}
     */
    public static Date addMinute(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, n);
        return cal.getTime();
    }

    /**
     * 在日期上增加秒钟
     *
     * @param date 日期
     * @param n    要增加的秒钟数
     * @return {@link Date}
     */
    public static Date addSecond(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, n);
        return cal.getTime();
    }

    /**
     * 获取时间戳
     */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    /**
     * 获取日期年份
     *
     * @param date 日期
     * @return {@link String}
     */
    public static String getYear(Date date) {
        return format(date).substring(0, 4);
    }

    /**
     * 按默认格式的字符串距离今天的天数
     *
     * @param date 日期字符串
     * @return {@link Integer}
     */
    public static int countDays(String date) {
        return countDays(parseDate(date));
    }

    /**
     * 按默认格式的字符串距离今天的天数
     *
     * @param date 日期
     * @return {@link Integer}
     */
    public static int countDays(Date date) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
    }

    /**
     * 按用户格式字符串距离今天的天数
     *
     * @param date   日期字符串
     * @param format 日期格式
     * @return {@link Integer}
     */
    public static int countDays(String date, String format) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date, format));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;
    }

    /**
     * 按用户格式字符串计算两个日期相距的天数
     *
     * @param format 日期格式
     * @return {@link Integer}
     */

    public static int countDays(String date1, String date2, String format) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(DateUtils.parse(date1, format));
        c2.setTime(DateUtils.parse(date2, format));

        long t1 = c1.getTime().getTime();
        long t2 = c2.getTime().getTime();
        return (int) (t2 / 1000 - t1 / 1000) / 3600 / 24;
    }

    /**
     * 计算两个日期相距的小时数
     *
     * @param date1
     * @param date2
     * @return {@link Integer}
     */
    public static int countHours(Date date1, Date date2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);

        long t1 = c1.getTime().getTime();
        long t2 = c2.getTime().getTime();
        return Math.round((float) ((t2 / 1000d - t1 / 1000d) / 3600));
    }

    /**
     * 计算两个日期相距的天数
     *
     * @param date1
     * @param date2
     * @return {@link Integer}
     */
    public static int countDays(Date date1, Date date2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);

        long t1 = c1.getTime().getTime();
        long t2 = c2.getTime().getTime();
        return Math.round((float) ((t2 / 1000d - t1 / 1000d) / 3600 / 24f));
    }

    /**
     * 计算两个日期相距的返回的字符串
     *
     * @param date1
     * @param date2
     * @return {@link String}
     */
    public static String getCountDays(Date date1, Date date2) {
        if (date1 == null || date2 == null) return "未知";
        int hours = countHours(date1, date2);
        if (hours < 72) {
            return hours + "小时";
        } else if (hours < 24 * 365) {
            return (hours / 24) + "天";
        } else {
            return getAge(date1, FORMAT_FULL) + "岁";
        }
    }

    /**
     * 计算当前时间距离date1距离返回的字符串
     *
     * @param date1
     * @return
     */
    public static String getCountDays(Date date1) {
        Calendar c = Calendar.getInstance();
        return getCountDays(date1, c.getTime());
    }

    /**
     * 按用户格式字符串计算两个日期相距的天数
     *
     * @param date1
     * @param date2
     * @param format
     * @return
     */
    @SuppressWarnings("deprecation")
    public static int countDays(Date date1, Date date2, String format) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(DateUtils.parse(DateUtils.format(date1, format), format));
        c2.setTime(DateUtils.parse(DateUtils.format(date2, format), format));
        long t1 = c1.getTime().getTime();
        long t2 = c2.getTime().getTime();
        return (int) (t2 / 1000 - t1 / 1000) / 3600 / 24;
    }

    /**
     * 按用户格式字符串计算两个日期相距的小时数除以6
     *
     * @param date1
     * @param date2
     * @param format
     * @return
     */

    public static int countHours(String date1, String date2, String format) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(DateUtils.parse(date2 + " 00:00:00", format));
        c2.setTime(DateUtils.parse(date2 + " 00:00:00", format));

        long t1 = c1.getTime().getTime();
        long t2 = c2.getTime().getTime();
        return (int) (t2 / 1000 - t1 / 1000) / 3600 / 6;
    }

    /**
     * 得到curDate本周的第一天
     *
     * @param curDate
     * @return
     */
    @SuppressWarnings("static-access")
    public static Date getFirstDayInWeek(Date curDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(curDate);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        return c.getTime();
    }

    /**
     * 得到curDate本周的最后一天
     *
     * @param curDate
     * @return
     */
    @SuppressWarnings("static-access")
    public static Date getLastDayInWeek(Date curDate) {
        Calendar c = Calendar.getInstance();
        c.setTime(curDate);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
        return c.getTime();
    }

    /**
     * 日期格式化转换
     *
     * @param dateStr
     * @param bFormat
     * @param eFormat
     * @return
     */
    public static String converFormat(String dateStr, String bFormat, String eFormat) {
        Date date = parse(dateStr, bFormat);
        return format(date, eFormat);
    }

    /**
     * 得到岁数
     *
     * @param birthday
     * @param fromat
     * @return
     */
    @SuppressWarnings("deprecation")
    public static int getAge(String birthday, String fromat) {
        Date date = parse(birthday, fromat);
        Date currDate = new Date();
        return currDate.getYear() - date.getYear();
    }

    /**
     * 得到岁数
     *
     * @param birthday
     * @param fromat
     * @return
     */
    @SuppressWarnings("deprecation")
    public static int getAge(Date birthday, String fromat) {

        Date currDate = new Date();
        return currDate.getYear() - birthday.getYear();
    }

    /**
     * 得到当前时间
     *
     * @return
     */
    public static Calendar getCurrent() {
        return Calendar.getInstance();
    }

    /**
     * 得到cal的年月日和设置小时，分，秒的时间
     *
     * @param cal
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Calendar getCalendar(Calendar cal, int hour, int minute, int second) {
        return getCalendar(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH),
                hour, minute, second);
    }

    /**
     * 得到设置年,月,日的时间 小时0，分钟0，秒数0，毫秒0
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Calendar getCalendar(int year, int month, int day) {
        return getCalendar(year, month, day, 0, 0, 0);
    }

    /***
     * 得到设置年,月,日,小时，分钟，秒数的时间 毫秒0
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return 设置的时间
     */
    public static Calendar getCalendar(int year, int month, int day, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, hour, minute, second);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    /**
     * 得到本周第一天开始时间
     *
     * @return
     */
    public static Date getCurrentWeekBefore() {
        Calendar cal = getCurrent();
        cal.set(Calendar.DAY_OF_WEEK, 1);
        return getCalendar(cal, 0, 0, 0).getTime();
    }

    /**
     * 得到过去一周的时间
     *
     * @return
     */
    public static Date getBeforeWeek() {
        Calendar cal = getCurrent();
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 6);
        return getCalendar(cal, 24, 0, 0).getTime();
    }

    /**
     * 得到过去一个月的时间
     *
     * @return
     */
    public static Date getBeforeMonth() {
        Calendar cal = getCurrent();
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 29);
        return getCalendar(cal, 24, 0, 0).getTime();
    }

    /**
     * 得到下周开始时间
     *
     * @return
     */
    public static Date getCurrentWeekAfter() {
        Calendar cal = getCurrent();
        cal.set(Calendar.DAY_OF_WEEK, 14);
        return getCalendar(cal, 24, 0, 0).getTime();
    }

    /**
     * 得到今天0点的时间
     *
     * @return
     */
    public static Date getCurrentDateBefore() {
        Calendar cal = getCurrent();
        return getCalendar(cal, 0, 0, 0).getTime();
    }


    /**
     * 得到明天0点的时间
     *
     * @return
     */
    public static Date getCurrentDateAfter() {
        Calendar cal = getCurrent();
        return getCalendar(cal, 24, 0, 0).getTime();
    }

    /**
     * 得到今天0点的时间戳
     *
     * @return
     */
    public static long getCurrentDateTimeBefore() {
        Calendar cal = getCurrent();
        Date time = getCalendar(cal, 0, 0, 0).getTime();
        return time.getTime();
    }


    /**
     * 得到明天0点的时间戳
     *
     * @return
     */
    public static long getCurrentDateTimeAfter() {
        Calendar cal = getCurrent();
        Date time = getCalendar(cal, 24, 0, 0).getTime();
        return time.getTime();
    }

    /**
     * 得到现在过去一天的时间
     *
     * @return
     */
    public static Date getCurrentDateBeforeDay() {
        Calendar cal = getCurrent();
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 1);
        return cal.getTime();
    }

    /**
     * 得到现在未来一天的时间
     *
     * @return
     */
    public static Date getCurrentDateAfterDay() {
        Calendar cal = getCurrent();
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
        return cal.getTime();
    }

    /**
     * 得到指定时间0点的时间
     *
     * @return
     */
    public static Date getCurrentDateBefore(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getCalendar(cal, 0, 0, 0).getTime();
    }

    /**
     * 得到指定时间明天0点的时间
     *
     * @return
     */
    public static Date getCurrentDateAfter(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getCalendar(cal, 24, 0, 0).getTime();
    }

    /**
     * 得到当前日期的前<code>day<code>天0:00:00时间
     *
     * @return
     */
    public static Date getCurrentDateBeforeDay(int day) {
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal2.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) - day, 0, 0, 0);
        cal2.set(Calendar.MILLISECOND, 0);
        return cal2.getTime();
    }

    /**
     * 得到当前日期的前<code>hour<code>天00:00时间
     *
     * @return
     */
    public static Date getCurrentDateBeforeHour(int hour) {
        Calendar cal = Calendar.getInstance();
        return getCalendar(cal, cal.get(Calendar.HOUR_OF_DAY) - hour, 0, 0).getTime();
    }

    /**
     * 得到当前日期的前<code>minute<code>分钟00:00时间
     *
     * @return
     */
    public static Date getCurrentDateBeforeMinute(int minute) {
        Calendar cal = Calendar.getInstance();
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, -minute);// 5分钟之前的时间
        Date beforeD = beforeTime.getTime();
        return beforeD;
    }

    /**
     * 得到指定时间0分0秒的时间
     *
     * @return
     */
    public static Date getCurrentHourBefore(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getCalendar(cal, cal.get(Calendar.HOUR_OF_DAY), 0, 0).getTime();
    }

    /**
     * 得到今天到00：00 分钟数
     *
     * @return
     */
    public static Long getToDayMinute() {
        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();
        cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + 1);
        Date toTime = parse(format(cal.getTime(), DateUtils.FORMAT_SHORT), DateUtils.FORMAT_SHORT);
        return (toTime.getTime() - now.getTime())/1000/60;
    }

    /**
     * 转换字符串成date
     *
     * @param date
     * @return
     */
    public static Date parseDate(String date) {
        if (date == null) return null;
        if (date.matches("^\\d+$")) {
            try {
                return new Date(Long.valueOf(date));
            } catch (Throwable e) {
            }
        }
        String[] pattern = new String[]{
                "yyyy-MM-dd HH:mm:ss",
                "yyyy/MM/dd HH:mm:ss",
                "yyyyMMddHHmmss",
                "yyyy-MM-dd",
                "yyyy/MM/dd",
                "yyyyMMdd"
        };
        Date cDate = null;
        for (int i = 0; i < pattern.length; i++) {
            cDate = parse(date, pattern[i]);
            if (cDate != null) {
                return cDate;
            }
        }
        return null;
    }


    /**
     * 得到指定下一个小时时间0分0秒的时间
     *
     * @return
     */
    public static Date getCurrentHourAfter(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getCalendar(cal, cal.get(Calendar.HOUR_OF_DAY) + 1, 0, 0).getTime();
    }

    /**
     * 得到指定下一个小时时间0分0秒的时间
     *
     * @return
     */
    public static Date getDatePositionHour(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getCalendar(cal, hour, 0, 0).getTime();
    }

    /**
     * 得到年龄
     *
     * @param date
     * @return
     */
    public static String getAgesDetail(String date) {
        Date dat = parseDate(date);
        return getAgesDetail(dat);
    }

    /**
     * 得到年龄
     *
     * @return
     */
    public static String getAgesDetail(Date date) {

        Calendar c = Calendar.getInstance();
        if (date == null) {
            c = null;
        } else {
            c.setTime(date);
        }
        return getAgesDetail(c);
    }

    /**
     * 得到年龄
     *
     * @param date
     * @return
     */
    public static String getAgesDetail(Calendar date) {
        if (date == null) {
            return "未知";
        }

        Calendar cal = Calendar.getInstance();
        int hour = countHours(date.getTime(), cal.getTime());
        if (hour < 72) {
            return hour + "小时";
        } else if (hour < 100 * 24) {
            return (hour / 24) + "天";
        } else if (hour < 24 * 24 * 30) {
            if (cal.get(Calendar.YEAR) == date.get(Calendar.YEAR)) {
                return (cal.get(Calendar.MONTH) - date.get(Calendar.MONTH)) + "月";
            } else {
                return (cal.get(Calendar.MONTH) + 12 * (cal.get(Calendar.YEAR) - date.get(Calendar.YEAR)) - date.get(Calendar.MONTH)) + "月";
            }
        } else {
            return (cal.get(Calendar.YEAR) - date.get(Calendar.YEAR)) + "岁";
        }
    }

    /**
     * UTC日期转换.
     * 例如：2018-08-14 11:07:35 UTC
     */
    public static String utcFormat(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(dateStr));
            calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 8);
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(calendar.getTime());
        } catch (Exception e) {
        }
        return dateStr;

    }

    /**
     * 第一个时间大于第二个则为true
     *
     * @param before
     * @param after
     * @return
     */
    public static boolean compareDate(Date before, Date after) {
        return before.getTime() - after.getTime() > 0;
    }


    /**
     * 将小时分钟转换为HH:mm格式字符串
     *
     * @param dateHour
     * @param dateMinute
     * @return
     */
    public static String formatHourMinute(Integer dateHour, Integer dateMinute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, dateHour);
        if (null == dateMinute) {
            dateMinute = 0;
        }
        calendar.set(Calendar.MINUTE, dateMinute);
        return DateFormatUtils.format(calendar.getTimeInMillis(), "HH:mm");
    }
}