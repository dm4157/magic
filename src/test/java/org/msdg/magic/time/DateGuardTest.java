package org.msdg.magic.time;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 测试咯
 * Created by mw4157 on 2016/11/23.
 */
public class DateGuardTest {

    private Date date1;
    private LocalDate date2;
    private LocalDateTime dateTime;

    @Before
    public void init() {
        date1 = new Date();
        date2 = LocalDate.now();
        dateTime = LocalDateTime.now();
    }

    @Test
    public void testToday() {
        System.out.println(DateGuard.today());
        System.out.println(DateGuard.today(DateGuard.Format.date1));
        System.out.println(DateGuard.today(DateGuard.Format.date2));
        System.out.println(DateGuard.today(DateGuard.Format.datetime1));
    }

    @Test
    public void testFormat() {
        System.out.println(DateGuard.format(date1, DateGuard.Format.date1));
        System.out.println(DateGuard.format(date1, DateGuard.Format.date2));
        System.out.println(DateGuard.format(date1, DateGuard.Format.datetime1));
        System.out.println("============");
        System.out.println(DateGuard.format(date2, DateGuard.Format.date1));
        System.out.println(DateGuard.format(date2, DateGuard.Format.date2));
        System.out.println(DateGuard.format(date2, DateGuard.Format.datetime1));
        System.out.println("===========");
        System.out.println(DateGuard.format(dateTime, DateGuard.Format.date1));
        System.out.println(DateGuard.format(dateTime, DateGuard.Format.date2));
        System.out.println(DateGuard.format(dateTime, DateGuard.Format.datetime1));
    }

    @Test
    public void testMultiFormat() {
        long start = System.currentTimeMillis();
        for (int i=0; i<100000; i++) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateGuard.Format.datetime1.getValue());
            simpleDateFormat.format(date1);
        }
        long end = System.currentTimeMillis();
        System.out.printf("旧 100000次 每次new耗时：%dms %n", end-start);

        start = System.currentTimeMillis();
        for (int i=0; i<100000; i++) {
            DateGuard.format(date1, DateGuard.Format.datetime1);
        }
        end = System.currentTimeMillis();
        System.out.printf("旧 100000次 缓存后耗时：%dms %n", end-start);

        System.out.println("============");

        start = System.currentTimeMillis();
        for (int i=0; i<100000; i++) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DateGuard.Format.datetime1.getValue());
            dateTime.format(dateTimeFormatter);
        }
        end = System.currentTimeMillis();
        System.out.printf("新 100000次 每次new耗时：%dms %n", end-start);

        start = System.currentTimeMillis();
        for (int i=0; i<100000; i++) {
            DateGuard.format(dateTime, DateGuard.Format.datetime1);
        }
        end = System.currentTimeMillis();
        System.out.printf("新 100000次 缓存后耗时：%dms %n", end-start);
    }
}
