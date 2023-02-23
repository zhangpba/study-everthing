package com.study.niuke.test20;

import java.util.Scanner;

/**
 * @author zhangpba
 * @description HJ2 计算某字符出现次数
 *
 * https://www.nowcoder.com/practice/a35ce98431874e3a820dbe4b2d0508b1?tpId=37&tags=&title=&difficulty=0&judgeStatus=0&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37
 * @date 2023/2/21
 */
public class Test02 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String info = scanner.nextLine();
        String zifu = scanner.nextLine();

        info = info.toLowerCase();
        zifu = zifu.toLowerCase();

        String replaceAll = info.replaceAll(zifu, "");
        int count = info.length() - replaceAll.length();

        System.out.println(count);

    }

}
