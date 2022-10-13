package com.study.proxy.jingtai;

/**
 * @author zhangpba
 * @description 代理类
 * @date 2022/10/13
 */
public class AccountProxy implements IAccountService{

    private IAccountService target;

    public AccountProxy(IAccountService target) {
        this.target = target;
    }

    @Override
    public void transfer() {
        before();
        target.transfer();
    }

    /**
     * 前置增强
     */
    private void before() {
        System.out.println("对转账人身份进行验证.");
    }
}
