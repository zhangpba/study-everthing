package com.study.proxy.dongtai.jdk;

import java.lang.reflect.Proxy;

/**
 * @author zhangpba
 * @description 测试动态代理：JDK动态代理
 * @date 2022/10/13
 */
public class Client {
    public static void main(String[] args) {
        // 创建代理目标
        IAccountService target = new AccountServiceImpl();
        // 创建代理类
        IAccountService proxy = (IAccountService)Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new AccountAdvice(target));

        // 调用
        proxy.transfer();

        // 调用第二个方法
        proxy.test();
    }
}
