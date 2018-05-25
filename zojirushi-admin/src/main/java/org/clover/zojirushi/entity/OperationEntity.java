/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.entity;

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
@Table(name = "OPERATIONS")
public class OperationEntity {

    /**
     * 操作id
     */
    private Long id;
    /**
     * 操作名称中文
     */
    private String label;
    /**
     * 操作名称英文
     */
    private String name;
    /**
     * 手动排序
     */
    private Long manualSn;
    /**
     * 父亲id
     */
    private Long parentId;
    /**
     * 树形层级
     */
    private Long treeLevel;
    /**
     * 树形路径
     */
    private String path;
    /**
     * 操作URL
     */
    private String resUrl;
    /**
     * 操作图标路径
     */
    private String iconPath;
    /**
     * 操作类型 1:菜单 2:按钮
     */
    private long optType;
    /**
     * 可用状态
     */
    private Long enabled;
    /**
     * 应用ID
     */
    private Long applicationId;

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
    @Column(name = "MANUAL_SN")
    public Long getManualSn() {
        return manualSn;
    }

    public void setManualSn(Long manualSn) {
        this.manualSn = manualSn;
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
    @Column(name = "TREE_LEVEL")
    public Long getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(Long treeLevel) {
        this.treeLevel = treeLevel;
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
    @Column(name = "RES_URL")
    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    @Basic
    @Column(name = "ICON_PATH")
    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    @Basic
    @Column(name = "OPT_TYPE")
    public long getOptType() {
        return optType;
    }

    public void setOptType(long optType) {
        this.optType = optType;
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
    @Column(name = "APPLICATION_ID")
    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperationEntity that = (OperationEntity) o;

        if (id != that.id) return false;
        if (optType != that.optType) return false;
        if (label != null ? !label.equals(that.label) : that.label != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (manualSn != null ? !manualSn.equals(that.manualSn) : that.manualSn != null) return false;
        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null) return false;
        if (treeLevel != null ? !treeLevel.equals(that.treeLevel) : that.treeLevel != null) return false;
        if (path != null ? !path.equals(that.path) : that.path != null) return false;
        if (resUrl != null ? !resUrl.equals(that.resUrl) : that.resUrl != null) return false;
        if (iconPath != null ? !iconPath.equals(that.iconPath) : that.iconPath != null) return false;
        if (enabled != null ? !enabled.equals(that.enabled) : that.enabled != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (manualSn != null ? manualSn.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (treeLevel != null ? treeLevel.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (resUrl != null ? resUrl.hashCode() : 0);
        result = 31 * result + (iconPath != null ? iconPath.hashCode() : 0);
        result = 31 * result + (int) (optType ^ (optType >>> 32));
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OperationEntity{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", name='" + name + '\'' +
                ", manualSn=" + manualSn +
                ", parentId=" + parentId +
                ", treeLevel=" + treeLevel +
                ", path='" + path + '\'' +
                ", resUrl='" + resUrl + '\'' +
                ", iconPath='" + iconPath + '\'' +
                ", optType=" + optType +
                ", enabled=" + enabled +
                '}';
    }
}
