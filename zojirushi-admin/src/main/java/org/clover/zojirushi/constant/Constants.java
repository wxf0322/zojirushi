/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.constant;

/**
 * 描述 错误常量类
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/4/24 15:43
 */
public interface Constants {
    /**
     * 客户端认证失败
     */
    String INVALID_CLIENT_ID = "客户端验证失败，如：错误的client_id/client_secret。";

    /**
     * 无效的AccessToken
     */
    String INVALID_ACCESS_TOKEN = "accessToken无效或已过期。";

    /**
     * 无效的回调地址
     */
    String INVALID_REDIRECT_URI = "缺少回调地址。";

    /**
     * 无效的ResponseType
     */
    String INVALID_RESPONSE_TYPE = "无效的ResponseType。";

    /**
     * 不同的重定向地址
     */
    String DIFFERENT_REDIRECT_URI = "当前redirectUri与获取授权码时的redirectUri不同";

    /**
     * 无效的授权码
     */
    String INVALID_AUTH_CODE = "错误的授权码。";

    /**
     * 错误或无效的参数
     */
    String INVALID_PARAMS = "错误或无效的参数";

    /**
     * 用户密码错误
     */
    String INCORRECT_CREDENTIALS = "用户名或密码错误";

    /**
     * 账号不存在
     */
    String UNKNOWN_ACCOUNT = "用户名不存在";

    /**
     * 该账号被管理员锁定
     */
    String LOCKED_ACCOUNT = "该账号已被管理员锁定";

    /**
     * 用户名或密码不能为空
     */
    String EMPTY_ACCOUNT_OR_PASSWORD = "用户名或密码不能为空";

    /**
     * 未知请求
     */
    String UNKNOWN_REQUEST = "未知请求";

    /**
     * 登录成功
     */
    String LOGIN_SUCCESS = "登录成功";
}

