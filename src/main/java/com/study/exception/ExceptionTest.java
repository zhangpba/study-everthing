package com.study.exception;

/**
 * 2020-02-08
 */
public class ExceptionTest {

    public static void main(String[] args) {
        test();
    }

    // 轮询到自己想要的结果
    public static void test() {
        String str = null;
        for (int i = 0; i < 5; i++) {
            try {
                if (i != 3) {
                    int a = i / 0;
                }
                str = "大好河山";
                System.out.println("str=" + str);
                // 轮询到了自己想要的结果，剩下的循环不需要执行了
                break;
            } catch (Exception e) {
                System.out.println("异常=" + e.getMessage());
                continue;
            }
        }
        System.out.println("最终 str=" + str);
    }
}
