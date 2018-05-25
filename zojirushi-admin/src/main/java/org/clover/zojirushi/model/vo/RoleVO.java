/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.model.vo;

/**
 * @author Pisces Lu
 * @version 1.0
 * @created 2017-6-9 10:08
 */
public class RoleVO {

    /**
     *角色ID
     */
    private long id;

    /**
     *角色英文名称
     */
    private String name;

    /**
     *状态
     */
    private Long enabled;

    /**
     * 备注
     */
    private String remarks;

    /**
     *角色中文名称
     */
    private String label;

    /**
     * 关联的用户id串
     */
    private String userIds;

    /**
     * 关联的权限id串
     */
    private String privilegeIds;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }


    public Long getEnabled() {
        return enabled;
    }

    public void setEnabled(Long enabled) {
        this.enabled = enabled;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }



    public String getPrivilegeIds() {
        return privilegeIds;
    }

    public void setPrivilegeIds(String privilegeIds) {
        this.privilegeIds = privilegeIds;
    }


    @Override
    public String toString() {
        return "RoleVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", remarks='" + remarks + '\'' +
                ", label='" + label + '\'' +
                ", userIds='" + userIds + '\'' +
                ", privilegeIds='" + privilegeIds + '\'' +
                '}';
    }
}
