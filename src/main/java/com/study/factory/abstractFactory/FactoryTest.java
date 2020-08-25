package com.study.factory.abstractFactory;

/**
 * 抽象工厂模式测试
 */
public class FactoryTest {

    public static void main(String[] args) {
        Provider provider = new SenderMailFactory();
        Sender sender = provider.produce();
        sender.send();
    }
}
