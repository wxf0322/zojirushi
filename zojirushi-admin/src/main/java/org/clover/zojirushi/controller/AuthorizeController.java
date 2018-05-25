/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.controller;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.clover.zojirushi.constant.Constants;
import org.clover.zojirushi.entity.UserEntity;
import org.clover.zojirushi.interceptor.auth.ShiroSecurityHelper;
import org.clover.zojirushi.service.UserService;
import org.clover.zojirushi.util.PasswordHelper;
import org.clover.zojirushi.util.RequestHelper;
import org.clover.zojirushi.vo.ResultStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述 权限Controller层
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/4/24 15:38
 */
@Controller
@RequestMapping("/")
@MultipartConfig
public class AuthorizeController {

    /**
     * @see Logger
     */
    private static Logger logger = LoggerFactory.getLogger(AuthorizeController.class);

    /**
     * @see UserService
     */
    @Autowired
    private UserService userService;

    /**
     * @see PasswordHelper
     */
    @Autowired
    private PasswordHelper passwordHelper;

    /**
     * @see ShiroSecurityHelper
     */
    @Autowired
    private ShiroSecurityHelper shiroSecurityHelper;


    /**
     * 重定向登录页
     *
     * @return
     */
    @RequestMapping("index")
    public String index() {
        Subject subject = SecurityUtils.getSubject();
        return subject.isAuthenticated() ? "index" : "login";
    }

    /**
     * 登录处理
     *
     * @param request
     * @param model
     * @param loginName  登录名
     * @param password   密码
     * @param rememberMe 记住我
     * @return
     */
    @RequestMapping("login")
    public String login(HttpServletRequest request, Model model, @RequestParam("loginName") String loginName, @RequestParam("password") String password, boolean rememberMe) {
        model.addAttribute("loginName", loginName);
        model.addAttribute("password", password);
        // loginName 与 password 只能用post请求提交
        if ("get".equalsIgnoreCase(request.getMethod())) {
            model.addAttribute("result", new ResultStatus(false, Constants.UNKNOWN_REQUEST));
            return "login";
        }

        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)) {
            request.setAttribute("error", Constants.EMPTY_ACCOUNT_OR_PASSWORD);
            model.addAttribute("result", new ResultStatus(false, Constants.EMPTY_ACCOUNT_OR_PASSWORD));
            return "login";
        }

        UserEntity user = userService.getUserByLoginName(loginName);
        if (user == null) {
            request.setAttribute("error", Constants.UNKNOWN_ACCOUNT);
            model.addAttribute("result", new ResultStatus(false, Constants.UNKNOWN_ACCOUNT));
            return "login";
        }

        // 加密过后的密码
        String encryptPwd = passwordHelper.encryptPassword(loginName, password, user.getSalt());

        // 传入加密过的密码
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, encryptPwd, rememberMe, new RequestHelper().getIp(request));

        Subject subject = SecurityUtils.getSubject();
        String error = null;

        try {
            subject.login(token);
        } catch (IncorrectCredentialsException e) {
            error = Constants.INCORRECT_CREDENTIALS;
            logger.error(error, e);
        } catch (UnknownAccountException e) {
            error = Constants.INCORRECT_CREDENTIALS;
            logger.error(error, e);
        } catch (LockedAccountException e) {
            error = Constants.LOCKED_ACCOUNT;
            logger.error(error, e);
        } catch (AuthenticationException e) {
            error = e.getMessage();
            logger.error(error, e);
        }

        if (error != null) {
            request.setAttribute("error", error);
            model.addAttribute("result", new ResultStatus(false, error));
            return "login";
        } else {
            shiroSecurityHelper.kickOutUser(user.getLoginName());

            // 设置session
            Session session = subject.getSession(true);
            session.setAttribute("user", user);

            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("user", user);

            logger.info("用户登录成功！[{}]", loginName);
            model.addAttribute("result", new ResultStatus(true, Constants.LOGIN_SUCCESS));
            return "index";
        }
    }

    /**
     * 登出
     *
     * @return
     */
    @RequestMapping("logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "login";
    }

}
