package com.cn.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public interface RandomModule {
    /**
     * myint_09 定义数字0-9
     */
    public static int[] myint_09 = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57};

    /**
     * myint_AZ 定义字母A-Z
     */
    public static int[] myint_AZ = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74,
            75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90};

    /**
     * myint_az 定义字母a-z
     */
    public static int[] myint_az = {97, 98, 99, 100, 101, 102, 103, 104, 105,
            106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118,
            119, 120, 121, 122};

    /**
     * myint_all 定义A-z
     */
    public static int[] myint_all = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57,
            97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 65, 66, 67, 68,
            69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85,
            86, 87, 88, 89, 90, 108, 109, 110, 111, 112, 113, 114, 115, 116,
            117, 118, 119, 120, 121, 122};

    /**
     * mystring_china 定义常用汉字
     */
    public static String[] mystring_china = {"白", "毕", "卞", "蔡", "曹", "岑",
            "常", "车", "陈", "成", "程", "池", "邓", "丁", "范", "方", "樊", "费", "冯",
            "符", "傅", "甘", "高", "葛", "龚", "古", "关", "郭", "韩", "何", "贺", "洪",
            "侯", "胡", "华", "黄", "霍", "姬", "简", "江", "姜", "蒋", "金", "康", "柯",
            "孔", "赖", "郎", "乐", "雷", "黎", "李", "连", "廉", "梁", "廖", "林", "凌",
            "刘", "柳", "龙", "卢", "鲁", "陆", "路", "吕", "罗", "骆", "马", "梅", "孟",
            "莫", "母", "穆", "倪", "宁", "欧", "区", "潘", "彭", "蒲", "皮", "齐", "戚",
            "钱", "强", "秦", "丘", "邱", "饶", "任", "沈", "盛", "施", "石", "时", "史",
            "苏", "孙", "谭", "汤", "唐", "陶", "田", "童", "涂", "王", "危", "韦", "卫",
            "魏", "温", "文", "翁", "巫", "邬", "吴", "伍", "武", "席", "夏", "萧", "谢",
            "辛", "邢", "徐", "许", "薛", "严", "颜", "杨", "叶", "易", "殷", "尤", "于",
            "余", "俞", "虞", "元", "袁", "岳", "云", "曾", "詹", "张", "章", "赵", "郑",
            "钟", "周", "邹", "朱", "褚", "庄", "卓"};

    /**
     * getRBoolean 取得随机布尔值
     *
     * @return Boolean 返回随机生成的布尔值
     */
    public boolean getRandomBoolean();

    /**
     * 在起始时间和结束时间间取得随机日期
     *
     * @param startDate 起始时间
     * @param endDate   结束时间
     * @return 随机日期
     */
    public Date getRandomDate(Date startDate, Date endDate);

    /**
     * 在起始时间和结束时间间取得随机日期
     *
     * @param startDateString 起始时间
     * @param endDateString   结束时间
     * @param dateFormat      日期格式
     * @return 随机日期
     * @throws ParseException 解析错误
     */
    public Date getRandomDate(String startDateString, String endDateString,
                              String dateFormat) throws ParseException;

    /**
     * getRandomImage 产生随机码图片
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param sRand    String 随机码字符
     * @throws IOException IO错误
     */
    public void getRandomImage(HttpServletRequest request,
                               HttpServletResponse response, String sRand) throws IOException;

    /**
     * getRInt 取得的随机整数
     *
     * @return int 返回的随机整数
     */
    public int getRandomInt();

    /**
     * getRInt 取得小于指定数值的随机整数
     *
     * @param myint int 指定整数
     * @return int 返回的随机整数
     */
    public int getRandomInt(int myint);

    /**
     * getRStr 取得指定位数的随机字符串
     *
     * @param myint  int[] 指定数组
     * @param length int 生成随机数的位数
     * @return String 返回随机字符串
     */
    public String getRStr(int[] myint, int length);

    /**
     * getRStr 取得指定位数的随机字符串
     *
     * @param myString String[] 指定字符串组
     * @param length   int 生成随机数的位数
     * @return String 返回随机字符串
     */
    public String getRStr(String[] myString, int length);

    /**
     * getUUID 取得32位字符串的UUID字符串
     *
     * @return String 返回随机生成的UUID字符串
     */
    public String getUUID();
}
