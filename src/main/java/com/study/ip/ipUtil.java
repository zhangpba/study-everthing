package com.study.ip;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ipUtil {


    public static long convertIPToLong(String ip) {
        InetAddress ipAddr = null;
        try {
            ipAddr = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        if (ipAddr == null)
            return 0L;
        byte bytes[] = ipAddr.getAddress();
        if (bytes.length < 4) {
            return 0L;
        } else {
            long z0 = bytes[0];
            long z1 = bytes[1];
            long z2 = bytes[2];
            long z3 = bytes[3];
            System.out.println("z0=" + z0);
            System.out.println("z1=" + z1);
            System.out.println("z2=" + z2);
            System.out.println("z3=" + z3);
            System.out.println("----------------");

            long l0 = bytes[0] & 255;
            long l1 = bytes[1] & 255;
            long l2 = bytes[2] & 255;
            long l3 = bytes[3] & 255;
            System.out.println("l0=" + l0);
            System.out.println("l1=" + l1);
            System.out.println("l2=" + l2);
            System.out.println("l3=" + l3);

            System.out.println("----------------");

            /**
             * ip = 10.91.130.189
             *
             * 10进制   二进制       左移后
             *
             * 10       1010        左移24位：1010000000000000000000000000  167772160
             * 91       1011011     左移16位：10110110000000000000000       5963776
             * 130      10000010    左移8位： 1000001000000000              33280
             * 189      10111101    不移动：  10111101                      189
             *
             * 返回的 ip = 167772160 + 5963776 + 33280 + 189 = 173769405
             *
             * (l0 << 24) + (l1 << 16) + (l2 << 8) + l3 等效 l0 << 24 | l1 << 16 | l2 << 8 | l3
             */
            System.out.println(l0 << 24);// 二进制的数字左移24位为
            System.out.println(l1 << 16);
            System.out.println(l2 << 8);
            System.out.println(l3);

            System.out.println("----------------");

            System.out.println((l0 << 24) + (l1 << 16) + (l2 << 8) + l3);
            return l0 << 24 | l1 << 16 | l2 << 8 | l3;
        }
    }

    /**
     * 利用正则表达式提取ip
     *
     * @param str
     */
    public static void getIp(String str) {
        str = "<133> 2019-10-31T15:43:25+08:00 10.37.149.169 csmp_transfer:";

        String reg = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);

        while (m.find()) {
            String ip = m.group();
            System.out.println(ip);
        }
    }

    public static void main(String[] args) {
        long ipLong = convertIPToLong("10.91.130.189");
        System.out.println(ipLong);
    }

}
