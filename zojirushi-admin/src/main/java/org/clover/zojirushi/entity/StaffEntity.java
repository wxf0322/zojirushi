/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 描述
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/4/26 11:31
 */
@Entity
@Table(name = "STAFFS")
public class StaffEntity {
    /**
     * 员工ID
     */
    private long id;
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
    private Date birthday;
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
     * 更新时间
     */
    private Date lastModified;
    /**
     * 更新人ID
     */
    private Long modifierId;
    /**
     * 更新人
     */
    private String modifier;
    /**
     * 创建时间
     */
    private Date timeCreated;
    /**
     * 创建人ID
     */
    private Long creatorId;
    /**
     * 创建人
     */
    private String creator;

    /**
     * 照片地址
     */
    private String pictureUrl;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "TEL")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "MOBILE")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "ZIP_CODE")
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Basic
    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "EXTRANET_EMAIL")
    public String getExtranetEmail() {
        return extranetEmail;
    }

    public void setExtranetEmail(String extranetEmail) {
        this.extranetEmail = extranetEmail;
    }

    @Basic
    @Column(name = "ALIAS_NAMES")
    public String getAliasNames() {
        return aliasNames;
    }

    public void setAliasNames(String aliasNames) {
        this.aliasNames = aliasNames;
    }

    @Basic
    @Column(name = "SEX")
    public Long getSex() {
        return sex;
    }

    public void setSex(Long sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "PROFESSIONAL_TITLE")
    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    @Basic
    @Column(name = "OFFICAL_POST")
    public String getOfficalPost() {
        return officalPost;
    }

    public void setOfficalPost(String officalPost) {
        this.officalPost = officalPost;
    }

    @Basic
    @Column(name = "OFFICAL_DUTY")
    public String getOfficalDuty() {
        return officalDuty;
    }

    public void setOfficalDuty(String officalDuty) {
        this.officalDuty = officalDuty;
    }

    @Basic
    @Column(name = "EMPLOYEE_TYPE")
    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    @Basic
    @Column(name = "EMPLOYEE_NO")
    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    @Basic
    @Column(name = "CITIZEN_ID_NUMBER")
    public String getCitizenIdNumber() {
        return citizenIdNumber;
    }

    public void setCitizenIdNumber(String citizenIdNumber) {
        this.citizenIdNumber = citizenIdNumber;
    }

    @Basic
    @Column(name = "BIRTHDAY")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "ADMIN_DIVISION_CODE")
    public String getAdminDivisionCode() {
        return adminDivisionCode;
    }

    public void setAdminDivisionCode(String adminDivisionCode) {
        this.adminDivisionCode = adminDivisionCode;
    }

    @Basic
    @Column(name = "ADMIN_DIVISION")
    public String getAdminDivision() {
        return adminDivision;
    }

    public void setAdminDivision(String adminDivision) {
        this.adminDivision = adminDivision;
    }

    @Basic
    @Column(name = "CUR_RESIDENCE")
    public String getCurResidence() {
        return curResidence;
    }

    public void setCurResidence(String curResidence) {
        this.curResidence = curResidence;
    }

    @Basic
    @Column(name = "REMARKS")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Basic
    @Column(name = "LAST_MODIFIED")
    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @Basic
    @Column(name = "MODIFIER_ID")
    public Long getModifierId() {
        return modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    @Basic
    @Column(name = "MODIFIER")
    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @Basic
    @Column(name = "TIME_CREATED")
    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    @Basic
    @Column(name = "CREATOR_ID")
    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    @Basic
    @Column(name = "CREATOR")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "PICTURE_URL")
    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StaffEntity that = (StaffEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;
        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
        if (zipCode != null ? !zipCode.equals(that.zipCode) : that.zipCode != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (extranetEmail != null ? !extranetEmail.equals(that.extranetEmail) : that.extranetEmail != null)
            return false;
        if (aliasNames != null ? !aliasNames.equals(that.aliasNames) : that.aliasNames != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (professionalTitle != null ? !professionalTitle.equals(that.professionalTitle)
                : that.professionalTitle != null)
            return false;
        if (officalPost != null ? !officalPost.equals(that.officalPost) : that.officalPost != null) return false;
        if (officalDuty != null ? !officalDuty.equals(that.officalDuty) : that.officalDuty != null) return false;
        if (employeeType != null ? !employeeType.equals(that.employeeType) : that.employeeType != null) return false;
        if (employeeNo != null ? !employeeNo.equals(that.employeeNo) : that.employeeNo != null) return false;
        if (citizenIdNumber != null ? !citizenIdNumber.equals(that.citizenIdNumber) : that.citizenIdNumber != null)
            return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (adminDivisionCode != null ? !adminDivisionCode.equals(that.adminDivisionCode)
                : that.adminDivisionCode != null)
            return false;
        if (adminDivision != null ? !adminDivision.equals(that.adminDivision) : that.adminDivision != null)
            return false;
        if (curResidence != null ? !curResidence.equals(that.curResidence) : that.curResidence != null) return false;
        if (remarks != null ? !remarks.equals(that.remarks) : that.remarks != null) return false;
        if (lastModified != null ? !lastModified.equals(that.lastModified) : that.lastModified != null) return false;
        if (modifierId != null ? !modifierId.equals(that.modifierId) : that.modifierId != null) return false;
        if (modifier != null ? !modifier.equals(that.modifier) : that.modifier != null) return false;
        if (timeCreated != null ? !timeCreated.equals(that.timeCreated) : that.timeCreated != null) return false;
        if (creatorId != null ? !creatorId.equals(that.creatorId) : that.creatorId != null) return false;
        if (creator != null ? !creator.equals(that.creator) : that.creator != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (extranetEmail != null ? extranetEmail.hashCode() : 0);
        result = 31 * result + (aliasNames != null ? aliasNames.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (professionalTitle != null ? professionalTitle.hashCode() : 0);
        result = 31 * result + (officalPost != null ? officalPost.hashCode() : 0);
        result = 31 * result + (officalDuty != null ? officalDuty.hashCode() : 0);
        result = 31 * result + (employeeType != null ? employeeType.hashCode() : 0);
        result = 31 * result + (employeeNo != null ? employeeNo.hashCode() : 0);
        result = 31 * result + (citizenIdNumber != null ? citizenIdNumber.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (adminDivisionCode != null ? adminDivisionCode.hashCode() : 0);
        result = 31 * result + (adminDivision != null ? adminDivision.hashCode() : 0);
        result = 31 * result + (curResidence != null ? curResidence.hashCode() : 0);
        result = 31 * result + (remarks != null ? remarks.hashCode() : 0);
        result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
        result = 31 * result + (modifierId != null ? modifierId.hashCode() : 0);
        result = 31 * result + (modifier != null ? modifier.hashCode() : 0);
        result = 31 * result + (timeCreated != null ? timeCreated.hashCode() : 0);
        result = 31 * result + (creatorId != null ? creatorId.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StaffEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", mobile='" + mobile + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", email='" + email + '\'' +
                ", extranetEmail='" + extranetEmail + '\'' +
                ", aliasNames='" + aliasNames + '\'' +
                ", sex=" + sex +
                ", professionalTitle='" + professionalTitle + '\'' +
                ", officalPost='" + officalPost + '\'' +
                ", officalDuty='" + officalDuty + '\'' +
                ", employeeType='" + employeeType + '\'' +
                ", employeeNo='" + employeeNo + '\'' +
                ", citizenIdNumber='" + citizenIdNumber + '\'' +
                ", birthday=" + birthday +
                ", adminDivisionCode='" + adminDivisionCode + '\'' +
                ", adminDivision='" + adminDivision + '\'' +
                ", curResidence='" + curResidence + '\'' +
                ", remarks='" + remarks + '\'' +
                ", lastModified=" + lastModified +
                ", modifierId=" + modifierId +
                ", modifier='" + modifier + '\'' +
                ", timeCreated=" + timeCreated +
                ", creatorId=" + creatorId +
                ", creator='" + creator + '\'' +
                '}';
    }

}
