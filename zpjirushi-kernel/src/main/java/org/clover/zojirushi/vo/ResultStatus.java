/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.vo;

/**
 * 描述 返回结果的工具类
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/5/19 11:30
 */
public class ResultStatus {

    /**
     * 是否成功
     */
    private boolean success = true;

    /**
     * 返回的提示信息
     */
    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultStatus() {
    }

    public ResultStatus(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }
}
