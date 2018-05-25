package org.clover.zojirushi.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {

    /**
     * 获取Ip地址
     *
     * @param request
     * @return
     */
    public String getIp(HttpServletRequest request) {
        String xRealIP = request.getHeader("X-Real-IP");
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(xRealIP) && !"unKnown".equalsIgnoreCase(xRealIP)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = xRealIP.indexOf(",");
            if (index != -1) {
                return xRealIP.substring(0, index);
            } else {
                return xRealIP;
            }
        }
        xForwardedFor = xRealIP;
        if (StringUtils.isNotEmpty(xForwardedFor) && !"unKnown".equalsIgnoreCase(xForwardedFor)) {
            return xForwardedFor;
        }
        if (StringUtils.isBlank(xForwardedFor) || "unknown".equalsIgnoreCase(xForwardedFor)) {
            xForwardedFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(xForwardedFor) || "unknown".equalsIgnoreCase(xForwardedFor)) {
            xForwardedFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(xForwardedFor) || "unknown".equalsIgnoreCase(xForwardedFor)) {
            xForwardedFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(xForwardedFor) || "unknown".equalsIgnoreCase(xForwardedFor)) {
            xForwardedFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(xForwardedFor) || "unknown".equalsIgnoreCase(xForwardedFor)) {
            xForwardedFor = request.getRemoteAddr();
        }
        return xForwardedFor;
    }

}
