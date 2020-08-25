package com.study.factory.abstractFactory;

public class SenderMailFactory implements Provider {

    @Override
    public Sender produce() {
        return new MailSender();
    }
}
