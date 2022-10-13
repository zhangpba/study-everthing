package com.study.proxy.dongtai.cglib;

/**
 * @author zhangpba
 * @description 转账业务
 * @date 2022/10/13
 */
public class AccountService {

    public void transfer() {
        System.out.println("调用dao层,完成转账主业务.");
    }

    public void test() {
        System.out.println("第二个方法调用test......");
    }
}
