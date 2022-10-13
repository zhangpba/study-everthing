package com.study.proxy.dongtai.jdk;

/**
 * @author zhangpba
 * @description TODO
 * @date 2022/10/13
 */
public class AccountServiceImpl implements IAccountService{
    @Override
    public void transfer() {
        System.out.println("调用dao层,完成转账主业务.");
    }


    @Override
    public void test() {
        System.out.println("第二个方法调用test......");
    }
}
