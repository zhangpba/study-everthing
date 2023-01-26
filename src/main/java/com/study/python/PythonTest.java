package com.study.python;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhangpba
 * @description JAVA调用python
 * @date 2022/9/26
 *
 * http://www.manongjc.com/detail/28-cusikijzlwzpzqy.html
 */
public class PythonTest {
    public static void main(String[] args) {
        // test1();

        // test2();

        // test3();

        test4();
    }

    /**
     * java类中执行python语句
     */
    private static void test1() {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("a=[5,3,2,9,4,0];");
        interpreter.exec("print(sorted(a));");  // 此处python语句是3.x版本的语法
        interpreter.exec("print sorted(a);");   // 此处是python语句是2.x版本的语法
    }

    /**
     * 调用本地的python脚本
     */
    private static void test2() {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("/Users/pengbozhang/ideaworkspace/study-everthing/src/main/java/com/study/python/add.py");

        // 第一个参数为期望获得的函数（变量）的名字，第二个参数为期望返回的对象类型
        PyFunction pyFunction = interpreter.get("add", PyFunction.class);
        int a = 5, b = 10;

        // 调用函数,如果函数中需要参数，在java中必须先将参数转化为对应的"python类型"
        PyObject pyObject = pyFunction.__call__(new PyInteger(a), new PyInteger(b));
        System.out.println("this answer is:" + pyObject);
    }

    /**
     * 调用引用第三方库的python程序
     */
    private static void test3() {
        // 执行文件
        Process process;
        try {
            // """始化了一个3×4的一个矩阵"""
            process = Runtime.getRuntime().exec("python /Users/pengbozhang/ideaworkspace/study-everthing/src/main/java/com/study/python/demo1.py");
            // 用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 第三方的传入参数
     */
    private static void test4() {
        int a = 18;
        int b = 23;
        String[] args = new String[]{"python", "/Users/pengbozhang/ideaworkspace/study-everthing/src/main/java/com/study/python/demo2.py", String.valueOf(a), String.valueOf(b)};
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(args);
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


}
