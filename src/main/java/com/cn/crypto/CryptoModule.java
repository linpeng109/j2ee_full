package com.cn.crypto;

/**
 * 加密/解密模块接口
 *
 * @author Hartwell
 */
public interface CryptoModule {

    /**
     * @param srcBytes 原byte组
     * @return 解密后的byte组
     * @throws Exception 异常
     */
    public byte[] decode(byte[] srcBytes) throws Exception;

    /**
     * @param src 待解密字符串
     * @return 解密后的字符串
     * @throws Exception 异常
     */
    public String decode(String src) throws Exception;

    /**
     * @param srcBytes 原byte组
     * @return 加密后的byte组
     * @throws Exception 异常
     */
    public byte[] encode(byte[] srcBytes) throws Exception;

    /**
     * @param src 原字符串
     * @return 加密后的字符串
     * @throws Exception 异常
     */
    public String encode(String src) throws Exception;
}
