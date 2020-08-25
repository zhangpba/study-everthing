package com.study.java8.javascript;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Nashorn 一个 javascript 引擎。
 * 从JDK 1.8开始，Nashorn取代Rhino(JDK 1.6, JDK1.7)成为Java的嵌入式JavaScript引擎。
 * Nashorn完全支持ECMAScript 5.1规范以及一些扩展。它使用基于JSR 292的新语言特性，
 * 其中包含在JDK 7中引入的 invokedynamic，将JavaScript编译成Java字节码。
 * 与先前的Rhino实现相比，这带来了2到10倍的性能提
 */
public class JavaScriptTest {
    public static void main(String args[]) {

        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");

        String name = "Runoob";
        Integer result = null;

        try {
            nashorn.eval("print('" + name + "')");
            result = (Integer) nashorn.eval("10 + 2");

        } catch (ScriptException e) {
            System.out.println("执行脚本错误: " + e.getMessage());
        }

        System.out.println(result.toString());
    }
}

// JavaScript 中调用 Java
//    var BigDecimal = Java.type('java.math.BigDecimal');
//
//    function calculate(amount, percentage) {
//
//        var result = new BigDecimal(amount).multiply(
//                new BigDecimal(percentage)).divide(new BigDecimal("100"), 2, BigDecimal.ROUND_HALF_EVEN);
//
//        return result.toPlainString();
//    }
//
//    var result = calculate(568000000000000000023,13.9);
//    print(result);
