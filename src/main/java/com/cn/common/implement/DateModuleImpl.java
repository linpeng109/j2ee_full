package com.cn.common.implement;

import com.cn.common.DateModule;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期处理模块实现
 *
 * @author Hartwell
 * @version 1.0
 */
public class DateModuleImpl implements DateModule {
    /**
     * main 测试
     *
     * @param params String[] 输入字符串组
     * @throws ParseException 抛出解析错误
     */
    public static void main(String[] params) throws ParseException {
        DateModuleImpl dateModule = new DateModuleImpl();
        dateModule.dateFormatString = "yyyyMMdd";
        // Date date = dateModule.convertToDate("20070801");
        // Date dayBegin = dateModule.getDayBegin(date);
        // Date dayEnd = dateModule.getDayEnd(date);
        // Date weekBegin = dateModule.getWeekBegin(date);
        // Date weekEnd = dateModule.getWeekEnd(date);
        // Date monthBegin = dateModule.getMonthBegin(date);
        // Date monthEnd = dateModule.getMonthEnd(date);
        // Date yearBegin = dateModule.getYearBegin(date);
        // Date yearEnd = dateModule.getYearEnd(date);
        // logger.debug("DayBegin:" + (dayBegin));
        // logger.debug("DayEnd:" + (dayEnd));
        // logger.debug("WeekBegin:" + (weekBegin));
        // logger.debug("WeekEnd:" + (weekEnd));
        // logger.debug("MonthBegin:" + (monthBegin));
        // logger.debug("MonthEnd:" + (monthEnd));
        // logger.debug("YearBegin:" + (yearBegin));
        // logger.debug("YearEnd:" + (yearEnd));
        // logger.debug(dateModule.convertToMedium(date));
        logger.debug(dateModule.getRemainYearToString("2011-02-01",
                "2013-01-02"));
    }

    /**
     * dateFormatString 日期格式串
     */
    public String dateFormatString;
    public static Logger logger = Logger.getLogger(DateModuleImpl.class);

    private static Calendar calS = Calendar.getInstance();

    /**
     * 定义整则表达式
     */
    private static Pattern p = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");

    /**
     * 转换 dataAndTime 2013-12-31 23:59:59 到 date 2013-12-31
     *
     * @param dateAndTime 时间字符串
     * @return 返回转换结果字符串
     */
    public static String getDate(String dateAndTime) {
        if (dateAndTime != null && !"".equals(dateAndTime.trim())) {
            Matcher m = p.matcher(dateAndTime);
            if (m.find()) {
                return dateAndTime.subSequence(m.start(), m.end()).toString();
            }
        }
        return "data error";
    }

    public DateModuleImpl() {
    }

    /**
     * 将字符串转换成日期类型
     *
     * @param dateString String 输入日期字符串
     * @return Date 返回日期类型的对象
     * @throws ParseException 解析异常
     */
    @Override
    public java.util.Date convertToDate(String dateString)
            throws ParseException {
        if (dateString == null || "".equals(dateString)) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
        dateFormat.setLenient(false);
        Date timeDate = dateFormat.parse(dateString);
        return timeDate;
    }

    /**
     * convertToDate 按照指定格式将字符串转换成日期类型
     *
     * @param dateString       String 输入日期字符串
     * @param dateFormatString String
     * @return Date 返回日期类型的对象
     * @throws ParseException 抛出异常
     */
    @Override
    public java.util.Date convertToDate(String dateString,
                                        String dateFormatString) throws ParseException {
        if (dateString == null || "".equals(dateString)) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
        dateFormat.setLenient(false);
        Date timeDate = dateFormat.parse(dateString);
        return timeDate;
    }

    /**
     * convertToFull 将日期型转换为“全”型字符串日期
     *
     * @param date Date
     * @return String
     */
    @Override
    public String convertToFull(Date date) {
        DateFormat dateformat = DateFormat.getDateInstance(DateFormat.FULL);
        String datestring = dateformat.format(date);
        return datestring;
    }

