package com.study.system;

public class SystemParam {
    public static void main(String[] args) {

        System.getProperty("line.separator");    //这也是换行符,功能和"\n"是一致的,但是此种写法屏蔽了 Windows和Linux的区别 ，更保险一些.

        System.out.println("java版本号：" + System.getProperty("java.version"));
        System.out.println("Java提供商名称：" + System.getProperty("java.vendor"));
        System.out.println("Java提供商网站：" + System.getProperty("java.vendor.url"));
        System.out.println("jre目录：" + System.getProperty("java.home"));
        System.out.println("Java虚拟机规范版本号：" + System.getProperty("java.vm.specification.version"));
        System.out.println("Java虚拟机规范提供商：" + System.getProperty("java.vm.specification.vendor"));
        System.out.println("Java虚拟机规范名称：" + System.getProperty("java.vm.specification.name"));
        System.out.println("Java虚拟机版本号：" + System.getProperty("java.vm.version"));
        System.out.println("Java虚拟机提供商：" + System.getProperty("java.vm.vendor"));
        System.out.println("Java虚拟机名称：" + System.getProperty("java.vm.name"));
        System.out.println("Java规范版本号：" + System.getProperty("java.specification.version"));
        System.out.println("Java规范提供商：" + System.getProperty("java.specification.vendor"));
        System.out.println("Java规范名称：" + System.getProperty("java.specification.name"));
        System.out.println("Java类版本号：" + System.getProperty("java.class.version"));
        System.out.println("Java类路径：" + System.getProperty("java.class.path"));
        System.out.println("Java lib路径：" + System.getProperty("java.library.path"));
        System.out.println("Java输入输出临时路径：" + System.getProperty("java.io.tmpdir"));
        System.out.println("Java编译器：" + System.getProperty("java.compiler"));
        System.out.println("Java执行路径：" + System.getProperty("java.ext.dirs"));
        System.out.println("操作系统名称：" + System.getProperty("os.name"));
        System.out.println("操作系统的架构：" + System.getProperty("os.arch"));
        System.out.println("操作系统版本号：" + System.getProperty("os.version"));
        System.out.println("文件分隔符：" + System.getProperty("file.separator")); // 路径分隔符号 "/"
        System.out.println("路径分隔符：" + System.getProperty("path.separator")); // 分号 ";"
        System.out.println("直线分隔符：" + System.getProperty("line.separator")); // 换行符 "\n"
        System.out.println("操作系统用户名：" + System.getProperty("user.name"));
        System.out.println("操作系统用户的主目录：" + System.getProperty("user.home"));
        System.out.println("当前程序所在目录：" + System.getProperty("user.dir"));



        getTopic("");
    }

    public static String getTopic(String value){

        value = "m\n" +
                "10.91.130.141\"syslog(����.2?\n" +
                "\n" +
                "\n" +
                "port514\n" +
                "\n" +
                "10.91.130.141\n" +
                "10.95.107.189�devid=\"3\" dname=\"ISD\" serial=\"93b5b3cffc8ec4b44304f2b0dae50d6c74cc8c78\" module=\"ips\" severity=\"emerg\" vsys=\"root-vsys\" type=\"threat\" session_id=\"220182\" time=\"1586418051\" addr_src=\"1.1.1.7\" addr_dst=\"2.2.2.12\" port_src=\"55508\" port_dst=\"21\" appname=\"FTP\" proto=\"TCP\" session_time=\"27\" sess_nth=\"0\" sess_dev_id=\"0\" locale_src=\"澳大利亚\" locale_dst=\"法国\" app_category=\"APP_NETWORK\" app_risk=\"5\" focus_type=\"NO\" profile=\"\" non_standard_port=\"NO\" direction=\"C2S\" attacker_ip=\"1.1.1.7\" victim_ip=\"2.2.2.12\" attack_name=\"\" victim_name=\"\" sample_name=\"\" threat_name=\"Brute Force Attack\" threat_id=\"80036\" threat_severity=\"medium\" hit_num=\"1\" filename=\"\" url=\"\" dns_domain=\"\" detail=\"\" detail_zh=\"\" sample_type=\"\" sample_md5=\"\" threat_class=\"scan\" detect_method=\"\" malicious_type=\"\" ioc_id=\"\" disposal_id=\"\" confidence=\"\" threat_type=\"vulner\" last_hit_num=\"0\" filepath=\"malicious_scanning_detection\"\n";
        String LINE_BREAK = "\r?\n";

        String noBrankValue = value.replaceAll(LINE_BREAK,"");


        System.out.println(noBrankValue);
        return "noBrankValue";
    }


}
