package com.study.grok;

import io.thekraken.grok.api.Grok;
import io.thekraken.grok.api.exception.GrokException;

/**
 * 要使用java-grok，首先需要通过定义好的正则表达式文件的路径创建Grok对象，我们可以定义为一个单列模式
 */
public class GrokInstance {
    private static Grok grok;

    private GrokInstance() {

    }

    public static Grok getGrokInstance(String grokPatternPath) {
        if (grok == null) {
            try {
                grok = Grok.create(grokPatternPath);
            } catch (GrokException e) {
                e.printStackTrace();
            }
        }
        return grok;
    }


}
