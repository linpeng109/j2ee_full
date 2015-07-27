package com.cn.crypto.implement;

import com.cn.crypto.CryptoModule;
import org.bouncycastle.util.encoders.Hex;

public class CryptoHexImpl implements CryptoModule {

    @Override
    public byte[] decode(byte[] srcBytes) throws Exception {
        byte[] resultBytes = Hex.decode(srcBytes);
        return resultBytes;
    }

    @Override
    public String decode(String src) throws Exception {
        byte[] srcBytes = src.getBytes();
        byte[] resultBytes = decode(srcBytes);
        return new String(resultBytes);
    }

    @Override
    public byte[] encode(byte[] srcBytes) throws Exception {
        byte[] resultBytes = Hex.encode(srcBytes);
        return resultBytes;
    }

    @Override
    public String encode(String src) throws Exception {
        byte[] srcBytes = src.getBytes();
        byte[] resultBytes = encode(srcBytes);
        return new String(resultBytes);
    }

}
