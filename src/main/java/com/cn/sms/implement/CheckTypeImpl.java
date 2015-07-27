package com.cn.sms.implement;

import com.cn.sms.CheckType;
import org.apache.log4j.Logger;

import java.util.List;

public class CheckTypeImpl implements CheckType {
    public List<String> mobileNumbers;
    public List<String> unicomNumbers;
    public List<String> telecomNumbers;
    public List<String> satcomNubmers;

    public Logger logger = Logger.getLogger(CheckTypeImpl.class);

    @Override
    public String check(String phone) {
        // 去掉前缀取得手机号码
        if (phone.startsWith("0") || phone.startsWith("+860")) {
            phone = phone.substring(phone.indexOf("1"), phone.length());
        }
        // 检测是否为移动号段
        if (mobileNumbers.contains(phone.substring(0, 3))
                || mobileNumbers.contains(phone.substring(0, 4))) {
            return "mobile";
        }
        // 检测是否为联通号段
        if (unicomNumbers.contains(phone.substring(0, 3))
                || unicomNumbers.contains(phone.substring(0, 4))) {
            return "unicom";
        }
        // 检测是否为电信号段
        if (telecomNumbers.contains(phone.substring(0, 3))
                || telecomNumbers.contains(phone.substring(0, 4))) {
            return "telecom";
        }
        // 检测是否为卫通号段
        if (satcomNubmers.contains(phone.substring(0, 3))
                || satcomNubmers.contains(phone.substring(0, 4))) {
            return "satcom";
        }
        return null;
    }

    public List<String> getMobileNumbers() {
        return mobileNumbers;
    }

    public List<String> getSatcomNubmers() {
        return satcomNubmers;
    }

    public List<String> getTelecomNumbers() {
        return telecomNumbers;
    }

    public List<String> getUnicomNumbers() {
        return unicomNumbers;
    }

    public void setMobileNumbers(List<String> mobileNumbers) {
        this.mobileNumbers = mobileNumbers;
    }

    public void setSatcomNubmers(List<String> satcomNubmers) {
        this.satcomNubmers = satcomNubmers;
    }

    public void setTelecomNumbers(List<String> telecomNumbers) {
        this.telecomNumbers = telecomNumbers;
    }

    public void setUnicomNumbers(List<String> unicomNumbers) {
        this.unicomNumbers = unicomNumbers;
    }

}
