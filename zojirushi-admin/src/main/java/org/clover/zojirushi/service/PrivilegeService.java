/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.service;

import java.util.List;

import org.clover.zojirushi.entity.PrivilegeEntity;
import org.clover.zojirushi.entity.RoleEntity;
import org.clover.zojirushi.util.SqlFilter;
import org.springframework.data.domain.Page;

/**
 * @author Pisces Lu
 * @version 1.0
 * @created 2017-5-8 17:34
 */
public interface PrivilegeService extends BaseService<PrivilegeEntity, Long> {

    /**
     * 根据app名称来获取权限列表
     *
     * @param appName
     * @return
     */
    List<PrivilegeEntity> listPrivsByAppName(String appName);

    /**
     * 根据用户编码来获取权限列表
     *
     * @param userId
     * @return
     */
    List<PrivilegeEntity> listPrivsByUserId(long userId);

    /**
     * 判断是否拥有该权限
     *
     * @param userId
     * @param privName
     * @return
     */
    boolean hasPrivilege(long userId, String privName);

    /**
     * 查询权限列表
     */
    Page<PrivilegeEntity> listPrivsByPage(int page, int size, SqlFilter sqlFilter);

    /**
     * 根据权限ID，更新对应操作的关系
     */
    void updateOperations(Long privilegeId, String[] operationIds);

    List<RoleEntity> listTargetRoles(Long privilegeId);

    List<RoleEntity> listSourceRoles(Long privilegeId);

    /**
     * 更新相关角色关系
     */
    void updateRoles(Long privilegeId, String[] roleIds);
}
