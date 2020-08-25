package com.study.factory.simpleFactory;

/**
 * 工厂模式测试
 */
public class FactoryTest {

    public static void main(String[] args) {

        SenderFactory factory = new SenderFactory();

        // 测试普通工厂模式
        Sender sender = factory.produce("sms");
        sender.send();

        // 测试多工厂模式
        Sender manaySender = factory.produceMail();
        manaySender.send();

        // 静态工厂模式测试
        Sender staticSender =  SenderFactory.staticProduceMail();
        staticSender.send();
    }
}
