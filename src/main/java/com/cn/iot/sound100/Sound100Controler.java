package com.cn.iot.sound100;

import gnu.io.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 音频切换器控制模块
 *
 * @author admin
 */
public class Sound100Controler {
    /**
     * 日志
     */
    public Logger logger = Logger.getLogger(Sound100Controler.class);

    /**
     * 通讯协议
     */
    private Switch100 switch100;

    /**
     * 通讯比特率
     */
    private int bitRate;

    /**
     * 通讯端口名称
     */
    private String portName;

    /**
     * 构造函数
     */
    public Sound100Controler() {
    }

    public int getBitRate() {
        return bitRate;
    }

    public String getPortName() {
        return portName;
    }

    public Switch100 getSwitch100() {
        return switch100;
    }

    /**
     * 发送端口命令
     *
     * @param command 输入字节组命令
     * @throws InterruptedException              抛出终端错误
     * @throws IOException                       抛出io错误
     * @throws UnsupportedCommOperationException 抛出不支持操作错误
     * @throws PortInUseException                抛出端口占用错误
     * @throws NoSuchPortException               抛出无此端口错误
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

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public void setSwitch100(Switch100 switch100) {
        this.switch100 = switch100;
    }
}
