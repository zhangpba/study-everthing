package com.study.proxy.dongtai.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author zhangpba
 * @description lib动态代理测试类
 * https://blog.csdn.net/litianxiang_kaola/article/details/85335700
 * 静态代理：静态代理会为每一个业务增强都提供一个代理类, 由代理类来创建代理对象.
 * 动态代理：动态代理并不存在代理类, 代理对象直接由代理生成工具动态生成.
 * <p>
 * 动态代理的有两种实现：
 * JDK动态代理：是使用 java.lang.reflect 包下的代理类来实现. JDK动态代理动态代理必须要有接口.
 * CGLIB动态代理：的原理是生成目标类的子类, 这个子类对象就是代理对象, 代理对象是被增强过的.
 * @date 2022/10/13
 */
public class Client {

    public static void main(String[] args) {
        // 创建目标对象
        AccountService accountService = new AccountService();

        // 创建代理对象
        AccountService proxy = (AccountService) Enhancer.create(accountService.getClass(), new AccountAdvice());

        // 执行方法
        proxy.transfer();

        // 通过执行结果可以看出，每次通过代理调用目标对象的方法，都会先执行before()方法
        proxy.test();
    }
}
