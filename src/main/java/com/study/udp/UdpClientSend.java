package com.study.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP发送客户端
 */
public class UdpClientSend {
    //    public static final String SERVER_HOSTNAME = "10.95.107.141";
    public static final String SERVER_HOSTNAME = "localhost";
    // 服务器端口
    public static final int SERVER_PORT = 514;
    // 本地发送端口
    public static final int LOCAL_PORT = 8888;

    public static void main(String[] args) {
        try {
            // 1，创建udp服务。通过DatagramSocket对象。
            DatagramSocket socket = new DatagramSocket(LOCAL_PORT);
            // 2，确定数据，并封装成数据包。DatagramPacket(byte[] buf, int length, InetAddress address, int port)
            String log = "devid=\"3\" dname=\"NSG\" serial=\"67526b3b9066c72f9cef30220ad4ac04e4fffbec\" module=\"syslog\" severity=\"info\" vsys=\"root-vsys\" type=\"operate\" time=\"1568774157\" user=\"admin\" from=\"WEB\" addr_src=\"172.19.3.155\" msg=\"set syslog send config, type:[operate], severity:[emerg], name:[日志采集服务器], status:[On]\" msg_cn=\"设置syslog外发配置，类型:[操作]，级别:[紧急]，名称:[日志采集服务器]， 状态:[开启]\"\n" +
                    "devid=\"3\" dname=\"NSG\" serial=\"1e5587d5464c9d3983f4b68fe23b667c6784bd1b\" module=\"restore\" severity=\"info\" vsys=\"root-vsys\" type=\"operate\" time=\"1568600812\" user=\"admin\" from=\"WEB\" addr_src=\"172.30.2.22\" msg=\"Save config\" msg_cn=\"保存配置\"\n" +
                    "devid=\"3\" dname=\"NSG\" serial=\"2a40089017c4f670c4921724fb19c60b9f46f148\" module=\"policy\" severity=\"info\" vsys=\"root-vsys\" type=\"operate\" time=\"1568602703\" user=\"admin\" from=\"WEB\" addr_src=\"172.30.2.22\" msg=\"Edit security polciy, name:[安全设备策略], number of source ip member:[1], number of destination ip member:[1], number of service member:[1], number of application object:[1], number of source user:[0], status:[enable], action:[deny]\" msg_cn=\"编辑安全策略，名称：[安全设备策略]，源地址成员个数：[1]，目的地址成员个数：[1]，服务成员个数：[1]，应用对象个数：[1]， 源用户成员数：[0]，状态：[启用]，动作：[拒绝]\"\n" +
                    "devid=\"3\" dname=\"NSG\" serial=\"2a40089017c4f670c4921724fb19c60b9f46f148\" module=\"policy\" severity=\"info\" vsys=\"root-vsys\" type=\"operate\" time=\"1568602703\" user=\"admin\" from=\"WEB\" addr_src=\"172.30.2.22\" msg=\"Edit long access of security policy, name:[安全设备策略], long access status:[disable], long access name:[]\" msg_cn=\"编辑安全策略长连接状态，策略名称：[安全设备策略]，长连接状态：[disable], 长连接名称：[]\"\n" +
                    "devid=\"3\" dname=\"NSG\" serial=\"2a40089017c4f670c4921724fb19c60b9f46f148\" module=\"policy\" severity=\"info\" vsys=\"root-vsys\" type=\"operate\" time=\"1568602685\" user=\"admin\" from=\"WEB\" addr_src=\"172.30.2.22\" msg=\"Move security policy after target, name:[安全设备策略], target:[可信主机策略]\" msg_cn=\"移动安全策略到目标之后，名称：[安全设备策略]， 目标名称：[可信主机策略]\"\n" +
                    "devid=\"3\" dname=\"NSG\" serial=\"2a40089017c4f670c4921724fb19c60b9f46f148\" module=\"policy\" severity=\"info\" vsys=\"root-vsys\" type=\"operate\" time=\"1568602666\" user=\"admin\" from=\"WEB\" addr_src=\"172.30.2.22\" msg=\"Move security policy top, name:[可信主机策略]\" msg_cn=\"移动安全策略到最前，名称：[可信主机策略]\"\n" +
                    "devid=\"3\" dname=\"NSG\" serial=\"2a40089017c4f670c4921724fb19c60b9f46f148\" module=\"policy\" severity=\"info\" vsys=\"root-vsys\" type=\"operate\" time=\"1568602651\" user=\"admin\" from=\"WEB\" addr_src=\"172.30.2.22\" msg=\"Add destination zone  of security policy, name:[安全设备策略], zone name:[any]\" msg_cn=\"添加安全策略目的安全域， 策略名称：[安全设备策略]， 安全域：[any]\"\n" +
                    "devid=\"3\" dname=\"NSG\" serial=\"2a40089017c4f670c4921724fb19c60b9f46f148\" module=\"policy\" severity=\"info\" vsys=\"root-vsys\" type=\"operate\" time=\"1568602651\" user=\"admin\" from=\"WEB\" addr_src=\"172.30.2.22\" msg=\"Add from tunnel of security policy, name:[安全设备策略], from tunnel name:[]\" msg_cn=\"添加安全策略来自隧道， 策略名称：[安全设备策略]， 来自隧道：[]\"\n" +
                    "devid=\"3\" dname=\"NSG\" serial=\"2a40089017c4f670c4921724fb19c60b9f46f148\" module=\"address-obj\" severity=\"info\" vsys=\"root-vsys\" type=\"operate\" time=\"1568602439\" user=\"admin\" from=\"WEB\" addr_src=\"172.30.2.22\" msg=\"Add object address, name:[朱宇]\" msg_cn=\"添加地址对象，名称：[朱宇]\"\n" +
                    "devid=\"3\" dname=\"NSG\" serial=\"2a40089017c4f670c4921724fb19c60b9f46f148\" module=\"address-obj\" severity=\"info\" vsys=\"root-vsys\" type=\"operate\" time=\"1568602439\" user=\"admin\" from=\"WEB\" addr_src=\"172.30.2.22\" msg=\"Add object address host item, name:[朱宇], type:[include], host:[172.30.98.109]\" msg_cn=\"添加地址对象主机条目，名称：[朱宇]，类型：[包含]，主机：[172.30.98.109]\"\n" +
                    "devid=\"3\" dname=\"NSG\" serial=\"2a40089017c4f670c4921724fb19c60b9f46f148\" module=\"address-obj\" severity=\"info\" vsys=\"root-vsys\" type=\"operate\" time=\"1568602471\" user=\"admin\" from=\"WEB\" addr_src=\"172.30.2.22\" msg=\"Edit object address group, name:[可信主机], new name:[可信主机]\" msg_cn=\"编辑地址组对象，名称：[可信主机]，新名称：[可信主机]\"\n";

            byte[] buf = log.getBytes();
            DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName(SERVER_HOSTNAME),
                    SERVER_PORT);
            // 3，通过socket服务，将已有的数据包发送出去。通过send方法。
            socket.send(dp);
            // 4，关闭资源。
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
