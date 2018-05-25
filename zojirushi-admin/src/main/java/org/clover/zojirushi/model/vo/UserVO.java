/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.model.vo;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.clover.zojirushi.entity.StaffEntity;
import org.clover.zojirushi.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述 用户视图对象
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/5/19 9:16
 */
public class UserVO {

    /**
     * 日志管理器
     */
    private static Logger logger = LoggerFactory.getLogger(UserVO.class);

    /**
     * id
     */
    Long id;
    /**
     * 登入名
     */
    String loginName;
    /**
     * 姓名
     */
    String name;
    /**
     * 状态
     */
    Long enabled;
    /**
     * 固定电话
     */
    String tel;
    /**
     * 邮编
     */
    String zipCode;
    /**
     * 手机电话
     */
    String mobile;
    /**
     * 内网邮箱
     */
    String email;
    /**
     * 外网邮箱
     */
    String extranetEmail;
    /**
     * 别名
     */
    String aliasNames;
    /**
     * 性别
     */
    Long sex;
    /**
     * 职称
     */
    String professionalTitle;
    /**
     * 职责
     */
    String officalPost;
    /**
     * 职务
     */
    String officalDuty;
    /**
     * 员工类型
     */
    String employeeType;
    /**
     * 员工工号
     */
    String employeeNo;
    /**
     * 出生日期
     */
    Long birthday;
    /**
     * 居住地行政区划编号
     */
    String adminDivisionCode;
    /**
     * 居住地行政区划
     */
    String adminDivision;
    /**
     * 现居住地址
     */
    String curResidence;
    /**
     * 备注说明
     */
    String remarks;
    /**
     * 身份证号码
     */
    String citizenIdNumber;
    /**
     * 照片路径
     */
    String pictureUrl;
    /**
     * 关联的角色id串
     */
    String roleIds;

    /**
     * 关联的组织机构id串
     */
    String institutionIds;

    /**
     * 关联的组织机构名称
     */
    List<String> institutionNames;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getEnabled() {
        return enabled;
    }

    public void setEnabled(Long enabled) {
        this.enabled = enabled;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getCitizenIdNumber() {
        return citizenIdNumber;
    }

    public void setCitizenIdNumber(String citizenIdNumber) {
        this.citizenIdNumber = citizenIdNumber;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getInstitutionIds() {
        return institutionIds;
    }

    public void setInstitutionIds(String institutionIds) {
        this.institutionIds = institutionIds;
    }

    public List<String> getInstitutionNames() {
        return institutionNames;
    }

    public void setInstitutionNames(List<String> institutionNames) {
        this.institutionNames = institutionNames;
    }

    public UserEntity mergeUserEntity(UserEntity userEntity) {
        try {
            BeanUtils.copyProperties(userEntity, this);
            StaffEntity staffEntity = new StaffEntity();
            if (this.birthday != null) {
                staffEntity.setBirthday(new Date(this.birthday));
            } else {
                staffEntity.setBirthday(null);
            }
            BeanUtils.copyProperties(staffEntity, this);
            userEntity.setStaffEntity(staffEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error(e.getMessage(), e);
        }
        return userEntity;
    }

    public UserVO() {
    }

    public UserVO(UserEntity userEntity) {
        try {
            BeanUtils.copyProperties(this, userEntity);
            if (userEntity.getStaffEntity() != null) {
                StaffEntity staffEntity = userEntity.getStaffEntity();
                BeanUtils.copyProperties(this, staffEntity);
                this.setId(userEntity.getId());
                this.birthday = (staffEntity.getBirthday() != null) ?
                        staffEntity.getBirthday().getTime() : null;
            }
            this.setName(userEntity.getName());
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", tel='" + tel + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", extranetEmail='" + extranetEmail + '\'' +
                ", aliasNames='" + aliasNames + '\'' +
                ", sex=" + sex +
                ", professionalTitle='" + professionalTitle + '\'' +
                ", officalPost='" + officalPost + '\'' +
                ", officalDuty='" + officalDuty + '\'' +
                ", employeeType='" + employeeType + '\'' +
                ", employeeNo='" + employeeNo + '\'' +
                ", birthday=" + birthday +
                ", adminDivisionCode='" + adminDivisionCode + '\'' +
                ", adminDivision='" + adminDivision + '\'' +
                ", curResidence='" + curResidence + '\'' +
                ", remarks='" + remarks + '\'' +
                ", citizenIdNumber='" + citizenIdNumber + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", roleIds='" + roleIds + '\'' +
                ", institutionIds='" + institutionIds + '\'' +
                ", institutionNames='" + institutionNames + '\'' +
                '}';
    }
}