    /**
     * convertToFull 将日期型转换为“全”型字符串日期
     *
     * @param dateString Date
     * @return String 结果字符串
     * @throws ParseException 抛出解析异常
     */
    @Override
    public String convertToFull(String dateString) throws ParseException {
        Date date = this.convertToDate(dateString);
        DateFormat dateformat = DateFormat.getDateInstance(DateFormat.FULL);
        String datestring = dateformat.format(date);
        return datestring;
    }

    /**
     * convertToLong 将日期型转换为“长”型字符串日期
     *
     * @param date Date 待转换日期
     * @return String 返回转换结果字符串
     */
    @Override
    public String convertToLong(Date date) {
        DateFormat dateformat = DateFormat.getDateInstance(DateFormat.LONG);
        String datestring = dateformat.format(date);
        return datestring;
    }

    /**
     * convertToLong 将日期型转换为“长”型字符串日期
     *
     * @param dateString Date
     * @return String 结果字符串
     * @throws ParseException 抛出解析异常
     */
    @Override
    public String convertToLong(String dateString) throws ParseException {
        Date date = this.convertToDate(dateString);
        DateFormat dateformat = DateFormat.getDateInstance(DateFormat.LONG);
        String datestring = dateformat.format(date);
        return datestring;
    }

    /**
     * convertToMedium 将日期型转换为“中”型字符串日期
     *
     * @param date Date
     * @return String
     */
    @Override
    public String convertToMedium(Date date) {
        DateFormat dateformat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String datestring = dateformat.format(date);
        return datestring;
    }

    /**
     * convertToMedium 将日期型转换为“中”型字符串日期
     *
     * @param dateString Date 待转换日期
     * @return String 结果字符串
     * @throws ParseException 抛出解析异常
     */
    @Override
    public String convertToMedium(String dateString) throws ParseException {
        Date date = this.convertToDate(dateString);
        DateFormat dateformat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String datestring = dateformat.format(date);
        return datestring;
    }

    /**
     * convertToShort 将日期型转换为“短”型字符串日期
     *
     * @param date Date
     * @return String
     */
    @Override
    public String convertToShort(Date date) {
        DateFormat dateformat = DateFormat.getDateInstance(DateFormat.SHORT);
        String datestring = dateformat.format(date);
        return datestring;
    }

    /**
     * convertToShort 将日期型转换为“短”型字符串日期
     *
     * @param dateString 待转换日期字符串
     * @return String 结果字符串
     * @throws ParseException 抛出解析异常
     */
    @Override
    public String convertToShort(String dateString) throws ParseException {
        Date date = this.convertToDate(dateString);
        DateFormat dateformat = DateFormat.getDateInstance(DateFormat.SHORT);
        String datestring = dateformat.format(date);
        return datestring;
    }

    /**
     * convertToSQLDate 将字符串型日期，转换成SQL DATE型日期
     *
     * @param dateString String 输入的字符串型日期
     * @return Date 返回SQLDate型日期
     * @throws ParseException 抛出异常
     */
    @Override
    public java.sql.Date convertToSQLDate(String dateString)
            throws ParseException {
        if (dateString == null || "".equals(dateString)) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
        dateFormat.setLenient(false);
        Date timeDate = dateFormat.parse(dateString);
        java.sql.Date dateTime = new java.sql.Date(timeDate.getTime());
        return dateTime;
    }

