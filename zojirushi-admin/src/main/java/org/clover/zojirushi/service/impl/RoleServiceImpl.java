/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.clover.zojirushi.dao.PrivilegeDao;
import org.clover.zojirushi.dao.RoleDao;
import org.clover.zojirushi.entity.PrivilegeEntity;
import org.clover.zojirushi.entity.RoleEntity;
import org.clover.zojirushi.service.RoleService;
import org.clover.zojirushi.util.SqlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Pisces Lu
 * @version 1.0
 * @created 2017-5-8 18:29
 */
@Transactional
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleEntity, Long>
        implements RoleService {

    /**
     * @see RoleDao
     */
    @Autowired
    private RoleDao roleDao;

    /**
     * @see PrivilegeDao
     */
    @Autowired
    private PrivilegeDao privilegeDao;

    /**
     * 查找所有角色列表
     *
     * @return
     */
    @Override
    public Page<RoleEntity> listRolesByPage(int page, int size, SqlFilter sqlFilter) {
        return roleDao.listRolesByPage(page, size, sqlFilter);
    }

    /**
     * 根据用户ID来查找角色列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<RoleEntity> listRolesByUserId(long userId) {
        return roleDao.listRolesByUserId(userId);
    }

    /**
     * 判断是否拥有该角色
     *
     * @param userId
     * @param roleName
     * @return
     */
    @Override
    public boolean hasRole(long userId, String roleName) {
        List<RoleEntity> result = roleDao.listUserRoles(userId, roleName);
        return result.size() != 0;
    }

    @Override
    public List<PrivilegeEntity> listTargetPrivileges(Long roleId) {
        if (roleId == null) {
            return new ArrayList<>();
        } else {
            List<PrivilegeEntity> result = privilegeDao.listTargetPrivileges(roleId);
            return result;
        }
    }

    @Override
    public List<PrivilegeEntity> listSourcePrivileges(Long roleId) {
        if (roleId != null) {
            List<PrivilegeEntity> result = privilegeDao.listSourcePrivileges(roleId);
            return result;
        } else {
            List<PrivilegeEntity> result = privilegeDao.findByEnabled(1L);
            return result;
        }
    }

    @Override
    public void updatePrivileges(Long roleId, String[] privileges) {
        roleDao.updatePrivileges(roleId, privileges);
    }

    /**
     * 根据角色ID查找已选用户列表
     *
     * @param roleId
     * @return
     */
    public List<Map<String, Object>> listTargetUsers(Long roleId, SqlFilter sqlFilter) {
        return roleDao.listTargetUsers(roleId, sqlFilter);
    }

    /**
     * 根据角色ID查找未选用户列表
     *
     * @param roleId
     * @return
     */
    @Override
    public List<Map<String, Object>> listSourceUsers(Long roleId, Long institutionId, String key) {
        return roleDao.listSourceUsers(roleId, institutionId, key);
    }

    /**
     * 更新用户列表
     *
     * @param roleId
     * @param users
     */
    @Override
    public void updateUsers(Long roleId, String[] users) {
        roleDao.updateUsers(roleId, users);
    }

}
