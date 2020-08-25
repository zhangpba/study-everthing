package com.study.factory.abstractFactory;

public class SenderSmsFactory implements Provider {

    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
