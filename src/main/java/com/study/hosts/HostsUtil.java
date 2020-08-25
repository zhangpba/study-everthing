package com.study.hosts;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class HostsUtil {
    public static void main(String[] args) {
        try {
            hostsValue();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("异常...");
        }
    }


    /**
     * @throws Exception
     * @描述 从hosts文件中读取配置信息
     * @date 2020-01-08
     * <p>
     * 1 在hosts中配置 127.0.0.1	 eureka9003.com
     * 2 利用下面代码获取
     */
    public static void hostsValue() throws Exception {
        String testURL = "zuul6001.com";
        System.out.println(InetAddress.getByName(testURL));

        InetAddress inetAddress = InetAddress.getByName(testURL);
        String hostName = inetAddress.getHostName();
        String hostAddress = inetAddress.getHostAddress();

        System.out.println("hostName = " + hostName);
        System.out.println("hostAddress = " + hostAddress);
    }


    /**
     * 获取本机ip
     *
     * @return
     * @throws SocketException
     */
    public static InetAddress getInetAddress() throws SocketException {
        Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress iphost = null;
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = allNetInterfaces.nextElement();
            Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                iphost = addresses.nextElement();
                if (iphost != null && iphost instanceof Inet4Address) {
                    System.out.println("本机的hostIp = " + iphost.getHostAddress());
                    System.out.println("本机hostname = " + iphost.getHostName());
                    return iphost;
                }
            }
        }
        return iphost;

    }
}
