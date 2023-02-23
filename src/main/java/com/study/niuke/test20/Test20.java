package com.study.niuke.test20;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author zhangpba
 * @description J20 密码验证合格程序
 * @date 2023/2/23
 */
public class Test20 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String info = scanner.nextLine();

            String result = "";
            if (info.length() < 8) {
                result = "NG";
                System.out.println(result);
                System.exit(1);
            }

            if (isHaveA(info) && ishaveNum(info) && getString(info, 0, 3)) {
                result = "OK";
            } else {
                result = "NG";
            }

//            if (getString(info, 0, 3)) {
//                result = "NG";
//            } else {
//                result = "OK";
//            }
            System.out.println(result);
        }

    }

    // 是否有字母
    private static boolean isHaveA(String info) {
        boolean have = false;
        for (int i = 0; i < info.length(); i++) {
            if (!('A' < info.charAt(i) && info.charAt(i) < 'z')) {
                have = true;
                break;
            }
        }
        return have;
    }

    // 是否有连续字符
    private static boolean isRe(String info) {
        boolean isHave = true;
        for (int i = 0; i < info.length() - 2; i++) {
            if (info.substring(i, i + 1).equals(info.substring(i + 1, i + 2))) {
                isHave = false;
            }
        }
        return isHave;
    }

    // todo 校验是否有重复子串
    // 不能有长度大于2的不含公共元素的子串重复 （注：其他符号不含空格或换行）
    private static boolean getString(String str, int l, int r) {
        if (r >= str.length()) {
            return false;
        }
        // 头尾校验，从头部开始 依次 按照3位长度截取出字符串与剩与长度的字符串进行校验，如果剩余中包含截取的3位字符串则表示出现了重复
        if (str.substring(r).contains(str.substring(l, r))) {
            return true;
        } else {
            return getString(str, l + 1, r + 1);
        }
    }

    // 是否有数字
    private static boolean ishaveNum(String info) {
        boolean ishaveNum = false;
        List<String> list = new ArrayList<String>();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        if (info.contains("0") || info.contains("1") || info.contains("2") || info.contains("3") || info.contains("4") || info.contains("5") || info.contains("6") || info.contains("7") || info.contains("8") || info.contains("9")) {
            ishaveNum = true;
        }
        return ishaveNum;
    }
}
