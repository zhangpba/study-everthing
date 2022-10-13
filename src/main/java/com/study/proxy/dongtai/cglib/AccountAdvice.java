package com.study.proxy.dongtai.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zhangpba
 * @description CGLIB动态代理：CGLIB动态代理的原理是生成目标类的子类, 这个子类对象就是代理对象, 代理对象是被增强过的.
 * 注意: 不管有没有接口都可以使用CGLIB动态代理, 而不是只有在无接口的情况下才能使用.
 * @date 2022/10/13
 */
public class AccountAdvice implements MethodInterceptor {

    /**
     * 代理方法, 每次调用目标方法时都会进到这里
     * @param obj
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        return methodProxy.invokeSuper(obj, objects);
        //        return method.invoke(obj, objects);  这种也行
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
}
