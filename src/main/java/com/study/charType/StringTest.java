package com.study.charType;

public class StringTest {

    // 换行符
    private static final String LINE_BREAK = "\r?\n";

    public static void one() {
        String responseStr = "72851492864\n74249121792\n77690839040\n           ";
        System.out.println(responseStr);
        String response = responseStr.replace(" ", "");
        String[] esData = response.split(LINE_BREAK);
        for (int i = 0; i < esData.length; i++) {
            System.out.println("输出：" + esData[i]);
        }

        System.out.println("结束");
    }

    public static void two() {
        String str = "123456TBabcdefTB78910TBghijklmn";
        // 截取从0开始到第一个TB的所有字符串
        String sub1 = str.substring(0, str.indexOf("TB"));
        System.out.println("sub1=" + sub1);

        // 截取从0开始到最后一个TB的所有字符串
        String sub2 = str.substring(0, str.lastIndexOf("TB"));
        System.out.println("sub2=" + sub2);

        // 截取从0开始到第n个TB的所有字符串
        int index = str.indexOf("TB", 1);
        System.out.println("index=" + index);
        String sub3 = str.substring(0, index);
        System.out.println("sub3=" + sub3);
    }

    public static void main(String[] args) {

//        one();
//        two();
        three();
    }


    /**
     * 测试String最大可以定义65534个字符
     */
    public static void three(){
        // 构建一个长度为65534的字符串
        String bigString = "";
        int i = 0;
        boolean flag = true;
        while (flag) {
            i++;
            bigString += "a";
            if (i == 65534) {
                flag = false;
            }
        }

        System.out.println("构造的字符串长度" + bigString.length());
        System.out.println(bigString);

        // 常量字符串(1个b+ 65534个a = 65535个字符)
        String chaochang = "baaaaaaaa";
        System.out.println(chaochang);
    }


}
