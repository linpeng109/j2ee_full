package com.cn.common;

import java.text.ParseException;
import java.util.Date;

public interface DateModule {
    /**
     * convertToDate 将字符串转换成日期类型
     *
     * @param dateString String 输入日期字符串
     * @return Date 返回日期类型的对象
     * @throws ParseException 抛出异常
     */
    public Date convertToDate(String dateString) throws ParseException;

    /**
     * convertToDate 按照指定格式将字符串转换成日期类型
     *
     * @param dateString       String 输入日期字符串
     * @param dateFormatString String
     * @return Date 返回日期类型的对象
     * @throws ParseException 抛出异常
     */
    public Date convertToDate(String dateString, String dateFormatString)
            throws ParseException;

    /**
     * convertToFull 将日期型转换为“全”型字符串日期
     *
     * @param date Date 输入日期
     * @return String 全型日期字符串
     */
    public String convertToFull(Date date);

    /**
     * @param dateString 日期型字符串
     * @return 全型日期型字符串
     * @throws ParseException 抛出解析异常
     */
    public String convertToFull(String dateString) throws ParseException;

    /**
     * @param date 日期
     * @return 长型日期型字符串
     */
    public String convertToLong(Date date);

    /**
     * @param dateString 日期型字符串
     * @return 长型日期型字符串
     * @throws ParseException 抛出解析异常
     */
    public String convertToLong(String dateString) throws ParseException;

    /**
     * convertToMedium 将日期型转换为“中”型字符串日期
     *
     * @param date 待转换日期
     * @return String 返回结果字符串
     */
    public String convertToMedium(Date date);

    /**
     * convertToMedium 将日期型转换为“中”型字符串日期
     *
     * @param dateString Date
     * @return String 返回结果字符串
     * @throws ParseException 解析异常
     */
    public String convertToMedium(String dateString) throws ParseException;

    /**
     * convertToShort 将日期型转换为“短”型字符串日期
     *
     * @param date Date
     * @return String
     */
    public String convertToShort(Date date);

    /**
     * convertToShort 将日期型转换为“短”型字符串日期
     *
     * @param dateString Date
     * @return String 结果字符串
     * @throws ParseException 解析异常
     */
    public String convertToShort(String dateString) throws ParseException;

    /**
     * convertToSQLDate 将字符串型日期，转换成SQL DATE型日期
     *
     * @param dateString String 输入的字符串型日期
     * @return Date 返回SQLDate型日期
     * @throws ParseException 抛出异常
     */
    public java.sql.Date convertToSQLDate(String dateString)
            throws ParseException;

    /**
     * convertToString 按照注入的时间格式将日期型转换成字符型
     *
     * @param date Date
     * @return String
     */
    public String convertToString(Date date);

    /**
     * convertToString 按照指定的时间格式将日期型转换成字符型
     *
     * @param date             Date
     * @param dateFormatString String
     * @return String
     */
    public String convertToString(Date date, String dateFormatString);

    public String getDateFormatString();

    /**
     * getTodayBegin 返回今天0时0分0秒的日期
     *
     * @param date Date
     * @return Date 返回今天0时0分0秒的日期
     */
    public Date getDayBegin(Date date);

    /**
     * getYesterday 返回今天24时0分0秒的日期
     *
     * @param date Date
     * @return Date 返回今天24时0分0秒的日期
     */
    public Date getDayEnd(Date date);

    /**
     * getMonthBegin 返回当前月第1天0时0分0秒的日期
     *
     * @param date Date
     * @return Date 返回当前月第1天0时0分0秒的日期
     */
    public Date getMonthBegin(Date date);

    /**
     * getMonthEnd 返回当前月最后1天24时0分0秒的日期
     *
     * @param date Date
     * @return Date 返回当前月第1天0时0分0秒的日期
     */
    public Date getMonthEnd(Date date);

    /**
     * getWeekBegin 返回当前周第1天0时0分0秒的日期
     *
     * @param date Date
     * @return Date 返回当前周第1天0时0分0秒的日期
     */
    public Date getWeekBegin(Date date);

    /**
     * getWeekEnd 返回当前周第7天24时0分0秒的日期
     *
     * @param date Date
     * @return Date 返回当前周第7天24时0分0秒的日期
     */
    public Date getWeekEnd(Date date);

    /**
     * getYearBegin 返回当前年第1天0时0分0秒的日期
     *
     * @param date Date
     * @return Date 返回当前年第1天0时0分0秒的日期
     */
    public Date getYearBegin(Date date);

    /**
     * getYearEnd 返回当前年最后1天24时0分0秒的日期
     *
     * @param date Date
     * @return Date 返回当前年最后1天0时0分0秒的日期
     */
    public Date getYearEnd(Date date);

}
