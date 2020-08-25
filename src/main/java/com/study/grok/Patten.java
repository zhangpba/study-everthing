package com.study.grok;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式提取字符串
 */
public class Patten {
    public static void main(String[] args) {

        String pluginFileName = "test-asset-asset-classify-GA-5.1.1-SNAPSHOT.jar";
//        Pattern pattern = Pattern.compile("^test-(\\S+)-(\\d.*)\\.jar$");
//        Pattern p = Pattern.compile("^test-(\\S+)-(GA-\\d.*)\\.jar$");
//        Matcher m = p.matcher(pluginFileName);
//        if (m.find()) {
//            String group = m.group();
//            String group1 = m.group(1);
//            String group2 = m.group(2);
//
//            System.out.println("group= " + group);
//            System.out.println("group1= " + group1);
//            System.out.println("group2= " + group2);
//        }

        String value = "m\n" +
                "10.91.130.141\"syslog(����.2?\n" +
                "\n" +
                "\n" +
                "port514\n" +
                "\n" +
                "10.91.130.141\n" +
                "10.95.107.189�devid=\"3\" dname=\"ISD\" serial=\"93b5b3cffc8ec4b44304f2b0dae50d6c74cc8c78\" module=\"ips\" severity=\"emerg\" vsys=\"root-vsys\" type=\"threat\" session_id=\"220182\" time=\"1586418051\" addr_src=\"1.1.1.7\" addr_dst=\"2.2.2.12\" port_src=\"55508\" port_dst=\"21\" appname=\"FTP\" proto=\"TCP\" session_time=\"27\" sess_nth=\"0\" sess_dev_id=\"0\" locale_src=\"澳大利亚\" locale_dst=\"法国\" app_category=\"APP_NETWORK\" app_risk=\"5\" focus_type=\"NO\" profile=\"\" non_standard_port=\"NO\" direction=\"C2S\" attacker_ip=\"1.1.1.7\" victim_ip=\"2.2.2.12\" attack_name=\"\" victim_name=\"\" sample_name=\"\" threat_name=\"Brute Force Attack\" threat_id=\"80036\" threat_severity=\"medium\" hit_num=\"1\" filename=\"\" url=\"\" dns_domain=\"\" detail=\"\" detail_zh=\"\" sample_type=\"\" sample_md5=\"\" threat_class=\"scan\" detect_method=\"\" malicious_type=\"\" ioc_id=\"\" disposal_id=\"\" confidence=\"\" threat_type=\"vulner\" last_hit_num=\"0\" filepath=\"malicious_scanning_detection\"\n";
        Pattern pattern = Pattern.compile("^\\d+\\.\\d+\\.\\d+\\.\\d+.(.*)");
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            String group = matcher.group();
            String group1 = matcher.group(1);
            String group2 = matcher.group(2);

            System.out.println("group= " + group);
            System.out.println("group1= " + group1);
            System.out.println("group2= " + group2);
        }

        String a = "�";
//        String lastValue = value.replaceAll("\n?\n", "");
        String syslog = null;
        String ip = null;
        if (value.contains(a)) {
            String[] values = value.split(a);
            int i = 0;
            for (String vul : values) {
                i = i + 1;
                System.out.println("第" + i + "个：" + vul);
                if (i == 5) {
                    String[] ips = vul.split("\\n?\\n");
                    int j = 0;
                    for (String s : ips) {
                        j = j + 1;
                        System.out.println("第二层，第" + j + "个：" + s);
                        if (j == 5) {
                            ip = s;
                        }
                    }
                }
                if (i == 6) {
                    syslog = vul;
                }
            }

            System.out.println("ip=" + ip);
            System.out.println("syslog=" + syslog);
        }

    }
}
