package com.study.programming;

/**
 * 编程练习
 *
 * @author zhangpba
 * @date 2021-07-27
 */
public class Main20210727 {


    public static void main(String[] args) {
//        System.out.println(jump(7));

        String S = "ababab";
        String T = "abababab";

        kmp(S, T);
    }


    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
     * <p>
     * 青蛙最后一步
     * 最后一步条2级：前一步跳f(n-2)
     * 最后一步条1级：前一步跳f(n-1)
     *
     * @date 2021-07-27
     */
    public static int jump(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            return (jump(n - 1) + jump(n - 2));
        }
    }


    /**
     * 计算模板串S在文本串T中出现了多少次
     * <p>
     * 输入： "ababab","abababab"
     * 返回值： 2
     *
     * @param S string字符串 模板串
     * @param T string字符串 文本串
     * @return int整型
     */
    public static int kmp(String S, String T) {
        StringBuffer buffer = new StringBuffer();

        int sSize = S.length();
        int num = 0;
        for (int i = 0; i < T.length(); i++) {
            buffer.append(T.charAt(i));

            if (buffer.length() > sSize) {
                buffer.deleteCharAt(0);
            }

            // 去掉第一个字符
            if (buffer.toString().equals(S)) {
                num++;
            }
        }
        return num;
    }

}
