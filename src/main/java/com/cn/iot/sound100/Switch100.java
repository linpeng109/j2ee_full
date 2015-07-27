package com.cn.iot.sound100;

/**
 * 音频控制通讯协议
 *
 * @author admin
 */
public class Switch100 {

    public static byte[] SELECT_INPUT_1 = {(byte) 0xF8, (byte) 0x00,
            (byte) 0x55, (byte) 0x55, (byte) 0x01, (byte) 0x00, (byte) 0x23};

    public static byte[] SELECT_OUTPUT_1 = {(byte) 0xF8, (byte) 0x01,
            (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x7A};

    public Switch100() {
    }

}