    /**
     * convertToString 按照注入的时间格式将日期型转换成字符型
     *
     * @param date Date
     * @return String
     */
    @Override
    public String convertToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
        return dateFormat.format(date);
    }

    /**
     * convertToString 按照指定的时间格式将日期型转换成字符型
     *
     * @param date             Date
     * @param dateFormatString String
     * @return String
     */
    @Override
    public String convertToString(Date date, String dateFormatString) {
        DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
        return dateFormat.format(date);
    }

    @Override
    public String getDateFormatString() {
        return dateFormatString;
    }

    /**
     * getTodayBegin 返回今天0时0分0秒的日期
     *
     * @param date Date
     * @return Date 返回今天0时0分0秒的日期
     */
    @Override
    public Date getDayBegin(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * getYesterday 返回今天24时0分0秒的日期
     *
     * @param date Date
     * @return Date 返回今天24时0分0秒的日期
     */
    @Override
    public Date getDayEnd(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 24);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * getMonthBegin 返回当前月第1天0时0分0秒的日期
     *
     * @param date Date
     * @return Date 返回当前月第1天0时0分0秒的日期
     */
    @Override
    public Date getMonthBegin(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * getMonthEnd 返回当前月最后1天24时0分0秒的日期
     *
     * @param date Date
     * @return Date 返回当前月第1天0时0分0秒的日期
     */
    @Override
    public Date getMonthEnd(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 计算剩余时间
     *
     * @param startDateStr 起始时间
     * @param endDateStr   结束时间
     * @return 剩余时间字符
     */
    public String getRemainDateToString(String startDateStr, String endDateStr) {
        java.util.Date startDate = null;
        java.util.Date endDate = null;
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        try {
            endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        calS.setTime(startDate);
        int startY = calS.get(Calendar.YEAR);
        int startM = calS.get(Calendar.MONTH);
        int startD = calS.get(Calendar.DATE);
        int startDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);

        calS.setTime(endDate);
        int endY = calS.get(Calendar.YEAR);
        int endM = calS.get(Calendar.MONTH);
        // 处理2011-01-10到2011-01-10，认为服务为一天
        int endD = calS.get(Calendar.DATE) + 1;
        int endDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);

        StringBuilder sBuilder = new StringBuilder();
        if (endDate.compareTo(startDate) < 0) {
            return sBuilder.append("过期").toString();
        }
        int lday = endD - startD;
        if (lday < 0) {
            endM = endM - 1;
            lday = startDayOfMonth + lday;
        }
        // 处理天数问题，如：2011-01-01 到 2013-12-31 2年11个月31天 实际上就是3年
        if (lday == endDayOfMonth) {
            endM = endM + 1;
            lday = 0;
        }
        int mos = (endY - startY) * 12 + (endM - startM);
        int lyear = mos / 12;
        int lmonth = mos % 12;
        if (lyear > 0) {
            sBuilder.append(lyear + "年");
        }
        if (lmonth > 0) {
            sBuilder.append(lmonth + "个月");
        }
        if (lday > 0) {
            sBuilder.append(lday + "天");
        }
        return sBuilder.toString();
    }

    public String getRemainYearToString(String startDateStr, String endDateStr) {

        java.util.Date startDate = null;
        java.util.Date endDate = null;
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        try {
            endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }

        calS.setTime(startDate);
        int startY = calS.get(Calendar.YEAR);

        calS.setTime(endDate);
        int endY = calS.get(Calendar.YEAR);
        int endM = calS.get(Calendar.MONTH);

        if (endM >= 8) {
            return "间隔年:" + (endY - startY) + 1;
        } else {
            return "间隔年:" + (endY - startY);
        }

    }

    /**
     * getWeekBegin 返回当前周第1天0时0分0秒的日期
     *
     * @param date Date
     * @return Date 返回当前周第1天0时0分0秒的日期
     */
    @Override
    public Date getWeekBegin(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        // 设置一个星期的第1天为星期1，默认是星期日
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * getWeekEnd 返回当前周第7天24时0分0秒的日期
     *
     * @param date Date
     * @return Date 返回当前周第7天24时0分0秒的日期
     */
    @Override
    public Date getWeekEnd(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        // 设置一个星期的第1天为星期1，默认是星期日
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.set(Calendar.HOUR, 24);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * getYearBegin 返回当前年第1天0时0分0秒的日期
     *
     * @param date Date
     * @return Date 返回当前年第1天0时0分0秒的日期
     */
    @Override
    public Date getYearBegin(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * getYearEnd 返回当前年最后1天24时0分0秒的日期
     *
     * @param date Date
     * @return Date 返回当前年最后1天0时0分0秒的日期
     */
    @Override
    public Date getYearEnd(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public void setDateFormatString(String dateFormatString) {
        this.dateFormatString = dateFormatString;
    }

}
