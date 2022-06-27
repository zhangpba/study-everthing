package com.study.jiami;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 加密帮助类
 *
 * @author zhangpba
 * @date 2022-01-28
 */
public class SecurityUtil {
    /**
     * MD5加密
     *
     * @param input 加密数据
     * @return 加密后的数据
     */
    public String md5Encode(byte[] input) {
        return DigestUtils.md5Hex(input);
    }

    /**
     * sha256加密
     *
     * @param input 加密数据
     * @return 加密后的数据
     */
    public String sha256Encode(byte[] input) {
        return DigestUtils.sha256Hex(input);
    }
}
