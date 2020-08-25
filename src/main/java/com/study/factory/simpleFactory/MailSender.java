package com.study.factory.simpleFactory;

public class MailSender implements Sender {
    @Override
    public void send() {
        System.out.println("this is mailSender!");
    }
}
