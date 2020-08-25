package com.study.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class FtpUtils {

    private InputStream is; // 文件下载输入流
    private FTPClient ftpClient;
    private static FtpUtils ftpUtils;
    Map<String, Object> map;

    // 私有构造器
    private FtpUtils() {
        if (null == ftpClient) {
            ftpClient = new FTPClient();
        }
    }

    // 获取ftpUtils对象实例
    public synchronized static FtpUtils getInstance() {
        if (null == ftpUtils) {
            ftpUtils = new FtpUtils();
        }
        return ftpUtils;
    }

    // 连接服务器
    public boolean connectToTheServer(String remotePath, Map<String, Object> map) {
        this.map = map;

        String ftpHost = (String) map.get("ftpHost");
        Integer ftpPort = (Integer) map.get("ftpPort");
        String ftpUsername = (String) map.get("ftpUsername");
        String ftpPassword = (String) map.get("ftpPassword");

        // 定义返回值
        boolean result = false;
        try {
            // 连接至服务器，端口默认为21时，可直接通过URL连接
            ftpClient.connect(ftpHost, ftpPort);
            // 登录服务器
            ftpClient.login(ftpUsername, ftpPassword);
            // 判断返回码是否合法
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                // 不合法时断开连接
                ftpClient.disconnect();
                // 结束程序
                return result;
            }
            // 设置文件操作目录
            result = ftpClient.changeWorkingDirectory(remotePath);
            // 设置文件类型，二进制
            result = ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 设置缓冲区大小
            ftpClient.setBufferSize(3072);
            // 设置字符编码
            ftpClient.setControlEncoding("UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 上传文件至FTP服务器
     *
     * @param storePath 上传文件存储路径
     * @param fileName  上传文件存储名称
     * @param is        上传文件输入流
     * @return
     */
    public boolean storeFile(String storePath, String fileName, InputStream is) {
        boolean result = false;
        try {
            // 连接至服务器
            result = connectToTheServer(storePath, map);
            // 判断服务器是否连接成功
            if (result) {
                // 上传文件
                result = ftpClient.storeFile(fileName, is);
            }
            // 关闭输入流
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 判断输入流是否存在
            if (null != is) {
                try {
                    // 关闭输入流
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 登出服务器并断开连接
            ftpUtils.logout();
        }
        return result;
    }


    /**
     * 下载FTP服务器文件至本地
     *
     * @param serverName 服务器名称
     * @param remotePath 下载文件存储路径
     * @param fileName   下载文件存储名称
     * @return 文件输入流
     */
    public InputStream retrieveFile(String serverName, String remotePath, String fileName) {
        try {
            boolean result = false;
            // 连接至服务器
            result = connectToTheServer(remotePath, map);
            // 判断服务器是否连接成功
            if (result) {
                // 获取文件输入流
                is = ftpClient.retrieveFileStream(fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }

    /**
     * 删除FTP服务器文件
     *
     * @param serverName 服务器名称
     * @param remotePath 当前访问目录
     * @param fileName   文件存储名称
     * @return
     */
    public boolean deleteFile(String serverName, String remotePath, String fileName) {
        boolean result = false;
        // 连接至服务器
        result = connectToTheServer(remotePath, map);
        // 判断服务器是否连接成功
        if (result) {
            try {
                // 删除文件
                result = ftpClient.deleteFile(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 登出服务器并断开连接
                ftpUtils.logout();
            }
        }
        return result;
    }


    // 登出服务器并断开连接
    public boolean logout() {
        boolean result = false;
        if (null != is) {
            try {
                // 关闭输入流
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (null != ftpClient) {
            try {
                // 登出服务器
                result = ftpClient.logout();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 判断连接是否存在
                if (ftpClient.isConnected()) {
                    try {
                        // 断开连接
                        ftpClient.disconnect();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return result;
    }
}
