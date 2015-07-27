package com.cn.iot.se3000;

import gnu.io.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Se3000切换台控制器
 *
 * @author linp
 */
public class Se900Controler {
    /**
     * @param args 输入字符串组
     * @throws InterruptedException              抛出终端错误
     * @throws IOException                       抛出io错误
     * @throws UnsupportedCommOperationException 抛出不支持操作错误
     * @throws PortInUseException                抛出端口占用错误
     * @throws NoSuchPortException               抛出无此端口错误
     */
    public static void main(String[] args) throws NoSuchPortException,
            PortInUseException, UnsupportedCommOperationException, IOException,
            InterruptedException {
        Se900Controler controler = new Se900Controler();
        controler.portName = "COM3";
        controler.bitRate = 115200;
        controler.gvg100 = new GVG100();
        controler.sendCommand(controler.gvg100.command_2);
    }

    private GVG100 gvg100;

    private int bitRate;

    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(Se900Controler.class);

    /**
     * 端口名称
     */
    private String portName;

    /**
     * 构造函数
     */
    public Se900Controler() {

    }

    public int getBitRate() {
        return bitRate;
    }

    public GVG100 getGvg100() {
        return gvg100;
    }

    public Logger getLogger() {
        return logger;
    }

    public String getPortName() {
        return portName;
    }

    /**
     * 发送端口命令
     *
     * @param command 输入指令字节组
     * @throws NoSuchPortException               抛出无此端口错误
     * @throws PortInUseException                抛出端口占用错误
     * @throws UnsupportedCommOperationException 抛出不支持操作错误
     * @throws IOException                       抛出io错误
     * @throws InterruptedException              抛出中断错误
     */
    public void sendCommand(byte[] command) throws NoSuchPortException,
            PortInUseException, UnsupportedCommOperationException, IOException,
            InterruptedException {
        CommPortIdentifier portIdentifier = CommPortIdentifier
                .getPortIdentifier(portName);
        if (portIdentifier.isCurrentlyOwned()) {
            logger.error("This port is currently in use");
        } else {

            /**
             * 定义端口
             */
            CommPort commPort = portIdentifier.open(this.getClass().getName(),
                    2000);
            if (commPort instanceof SerialPort) {

                /**
                 * 定义输入输出端口参数
                 */
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(this.bitRate,
                        SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE);

                /**
                 * 定义输入输出流
                 */
                InputStream in = serialPort.getInputStream();
                OutputStream out = serialPort.getOutputStream();

                /**
                 * 写出命令
                 */
                out.write(command);
                out.flush();

                /**
                 * 休眠
                 */
                Thread.sleep(1000);

                /**
                 * 读取端口
                 */
                StringBuffer result = new StringBuffer();
                int data;
                while ((data = in.read()) > -1) {
                    String tmp = Integer.toHexString(data);

                    if (tmp.length() == 1) {
                        tmp = "0" + tmp;
                    }
                    result.append(tmp).append(" ");
                }

                /**
                 * 打印返回
                 */
                logger.debug(result.toString());

                /**
                 * 关闭输入输出流及端口
                 */
                out.close();
                in.close();
                serialPort.close();
            }
        }

    }

    public void setBitRate(int bitRate) {
        this.bitRate = bitRate;
    }

    public void setGvg100(GVG100 gvg100) {
        this.gvg100 = gvg100;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

}
