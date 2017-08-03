package org.msdg.magic.text;

import org.apache.commons.lang3.StringUtils;

/**
 * 关于文本的一些工具
 * Created by mw4157 on 2016/11/28.
 */
public class TextUtils {

    /**
     * 判断一个字符串是不是数字
     * @param str       字符串
     * @return          true 是数字； false 不是数字
     */
    public static boolean isNumeric(String str) {
        for (int i=str.length(); --i >= 0;) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * 将数组字符串变为数组
     */
    public static String[] convertStrToArray(String arrayStr) {
        // 入参为空
        // 或者入参是 "[]"
        // 直接返回空数组
        if (StringUtils.isBlank(arrayStr) || arrayStr.length() <= 2) {
            return new String[] {};
        }

        return arrayStr.substring(1, arrayStr.length()-1).split(",");
    }
}
