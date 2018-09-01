package org.msdg.magic.util;

import java.lang.reflect.Array;
import java.time.*;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * 呵呵哒
 * Created by msdg on 2018/8/12.
 * Look, there is a moon.
 */
public class LittleTrick {

    public static boolean notEmpty(Object target) {
        return ! isEmpty(target);
    }

    public static boolean isEmpty(Object target) {
        if (null == target) return true;

        if (target instanceof String) {
            return 0 == ((String) target).length();
        }

        if (target instanceof Collection) {
            return 0  == ((Collection) target).size();
        }

        if (target instanceof Map) {
            return 0 == ((Map) target).size();
        }

        if (target.getClass().isArray()) {
            return 0 == Array.getLength(target);
        }

        return false;
    }

    public static LocalDate toLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime todayStart() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0));
    }

    public static LocalDateTime tomorrowStart() {
        return todayStart().plusDays(1);
    }
}