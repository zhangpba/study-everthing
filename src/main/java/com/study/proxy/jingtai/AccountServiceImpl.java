package com.study.proxy.jingtai;

/**
 * @author zhangpba
 * @description 被代理类
 * @date 2022/10/13
 */
public class AccountServiceImpl implements IAccountService{
    @Override
    public void transfer() {
        System.out.println("被代理类-调用dao层,完成转账主业务.");
    }
}
