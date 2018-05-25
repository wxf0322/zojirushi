/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 描述 返回错误信息的工具类
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/5/9 10:42
 */
public class ErrorStatus {

    /**
     * 无效的参数
     */
    public static final String INVALID_PARAMS = "invalid_params";

    /**
     * 错误
     */
    private String error;

    /**
     * 错误描述
     */
    private String errorDescription;

    /**
     * 返回的json字符串
     */
    private String body = "";

    public String getBody() {
        return body;
    }

    /**
     * 对象构造器
     */
    public static class Builder {
        /**
         * 错误
         */
        private String error = "";

        /**
         * 错误描述
         */
        private String errorDescription = "";

        public Builder(String error, String errorDescription) {
            this.error = error;
            this.errorDescription = errorDescription;
        }

        public Builder setError(String error) {
            this.error = error;
            return this;
        }

        public Builder setErrorDescription(String errorDescription) {
            this.errorDescription = errorDescription;
            return this;
        }

        public ErrorStatus buildJSONMessage() {
            return new ErrorStatus(this);
        }
    }

    public ErrorStatus(Builder builder) {
        this.error = builder.error;
        this.errorDescription = builder.errorDescription;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error", this.error);
        jsonObject.put("error_description", this.errorDescription);
        this.body = jsonObject.toString();
    }

}
