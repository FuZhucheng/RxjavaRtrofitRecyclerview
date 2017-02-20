package com.fuzhucheng.rxjavartrofitrecyclerview.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 符柱成 on 2017/1/12.
 */

public class ReUtil {
    public static boolean isPhone(String phone) {
        Pattern pattern = Pattern.compile("^1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\d{8}$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
