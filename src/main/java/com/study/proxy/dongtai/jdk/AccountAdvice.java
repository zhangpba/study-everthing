package com.study.proxy.dongtai.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zhangpba
 * @description TODO
 * @date 2022/10/13
 */
public class AccountAdvice implements InvocationHandler {

    private IAccountService target;

    public AccountAdvice(IAccountService target) {
        this.target = target;
    }

    /**
     * 代理方法, 每次调用目标方法时都会进到这里
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 可以模拟aop
        Object result = null;
        try {
            before();
            result = method.invoke(target, args);
            after();
        } catch (Exception e) {
            error();
        }
        return result;
    }

    /**
     * 前置增强
     */
    private void before() {
        System.out.println("前置增强...");
    }

    /**
     * 前置增强
     */
    private void after() {
        System.out.println("后置增强...");
    }

    /**
     * 前置增强
     */
    private void error() {
        System.out.println("异常增强.");
    }
}
