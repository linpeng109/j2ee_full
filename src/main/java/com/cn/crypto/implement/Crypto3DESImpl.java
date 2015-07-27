package com.cn.crypto.implement;

import com.cn.crypto.CryptoModule;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * @author admin
 */
public class Crypto3DESImpl implements CryptoModule {

    private Cipher cipher;

    private String keyString;

    /**
     * 加密算法名称("DESede/ECB/PKCS7Padding")
     */
    public String algorithmName;

    /*
     * (non-Javadoc)
     *
     * @see com.cn.crypto.CryptoModule#decode(byte[])
     */
    @Override
    public byte[] decode(byte[] srcBytes) throws Exception {
        cipher = initCipher(Cipher.DECRYPT_MODE);
        return cipher.doFinal(srcBytes);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.cn.crypto.CryptoModule#decode(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String decode(String src) throws Exception {
        byte[] srcBytes = src.getBytes();
        byte[] resultBytes = decode(srcBytes);
        return new String(resultBytes);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.cn.crypto.CryptoModule#encode(byte[])
     */
    @Override
    public byte[] encode(byte[] src) throws Exception {
        cipher = initCipher(Cipher.ENCRYPT_MODE);
        return cipher.doFinal(src);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.cn.crypto.CryptoModule#encode(java.lang.String,
     * java.lang.String)
     */
    @Override
    public String encode(String src) throws Exception {
        byte[] srcBytes = src.getBytes();
        byte[] resultBytes = encode(srcBytes);
        return new String(resultBytes);
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public String getKeyString() {
        return keyString;
    }

    /**
     * @param mode 加密模式或者解密模式
     * @return 加密或解密用的Cipher实例
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     */
    private final Cipher initCipher(int mode)
            throws UnsupportedEncodingException, NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException {
        Security.addProvider(new BouncyCastleProvider());
        // 必须24位，不足24位则补0，超过只取前24位
        byte[] keybytes = new byte[24];
        byte[] keydates = keyString.getBytes();
        int length = keydates.length;
        System.arraycopy(keydates, 0, keybytes, 0, length > 24 ? 24 : length);
        SecretKey desKey = new SecretKeySpec(keybytes, algorithmName);
        Cipher cipher = Cipher.getInstance(algorithmName);
        cipher.init(mode, desKey);
        return cipher;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setKeyString(String keyString) {
        this.keyString = keyString;
    }
}
