/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 描述 md5工具类
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/4/25 8:54
 */
public class MD5Util {

    /**
     * 日志管理器
     */
    private static Logger logger = LoggerFactory.getLogger(MD5Util.class);

    /**
     * 传入一个字符串String msg，返回Java MD5加密后的16进制的字符串结果。
     * 结果形如：c0e84e870874dd37ed0d164c7986f03a
     *
     * @param msg
     * @return
     */
    public static String getMD5(String msg) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error("There is no such algorithm!", e.getMessage());
        }
        md.reset();
        md.update(msg.getBytes());
        byte[] bytes = md.digest();

        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            // byte转换成16进制
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

}
