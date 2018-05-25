/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.model.vo;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.clover.zojirushi.entity.StaffEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/6/2 11:37
 */
public class StaffVO {

    /**
     * 日志管理器
     */
    private static Logger logger = LoggerFactory.getLogger(StaffVO.class);

    /**
     * 员工姓名
     */
    private String name;
    /**
     * 固定电话号
     */
    private String tel;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 邮编
     */
    private String zipCode;
    /**
     * 内网邮箱
     */
    private String email;
    /**
     * 外网邮箱
     */
    private String extranetEmail;
    /**
     * 别名,多个用逗号隔开
     */
    private String aliasNames;
    /**
     * 性别  1;男  2:女
     */
    private Long sex;
    /**
     * 职称
     */
    private String professionalTitle;
    /**
     * 职务
     */
    private String officalPost;
    /**
     * 职责
     */
    private String officalDuty;
    /**
     * 员工类别
     */
    private String employeeType;
    /**
     * 员工工号
     */
    private String employeeNo;
    /**
     * 身份证号码
     */
    private String citizenIdNumber;
    /**
     * 出生日期
     */
    private Long birthday;
    /**
     * 居住地行政区划编号
     */
    private String adminDivisionCode;
    /**
     * 居住地行政区划
     */
    private String adminDivision;
    /**
     * 现居住地址
     */
    private String curResidence;
    /**
     * 备注说明
     */
    private String remarks;
    /**
     * 照片地址
     */
    private String pictureUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExtranetEmail() {
        return extranetEmail;
    }

    public void setExtranetEmail(String extranetEmail) {
        this.extranetEmail = extranetEmail;
    }

    public String getAliasNames() {
        return aliasNames;
    }

    public void setAliasNames(String aliasNames) {
        this.aliasNames = aliasNames;
    }

    public Long getSex() {
        return sex;
    }

    public void setSex(Long sex) {
        this.sex = sex;
    }

    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    public String getOfficalPost() {
        return officalPost;
    }

    public void setOfficalPost(String officalPost) {
        this.officalPost = officalPost;
    }

    public String getOfficalDuty() {
        return officalDuty;
    }

    public void setOfficalDuty(String officalDuty) {
        this.officalDuty = officalDuty;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getCitizenIdNumber() {
        return citizenIdNumber;
    }

    public void setCitizenIdNumber(String citizenIdNumber) {
        this.citizenIdNumber = citizenIdNumber;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public String getAdminDivisionCode() {
        return adminDivisionCode;
    }

    public void setAdminDivisionCode(String adminDivisionCode) {
        this.adminDivisionCode = adminDivisionCode;
    }

    public String getAdminDivision() {
        return adminDivision;
    }

    public void setAdminDivision(String adminDivision) {
        this.adminDivision = adminDivision;
    }

    public String getCurResidence() {
        return curResidence;
    }

    public void setCurResidence(String curResidence) {
        this.curResidence = curResidence;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public StaffVO() {
    }

    public StaffVO(StaffEntity staffEntity) {
        try {
            BeanUtils.copyProperties(this, staffEntity);
            this.birthday = (staffEntity.getBirthday() != null) ? staffEntity.getBirthday().getTime() : null;
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error(e.getMessage(), e);
        }
    }


}
