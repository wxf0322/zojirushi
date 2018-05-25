/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.model.vo;

/**
 * @author Pisces Lu
 * @version 1.0
 * @created 2017-6-14 17:35
 */
public class PrivilegeVO {
    /**
     *权限ID
     */
    private long id;
    /**
     *权限中文名称
     */
    private String label;
    /**
     *权限英文编码
     */
    private String name;
    /**
     *可用状态(1;正常 0:冻结)
     */
    private Long enabled;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 角色串
     */
    private  String roleIds;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Long getEnabled() {
        return enabled;
    }

    public void setEnabled(Long enabled) {
        this.enabled = enabled;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    @Override
    public String toString() {
        return "PrivilegeVO{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", remarks='" + remarks + '\'' +
                ", roleIds='" + roleIds + '\'' +
                '}';
    }
}
