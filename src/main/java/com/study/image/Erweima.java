package com.study.image;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;

/**
 * 生成二维码：https://blog.csdn.net/weixin_45816407/article/details/130539870
 *
 * @author zhangpba
 * @date 2023-09-12
 */
public class Erweima {

    public static void main(String[] args) {

        // 扫描二维码 直接进入百度
        QrCodeUtil.generate("https://www.baidu.com/", 300, 300, FileUtil.file("d:/qrcode.jpg"));

        // 微信不支持文字，可以用支付宝，淘宝，飞书等其他的APP扫码
        QrCodeUtil.generate("姓名：zhangpba", 300, 300, FileUtil.file("d:/qrcode1.jpg"));


        // 附带logo小图标
        QrCodeUtil.generate(//
                "https://www.baidu.com/", //二维码内容
                QrConfig.create().setImg("D:/测试图片.jpg"), //附带logo
                FileUtil.file("d:/qrcodeWithLogo.jpg")//写出到的文件
        );


        // 识别二维码:
        String decode = QrCodeUtil.decode(FileUtil.file("d:/qrcode.jpg"));
        System.out.println(decode);
        String qrcode1 = QrCodeUtil.decode(FileUtil.file("d:/qrcode1.jpg"));
        System.out.println(qrcode1);
        String qrcodeWithLogo = QrCodeUtil.decode(FileUtil.file("d:/qrcode1.jpg"));
        System.out.println(qrcodeWithLogo);
    }
}
