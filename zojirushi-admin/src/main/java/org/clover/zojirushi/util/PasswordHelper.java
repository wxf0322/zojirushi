/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.util;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.clover.zojirushi.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 描述 密码加密工具类
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/4/25 8:51
 */
@Service
public class PasswordHelper {

    /**
     * 随机数生成策略
     */
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    /**
     * 加密算法
     */
    @Value("${password.algorithmName}")
    private String algorithmName = "md5";

    /**
     * 加密用户信息
     *
     * @param user
     */
    public void encryptPassword(UserEntity user) {
        // 随机一个盐值
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        // 密码采用md5进行加密，盐值为username + salt
        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt())).toHex();
        user.setPassword(newPassword);
    }

    /**
     * 传入表单中的用户名，密码，数据库中的盐，获得加密之后的密码
     *
     * @param loginName
     * @param password
     * @param salt
     */
    public String encryptPassword(String loginName, String password, String salt) {
        String pwd = new SimpleHash(
                algorithmName,
                password,
                ByteSource.Util.bytes(loginName + salt)).toHex();
        return pwd;
    }

}
