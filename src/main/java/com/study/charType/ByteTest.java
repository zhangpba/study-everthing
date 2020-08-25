package com.study.charType;

public class ByteTest {

    public static void main(String[] args) {

        int num = 12;
        // int转成字节
        byte[] bytes = int2Bytes(num);
        // 字节转成int
        System.out.println(bytes2Int(bytes));

    }


    /**
     * 将字节转化为字符串
     *
     * @param bytes
     * @return
     */
    public static String byteToString(byte[] bytes) {
//        byte[] bytes = {123, 34, 115, 117, 99, 99, 101, 115, 115, 34, 58, 102, 97, 108, 115, 101, 44, 34, 101, 114, 114, 67, 111, 100, 101, 34, 58, 49, 48, 48, 48, 49, 44, 34, 101, 114, 114, 77, 115, 103, 34, 58, 34, -26, -74, -120, -26, -127, -81, -26, -96, -121, -23, -94, -104, 32, 109, 115, 103, 84, 105, 116, 108, 101, 32, -28, -72, -115, -24, -125, -67, -28, -72, -70, -25, -87, -70, 34, 44, 34, 101, 114, 114, 78, 97, 109, 101, 34, 58, 34, -27, -113, -126, -26, -107, -80, -26, -96, -95, -23, -86, -116, -27, -92, -79, -24, -76, -91, 34, 44, 34, 116, 111, 116, 97, 108, 67, 111, 117, 110, 116, 34, 58, 48, 44, 34, 100, 97, 116, 97, 115, 34, 58, 110, 117, 108, 108, 44, 34, 112, 97, 103, 101, 78, 117, 109, 34, 58, 49, 44, 34, 112, 97, 103, 101, 83, 105, 122, 101, 34, 58, 49, 48, 125};

        String str = null;
        try {
            str = new String(bytes, "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            System.err.println("转换异常...");
        }
        return str;
    }


    /**
     * 将整数转成四位的byte[]数组
     *
     * @param integer
     * @return
     */
    public static byte[] int2Bytes(int integer) {
        byte[] bytes = new byte[4];
        bytes[3] = (byte) (integer >> 24);
        bytes[2] = (byte) (integer >> 16);
        bytes[1] = (byte) (integer >> 8);
        bytes[0] = (byte) integer;
        return bytes;
    }

    /**
     * 将byte[]数组转成整型
     * 如果不与0xff进行按位与操作，转换结果将出错
     *
     * @param bytes
     * @return
     */
    public static int bytes2Int(byte[] bytes) {
        int int1 = bytes[0] & 0xff;
        int int2 = (bytes[1] & 0xff) << 8;
        int int3 = (bytes[2] & 0xff) << 16;
        int int4 = (bytes[3] & 0xff) << 24;
        return int1 | int2 | int3 | int4;
    }
}
