/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.model.vo;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.clover.zojirushi.entity.InstitutionEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述 组织机构视图对象
 *
 * @author wangxiaofeng
 * @version 1.0
 * @created 2017/5/24 10:19
 */
public class InstitutionVO {
    /**
     * 日志管理器
     */
    Logger logger = LoggerFactory.getLogger(InstitutionVO.class);

    /**
     * 机构ID
     */
    private Long id;
    /**
     * 中文名称
     */
    private String label;
    /**
     * 英文名称
     */
    private String name;
    /**
     * 父机构ID
     */
    private Long parentId;
    /**
     * 类型 1:机构 2:部门
     */
    private Long type;
    /**
     * 行政区划代码
     */
    private String adminDivisionCode;
    /**
     * 行政区划
     */
    private String adminDivision;
    /**
     * 可用状态 1:正常 0:冻结
     */
    private Long enabled;
    /**
     * 社会信用统一代码
     */
    private String socialCreditUnicode;
    /**
     * 组织机构代码
     */
    private String orgCode;
    /**
     * 手动排序
     */
    private Long manualSn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
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

    public Long getEnabled() {
        return enabled;
    }

    public void setEnabled(Long enabled) {
        this.enabled = enabled;
    }

    public String getSocialCreditUnicode() {
        return socialCreditUnicode;
    }

    public void setSocialCreditUnicode(String socialCreditUnicode) {
        this.socialCreditUnicode = socialCreditUnicode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public Long getManualSn() {
        return manualSn;
    }

    public void setManualSn(Long manualSn) {
        this.manualSn = manualSn;
    }

    public InstitutionVO() {
    }

    public InstitutionVO(InstitutionEntity institutionEntity) {
        try {
            BeanUtils.copyProperties(this, institutionEntity);
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
            logger.error(e.getMessage(),e);
        }
    }

    @Override
    public String toString() {
        return "InstitutionVO{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", type=" + type +
                ", adminDivisionCode='" + adminDivisionCode + '\'' +
                ", adminDivision='" + adminDivision + '\'' +
                ", enabled=" + enabled +
                ", socialCreditUnicode='" + socialCreditUnicode + '\'' +
                ", orgCode='" + orgCode + '\'' +
                '}';
    }

}
