package com.study.color;

import com.study.color.org.fusesource.jansi.Ansi;

/**
 * @description 控制台输出彩色字符
 * @author zhangpba
 * @date 2020-06-24
 *
 * http://www.javased.com/index.php?source_dir=jansi/jansi/src/main/java/org/fusesource/jansi/AnsiString.java
 */
public class DuanWuTest {

    public static void main(String[] args) {
        String 龙 = "31069";
        String 舟 = "31471";
        String 节 = "21320";
        String 快 = "23433";
        String 乐 = "24247";

        System.out.print(Ansi.ansi().eraseScreen()
                .fg(Ansi.Color.CYAN).a((char) Integer.parseInt(龙)));
        System.out.print(Ansi.ansi().eraseScreen()
                .fg(Ansi.Color.MAGENTA).a((char) Integer.parseInt(舟)));
        System.out.print(Ansi.ansi().eraseScreen()
                .fg(Ansi.Color.RED).a((char) Integer.parseInt(节)));
        System.out.print(Ansi.ansi().eraseScreen()
                .fg(Ansi.Color.GREEN).a((char) Integer.parseInt(快)));
        System.out.print(Ansi.ansi().eraseScreen()
                .fg(Ansi.Color.YELLOW).a((char) Integer.parseInt(乐)));

    }
}
