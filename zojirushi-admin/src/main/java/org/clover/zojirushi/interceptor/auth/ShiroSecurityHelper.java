/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.interceptor.auth;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * 描述 shiro安全相关工具类
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/7/4 下午4:51
 */
public class ShiroSecurityHelper {

    /**
     * @see Logger
     */
    private static Logger logger = LoggerFactory.getLogger(ShiroSecurityHelper.class);

    /**
     * @see SessionDAO
     */
    private SessionDAO sessionDAO;

    /**
     * 获取sessionDAO
     *
     * @return
     */
    public SessionDAO getSessionDAO() {
        return sessionDAO;
    }

    /**
     * 设置sessionDAO
     *
     * @param sessionDAO
     */
    public void setSessionDAO(SessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }

    /**
     * 获得当前用户的session
     *
     * @return
     */
    public Session getSession() {
        return getSubject().getSession();
    }

    /**
     * 获得shiro的subject
     *
     * @return
     */
    private Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获得sessionId
     *
     * @return
     */
    public String getSessionId() {
        Session session = getSession();
        if (null == session) {
            return null;
        }
        return getSession().getId().toString();
    }

    /**
     * 根据loginName获得session
     *
     * @param loginName
     * @return
     */
    public Session getSessionByLoginName(String loginName) {
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session session : sessions) {
            String sessionKey = String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
            if (null != session && StringUtils.equals(sessionKey, loginName)) {
                return session;
            }
        }
        return null;
    }

    /**
     * 踢除用户
     *
     * @param loginName
     */
    public void kickOutUser(String loginName) {
        boolean flag = false;
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        for (Session session : sessions) {
            String sessionKey = String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
            if (null != session && StringUtils.equals(sessionKey, loginName)) {
                session.removeAttribute("user");
                flag = true;
            }
        }
        if(flag) {
            logger.info("成功踢出用户！[{}]", loginName);
        }
    }

    /**
     * 判断当前用户是否已通过认证
     *
     * @return
     */
    public boolean hasAuthenticated() {
        return getSubject().isAuthenticated();
    }

}
