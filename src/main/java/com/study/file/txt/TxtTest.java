package com.study.file.txt;

import com.study.file.PathUtil;

import java.io.*;

/**
 * 读取文本中的数据，并写入到另一个文本中
 *
 * @date 2020-07-27
 */
public class TxtTest {

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(getPathAndFile()), "UTF-8"));
            String b = "";
            StringBuffer sb = new StringBuffer();
            try {
                while ((b = br.readLine()) != null) {
                    //得到文件内容放到sb中
                    sb.append(b);

                    //这里可以写自己想对每一行的处理代码
                    System.out.println(b);
                }
                String s = sb.toString();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(getPathAndFile().replace(".txt", "1.txt")), "UTF-8"));
                bw.write(s);
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    // 获取txt的位置
    private static String getPathAndFile() {

        String filePath = PathUtil.getPackagePath() + "txt" + System.getProperty("file.separator") + "test.txt";
        System.out.println(filePath);

        return filePath;
    }
}
