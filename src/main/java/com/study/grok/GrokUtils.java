package com.study.grok;

import io.thekraken.grok.api.Grok;
import io.thekraken.grok.api.Match;
import io.thekraken.grok.api.exception.GrokException;

import java.util.Map;

/**
 * 辅助类
 * 得到Match对象后，我们就可以将数据转换为对应的Map或者Json数据了，我写了一个辅助类：
 */
public class GrokUtils {

    private static final String GROK_PATTERN_PATH = "conf/agent_patterns";

    private static Grok grok = GrokInstance.getGrokInstance(GROK_PATTERN_PATH);

    public static Map<String, Object> toMap(String pattern, String message) {
        Match match = getMatch(pattern, message);
        if (match != null) {
            return match.toMap();
        }
        return null;
    }

    public static String toJson(String pattern, String message) {
        Match match = getMatch(pattern, message);
        if (match != null) {
            return match.toJson();
        }
        return null;
    }

    /**
     * 获取到Grok对象后，通过传入我们需要解析的日志的表达式名称和要转换的日志消息，来创建Match对象：
     *
     * @param pattern 解析的日志的表达式名称
     * @param message 要转换的日志消息
     * @return
     */
    private static Match getMatch(String pattern, String message) {
        Match match = null;
        try {
            grok.compile(pattern);
            match = grok.match(message);
            match.captures();
        } catch (GrokException e) {
            e.printStackTrace();
            match = null;
        }
        return match;
    }


}
