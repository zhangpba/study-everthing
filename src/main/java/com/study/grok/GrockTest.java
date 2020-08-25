package com.study.grok;

import java.util.Map;

public class GrockTest {

    /**
     * 创建我们的测试类，注意：上面的每次解析只能解析日志中的单行数据，当要解析一个日志文件的时候，我们需要逐行解析该文件，我们需要解析下面这一行日志：
     * 64.242.88.10 - - [07/Mar/2004:16:45:56 -0800] \"GET /twiki/bin/attach/Main/PostfixCommands HTTP/1.1\" 401 12846，通过程序解析后，我需要得到clientip =64.242.88.10，timestamp = 07/Mar/2004:16:45:56 -0800，verb=GET，httpversion = 1.1等这样格式化的数据，用我们上面的程序就可以做到。
     * <p>
     * 在patterns文件中定义好我们需要解析日志的正则表达式：
     * <p>
     * XINTEST %{IPORHOST:clientip} %{USER:ident;boolean} %{USER:auth}[%{HTTPDATE:timestamp;date;dd/MMM/yyyy:HH:mm:ss Z}\] \"(?:%{WORD:verb;string} %{NOTSPACE:request}(?: HTTP/%{NUMBER:httpversion;float})?|%{DATA:rawrequest})\" %{NUMBER:response;int} (?:%{NUMBER:bytes;long}|-)
     * <p>
     * 其中的IPORHOST是pattern中已经定义好的正则表达式，如下面所示，clientip是我们为解析后的数据的Key的别名，如果没有别名，默认名称为正则表达式的名称：
     * <p>
     * IP (?:%{IPV6:UNWANTED}|%{IPV4:UNWANTED})
     * HOSTNAME \b(?:[0-9A-Za-z][0-9A-Za-z-]{0,62})(?:\.(?:[0-9A-Za-z][0-9A-Za-z-]{0,62}))*(\.?|\b)
     * <p>
     * IPORHOST (?:%{HOSTNAME:UNWANTED}|%{IP:UNWANTED})
     * 我们的pattern表达式编写好了，名称为XINTEST，下面就可以使用我们的代码进行测试了，在测试的时候，pattern表达式需要使用%{}将名称放进去，这个是规定：
     * ---------------------
     * 作者：HarderXin
     * 来源：CSDN
     * 原文：https://blog.csdn.net/HarderXin/article/details/76838914
     * 版权声明：本文为博主原创文章，转载请附上博文链接！
     *
     * @param args
     */
    public static void main(String[] args) {
        String pattern = "%{XINTEST}";
        String message = "64.242.88.10 - - [07/Mar/2004:16:45:56 -0800] \"GET /twiki/bin/attach/Main/PostfixCommands HTTP/1.1\" 401 12846";
        String json = GrokUtils.toJson(pattern, message);
        System.out.println(json);
        Map<String, Object> map = GrokUtils.toMap(pattern, message);
        System.out.println(map.toString());

    }
}
