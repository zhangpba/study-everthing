package com.study.udp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * UDP接收端:监听514端口
 */
public class UdpClientReceive {
    // 服务器端口
    public static final int SERVER_PORT = 514;

    // 数组长度为1024:最多可以存1024个字节的东西，如果超过这个值就会报溢出的异常了
    private static final int bufferSize = 1024;

    public static void main(String[] args) throws SocketException {

        DatagramSocket socket = new DatagramSocket(SERVER_PORT);
        while (true) {
            byte[] buffer = new byte[bufferSize];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(packet);
                processPacket(packet);
            } catch (IOException e) {
                System.err.println("异常...");
            }
        }
    }

    /**
     * 处理packet
     *
     * @param packet
     */
    public static void processPacket(DatagramPacket packet) {
        byte[] buffer = packet.getData();
        try {
            String content = new String(buffer, "UTF-8").trim();
            System.out.println(content);
        } catch (UnsupportedEncodingException e) {
            System.err.println("异常 " + e.getMessage());
        }
    }
}
