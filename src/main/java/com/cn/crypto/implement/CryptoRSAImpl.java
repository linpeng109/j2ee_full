package com.cn.crypto.implement;

import com.cn.crypto.CryptoModule;
import org.apache.log4j.Logger;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

/**
 * @author Administrator
 */
public class CryptoRSAImpl implements CryptoModule {

    public static void main(String[] args) throws Exception {
        CryptoRSAImpl cryptoRSAImpl = new CryptoRSAImpl();
        String plainString = "测试试验2009年07月06日测试试验2009年07月06日测试试验2009年07月06日测试试验2009年07月06日测试试验2009年07月06日";
        CryptoBASE64Impl base64 = new CryptoBASE64Impl();
        CryptoHexImpl hex = new CryptoHexImpl();
        plainString = hex.encode(plainString);
        plainString = base64.encode(plainString);
        System.out.println(plainString);
        byte[] encryptString = cryptoRSAImpl.encode(plainString.getBytes());
        byte[] result = cryptoRSAImpl.decode(encryptString);
        System.out.println(result);
    }

    public Logger logger = Logger.getLogger(CryptoRSAImpl.class);

    public String algorithmName = "RSA/ECB/PKCS1Padding";

    /*
     * 使用私鈅解密
     *
     * @see com.cn.crypt.CryptoModule#decrypt(java.lang.String)
     */
    @Override
    public byte[] decode(byte[] srcBytes) throws Exception {
        Cipher cipher = initCipher(Cipher.DECRYPT_MODE, "private");
        byte[] resultBytes = cipher.doFinal(srcBytes);
        return resultBytes;
    }

    @Override
    public String decode(String src) throws Exception {
        return null;
    }

    /*
     * 使用公鈅加密
     *
     * @see com.cn.crypt.CryptoModule#encrypt(java.lang.String)
     */
    @Override
    public byte[] encode(byte[] srcBytes) throws Exception {
        Cipher cipher = initCipher(Cipher.ENCRYPT_MODE, "public");
        byte[] resultBytes = cipher.doFinal(srcBytes);
        return resultBytes;
    }

    @Override
    public String encode(String src) throws Exception {
        return null;
    }

    public Cipher initCipher(int mode, String keyType) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();
        Cipher cipher = Cipher.getInstance(algorithmName);
        if (keyType.toLowerCase().trim().equals("public")) {
            System.out.println("public");
            cipher.init(mode, keyPair.getPublic());

        } else if (keyType.toLowerCase().trim().equals("private")) {
            cipher.init(mode, keyPair.getPrivate());
        } else {
            throw new Exception("key mode setting error !");
        }
        return cipher;
    }
}
