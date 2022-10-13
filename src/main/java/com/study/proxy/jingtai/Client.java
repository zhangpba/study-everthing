package com.study.proxy.jingtai;

/**
 * @author zhangpba
 * @description 测试动态代理：lCGLIB动态代理
 * @date 2022/10/13
 */
public class Client {
    public static void main(String[] args) {
        // 创建被代理对象
        IAccountService target = new AccountServiceImpl();
        // 创建代理对象
        IAccountService proxy = new AccountProxy(target);
        // 调用
        proxy.transfer();
    }
}
