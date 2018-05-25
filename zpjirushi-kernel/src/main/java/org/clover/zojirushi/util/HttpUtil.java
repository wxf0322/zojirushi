/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Http请求发送与接收工具类
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/5/1 9:45
 */
public class HttpUtil {

    /**
     * @see Logger
     */
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private HttpUtil() {
    }

    /**
     * http post方法
     *
     * @param url
     * @param params
     * @return
     */
    public static String httpPost(String url, Map<String, String> params) {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

        List<NameValuePair> postData = new ArrayList<>();
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            postData.add(new NameValuePair(key, params.get(key)));
        }
        postMethod.setRequestBody(postData.toArray(new NameValuePair[postData.size()]));

        String html = "";
        try {
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode == HttpStatus.SC_OK) {
                byte[] responseBody = postMethod.getResponseBody();
                html = new String(responseBody, "utf-8");
            }
        } catch (Exception e) {
            logger.error("The page can not be found.");
        } finally {
            postMethod.releaseConnection();
        }
        return html;
    }

    /**
     * http get方法
     *
     * @param url
     * @return
     */
    public static String httpGet(String url) {
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        String html = "";
        try {
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                logger.error("Method failed: " + getMethod.getStatusLine());
            }

            byte[] responseBody = getMethod.getResponseBody();
            html = new String(responseBody, "utf-8");
        } catch (IOException e) {
            logger.error("The page can not be found.");
        } finally {
            getMethod.releaseConnection();
        }
        return html;
    }

}