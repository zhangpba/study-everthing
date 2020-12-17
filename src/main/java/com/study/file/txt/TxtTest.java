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

        String sourcePath = getPathAndFile("test.txt");
        String targetePath = sourcePath.replace(".txt", "1.txt");
        copy(sourcePath, targetePath);

    }

    /**
     * 复制文本
     *
     * @param sourcePath 源路径
     * @param targetPath 目标路径
     */
    public static void copy(String sourcePath, String targetPath) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(sourcePath), "UTF-8"));
            String b = "";
            StringBuffer sb = new StringBuffer();
            try {
                while ((b = br.readLine()) != null) {
                    //得到文件内容放到sb中
                    sb.append(b);

                    // 换行
                    sb.append(System.getProperty("line.separator"));

                    //这里可以写自己想对每一行的处理代码
                    System.out.println(b);
                }
                String s = sb.toString();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetPath), "UTF-8"));
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
    private static String getPathAndFile(String fileName) {

        String filePath = PathUtil.getSpecific("txt") + fileName;
        System.out.println(filePath);

        return filePath;
    }
}
