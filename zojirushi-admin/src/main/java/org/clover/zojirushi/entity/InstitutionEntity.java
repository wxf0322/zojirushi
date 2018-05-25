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
@Table(name = "INSTITUTIONS")
public class InstitutionEntity {

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
     * 树形等级
     */
    private Long treeLevel;
    /**
     * 父机构ID
     */
    private Long parentId;
    /**
     * 树形路径
     */
    private String path;
    /**
     * 类型 1:机构 2:部门
     */
    private Long type;
    /**
     * 手动排序
     */
    private Long manualSn;
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
     * 创建时间
     */
    private Date timeCreated;
    /**
     * 创建人ID
     */
    private Long creatorId;
    /**
     * 更新时间
     */
    private Date lastModified;
    /**
     * 更新人ID
     */
    private Long modifierId;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 更新人
     */
    private String modifier;

    @Id
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "LABEL")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
    @Column(name = "TREE_LEVEL")
    public Long getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(Long treeLevel) {
        this.treeLevel = treeLevel;
    }

    @Basic
    @Column(name = "PARENT_ID")
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "PATH")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "TYPE")
    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    @Basic
    @Column(name = "MANUAL_SN")
    public Long getManualSn() {
        return manualSn;
    }

    public void setManualSn(Long manualSn) {
        this.manualSn = manualSn;
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
    @Column(name = "ENABLED")
    public Long getEnabled() {
        return enabled;
    }

    public void setEnabled(Long enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "SOCIAL_CREDIT_UNICODE")
    public String getSocialCreditUnicode() {
        return socialCreditUnicode;
    }

    public void setSocialCreditUnicode(String socialCreditUnicode) {
        this.socialCreditUnicode = socialCreditUnicode;
    }

    @Basic
    @Column(name = "ORG_CODE")
    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
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
    @Column(name = "CREATOR")
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "MODIFIER")
    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstitutionEntity that = (InstitutionEntity) o;

        if (id != that.id) return false;
        if (label != null ? !label.equals(that.label) : that.label != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (treeLevel != null ? !treeLevel.equals(that.treeLevel) : that.treeLevel != null) return false;
        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null) return false;
        if (path != null ? !path.equals(that.path) : that.path != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (manualSn != null ? !manualSn.equals(that.manualSn) : that.manualSn != null) return false;
        if (adminDivisionCode != null ? !adminDivisionCode.equals(that.adminDivisionCode)
                : that.adminDivisionCode != null)
            return false;
        if (adminDivision != null ? !adminDivision.equals(that.adminDivision) : that.adminDivision != null)
            return false;
        if (enabled != null ? !enabled.equals(that.enabled) : that.enabled != null) return false;
        if (socialCreditUnicode != null ? !socialCreditUnicode.equals(that.socialCreditUnicode)
                : that.socialCreditUnicode != null)
            return false;
        if (orgCode != null ? !orgCode.equals(that.orgCode) : that.orgCode != null) return false;
        if (timeCreated != null ? !timeCreated.equals(that.timeCreated) : that.timeCreated != null) return false;
        if (creatorId != null ? !creatorId.equals(that.creatorId) : that.creatorId != null) return false;
        if (lastModified != null ? !lastModified.equals(that.lastModified) : that.lastModified != null) return false;
        if (modifierId != null ? !modifierId.equals(that.modifierId) : that.modifierId != null) return false;
        if (creator != null ? !creator.equals(that.creator) : that.creator != null) return false;
        if (modifier != null ? !modifier.equals(that.modifier) : that.modifier != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (treeLevel != null ? treeLevel.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (manualSn != null ? manualSn.hashCode() : 0);
        result = 31 * result + (adminDivisionCode != null ? adminDivisionCode.hashCode() : 0);
        result = 31 * result + (adminDivision != null ? adminDivision.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        result = 31 * result + (socialCreditUnicode != null ? socialCreditUnicode.hashCode() : 0);
        result = 31 * result + (orgCode != null ? orgCode.hashCode() : 0);
        result = 31 * result + (timeCreated != null ? timeCreated.hashCode() : 0);
        result = 31 * result + (creatorId != null ? creatorId.hashCode() : 0);
        result = 31 * result + (lastModified != null ? lastModified.hashCode() : 0);
        result = 31 * result + (modifierId != null ? modifierId.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (modifier != null ? modifier.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "InstitutionEntity{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", name='" + name + '\'' +
                ", treeLevel=" + treeLevel +
                ", parentId=" + parentId +
                ", path='" + path + '\'' +
                ", type=" + type +
                ", manualSn=" + manualSn +
                ", adminDivisionCode='" + adminDivisionCode + '\'' +
                ", adminDivision='" + adminDivision + '\'' +
                ", enabled=" + enabled +
                ", socialCreditUnicode='" + socialCreditUnicode + '\'' +
                ", orgCode='" + orgCode + '\'' +
                ", timeCreated=" + timeCreated +
                ", creatorId=" + creatorId +
                ", lastModified=" + lastModified +
                ", modifierId=" + modifierId +
                ", creator='" + creator + '\'' +
                ", modifier='" + modifier + '\'' +
                '}';
    }
}
