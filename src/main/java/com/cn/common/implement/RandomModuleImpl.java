package com.cn.common.implement;

import com.cn.common.RandomModule;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 随机数据处理模块实现
 * Copyright: Copyright (c) 2008 Company: com.cn 2010-03-29——修改增加随机日期函数
 *
 * @author Hartwell
 * @version 2.0
 */
public class RandomModuleImpl implements RandomModule {
    public static Logger logger = Logger.getLogger(RandomModuleImpl.class);

    public static Random random = new Random();

    public static void main(String[] arg) throws ParseException {
        RandomModuleImpl random = new RandomModuleImpl();
        for (int i = 0; i < 100; ++i) {
            Date date = random.getRandomDate("2009-03-01", "2010-03-31",
                    "yyyy-MM-dd");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd");
            logger.debug(dateFormat.format(date));
        }
    }

    public RandomModuleImpl() {
    }

    /**
     * getRBoolean 取得随机布尔值
     *
     * @return Boolean 返回随机生成的布尔值
     */
    @Override
    public boolean getRandomBoolean() {
        boolean myRBoolean = random.nextBoolean();
        return myRBoolean;
    }

    /**
     * getRandColor 取得随机颜色
     *
     * @param fc 前台颜色
     * @param bc 后台颜色
     * @return Color
     */
    private Color getRandomColor(int fc, int bc) {
        // Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 在起始时间和结束时间间取得随机日期
     *
     * @param beginDate 起始时间
     * @param endDate   结束时间
     * @return 随机时间
     */
    @Override
    public Date getRandomDate(Date beginDate, Date endDate) {
        long begin = beginDate.getTime();
        long end = endDate.getTime();
        Date date = new Date(begin
                + (long) (random.nextDouble() * (end - begin)));
        return date;
    }

    /**
     * 在起始时间和结束时间间取得随机日期
     *
     * @param beginDateString 起始时间
     * @param endDateString   结束时间
     * @param dateFormat      日期格式
     * @return 随机时间
     * @throws ParseException io异常
     */
    @Override
    public Date getRandomDate(String beginDateString, String endDateString,
                              String dateFormat) throws ParseException {
        DateFormat format = new SimpleDateFormat(dateFormat);
        Date startDate = format.parse(beginDateString);
        Date endDate = format.parse(endDateString);
        Date date = this.getRandomDate(startDate, endDate);
        return date;
    }

    /**
     * getRandomImage 产生随机码图片
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param sRand    String 随机码字符
     * @throws IOException io异常
     */
    @Override
    public void getRandomImage(HttpServletRequest request,
                               HttpServletResponse response, String sRand) throws IOException {
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        int length = sRand.length();
        // 在内存中创建图象
        int width = 20 * length;
        int height = 20;
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        // 获取图形上下文
        Graphics g = image.getGraphics();

        // 设定背景色
        g.setColor(getRandomColor(200, 250));
        g.fillRect(0, 0, width, height);

        // 设定字体
        g.setFont(new Font("Times New Roman", Font.BOLD, 20));

        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandomColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        for (int i = 0; i < length; i++) {
            String rand = sRand.substring(i, i + 1);
            // 将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random
                    .nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i, 16);
        }

        // 图象生效
        g.dispose();
        ServletOutputStream responseOutputStream = response.getOutputStream();
        // 输出图象到页面
        ImageIO.write(image, "JPEG", responseOutputStream);
        // 以下关闭输入流！
        responseOutputStream.flush();

        responseOutputStream.close();
    }

    /**
     * getRInt 取得的随机整数
     *
     * @return int 返回的随机整数
     */
    @Override
    public int getRandomInt() {
        return random.nextInt();
    }

    /**
     * getRInt 取得小于指定数值的随机整数
     *
     * @param maxint 指定整数
     * @return int 返回的随机整数
     */
    @Override
    public int getRandomInt(int maxint) {
        return random.nextInt(maxint);
    }

    /**
     * getRStr 取得指定位数的随机字符串
     *
     * @param myint  int[] 指定数组
     * @param length int 生成随机数的位数
     * @return String 返回随机字符串
     */
    @Override
    public String getRStr(int[] myint, int length) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i = i + 1) {
            int r = random.nextInt(myint.length);
            buffer.append((char) myint[r]);
        }
        return buffer.toString();
    }

    /**
     * getRStr 取得指定位数的随机字符串
     *
     * @param myString String[] 指定字符串组
     * @param length   int 生成随机数的位数
     * @return String 返回随机字符串
     */
    @Override
    public String getRStr(String[] myString, int length) {
        StringBuffer mystrbuf = new StringBuffer();
        for (int i = 0; i < length; i = i + 1) {
            int r = random.nextInt(myString.length);
            mystrbuf.append(myString[r]);
        }
        return mystrbuf.toString();
    }

    /**
     * getUUID 取得32位字符串的UUID字符串
     *
     * @return String 返回随机生成的UUID字符串
     */
    @Override
    public String getUUID() {
        String uUIDString = UUID.randomUUID().toString();
        uUIDString = uUIDString.replaceAll("-", "");
        return uUIDString;
    }

}
