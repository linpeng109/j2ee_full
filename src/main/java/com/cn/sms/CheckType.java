package com.cn.sms;

public interface CheckType {
    public final static String mobile = "mobile";
    public final static String unicom = "unicom";
    public final static String telecom = "telecom";
    public final static String satcom = "satcom";

    public String check(String phone);
}
