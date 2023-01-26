package com.study.emial;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * @author zhangpba
 * @description 163邮件发送
 * @date 2022/11/23
 */
public class Email163 {
    public static void main(String[] args) throws Exception {
        sendTextEmail();
//        sendFileEmail();
    }

    /**
     * 文本邮件
     */
    public static void sendTextEmail() throws MessagingException {
        /*
         * 1.得到Session
         */
        Session session = getSession();

        /*
         * 2.创建MImeMessage
         */
        MimeMessage msg = new MimeMessage(session);
        // 设置发件人
        msg.setFrom(new InternetAddress("zhangpba@qq.com"));
//        msg.setFrom(new InternetAddress("zhangpba2022@163.com"));
        // 设置收件人
        msg.setRecipients(Message.RecipientType.TO, "answerzhang@tencent.com");

        // 设置主题（标题）
        msg.setSubject("这是一封myself学习Mail时的测试邮件");
        // 设置正文
        msg.setContent("这封邮件没有实质意义,就是测试用的", "text/html;charset=utf-8");

        /*
         * 3.发送
         */
        Transport.send(msg);
    }

    /**
     * 获取session
     * @return session
     */
    private static Session getSession() {
        Properties props = new Properties();
        // 设置服务器主机名
//        props.setProperty("mail.host", "smtp.163.com"); // 163邮箱
        props.setProperty("mail.host", "smtp.qq.com");  // qq邮箱
        // 是否需要认证
        props.setProperty("mail.smtp.auth", "true");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 这是你自己的邮箱域名和激活码
//                return new PasswordAuthentication("zhangpba2022", "TNEOASSTNCJNPOOO"); // 163邮箱
                return new PasswordAuthentication("zhangpba", "fhjpibgkvndmdhhf"); // qq邮箱
            }
        };

        return Session.getInstance(props, auth);
    }

    /**
     * 带附件的邮件
     *
     * @throws MessagingException
     */
    public static void sendFileEmail() throws MessagingException, IOException {
        /*
         * 1.得到Session
         */
        Session session = getSession();

        /*
         * 2.创建MImeMessage
         */
        MimeMessage msg = new MimeMessage(session);
        // 设置发件人
        msg.setFrom(new InternetAddress("zhangpba2022@163.com"));
        // 设置收件人
        msg.setRecipients(Message.RecipientType.TO, "zhangpba@qq.com");
        // 设置主题（标题）
        msg.setSubject("这是一封myself学习Mail时的测试邮件-有附件");

        /*
         *当发送包含附件的邮件时,邮件体就为多部件形式
         * 1.创建一个多部件邮件内容!MimeMultipart
         * MimeMultipart是一个集合,用来装载多部件
         * 2.我们需要创建两个主体部件,一个是文本内容,另一个是附件
         * 	主体叫MimeBodyPart
         * 3.把MimeMultiPart设置给MimeMessage的内容
         *
         */
        // 创建多部件主体
        MimeMultipart list = new MimeMultipart();
        // 创建MimeBodyPart
        MimeBodyPart part1 = new MimeBodyPart();
        // 设置主体部件内容
        part1.setContent("<h1>请查收图片！</h1> 这是一封包含附件的垃圾邮件", "text/html;charset=utf-8");
        // 把主体部件添加到集合中
        list.addBodyPart(part1);

        //创建MimeBodyPart
        MimeBodyPart part2 = new MimeBodyPart();
        part2.attachFile(new File("/Users/pengbozhang/ideaworkspace/study-everthing/src/main/java/com/study/emial/不同邮件服务器发送邮件.png"));
        //处理中文乱码问题--显示文件名称
        part2.setFileName(MimeUtility.encodeText("不同邮件服务器发送邮件.png"));
        list.addBodyPart(part2);

        // 把它设置给邮件作为邮件内容
        msg.setContent(list);

        /*
         * 3.发送
         */
        Transport.send(msg);
    }
}
