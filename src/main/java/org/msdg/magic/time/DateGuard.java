package org.msdg.magic.time;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 时间岗哨
 * Created by mw4157 on 2016/11/23.
 */
public class DateGuard {

    /**
     * 时间格式
     */
    public enum Format {
        date1("yyyy年MM月dd日"),
        date2("yyyy-MM-dd"),
        datetime1("yyyy-MM-dd HH:mm:ss");

        String value;
        Format(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 旧时代日期格式化对象缓存
     */
    private static Map<Format, SimpleDateFormat> oldMap = new HashMap<>();
    /**
     * 新时代日期格式化对象缓存
     */
    private static Map<Format, DateTimeFormatter> newMap = new HashMap<>();

    /**
     * 今天
     * @return      20161123
     */
    public static String today() {
        return LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE);
    }

    /**
     * 带格式的今天
     * @param format    格式
     */
    public static String today(Format format) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format.getValue()));
    }

    /**
     * 格式化时间
     * @param date      时间
     * @param format    格式
     */
    public static String format(Date date, Format format) {
        return takeOldFormat(format).format(date);
    }
    public static String format(LocalDate date, Format format) {
        if (format.name().startsWith("datetime")) {
            return "错误：日期类型不支持时间格式，尝试使用LocalDateTime";
        }
        return date.format(takeNewFormat(format));
    }
    public static String format(LocalDateTime dateTime, Format format) {
        return dateTime.format(takeNewFormat(format));
    }

    /**
     * 从缓存中获得格式化对象
     */
    private static SimpleDateFormat takeOldFormat(Format format) {
        oldMap.putIfAbsent(format, new SimpleDateFormat(format.getValue()));
        return oldMap.get(format);
    }
    private static DateTimeFormatter takeNewFormat(Format format) {
        newMap.putIfAbsent(format, DateTimeFormatter.ofPattern(format.getValue()));
        return newMap.get(format);
    }

}
