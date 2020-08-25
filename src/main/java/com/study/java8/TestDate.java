package com.study.java8;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 输出从今天起前30天的所有日期
 */
public class TestDate {

    public static void main(String[] args) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String minDateStr = "";
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 30; i++) {
            calendar.setTime(date);
            calendar.add(calendar.DATE, -i);
            Date minDate = calendar.getTime();
            minDateStr = sdf.format(minDate);
            System.out.println(i + ":minDateStr:" + minDateStr);
        }

    }
}
