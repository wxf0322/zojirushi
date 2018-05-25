/*
 * Copyright (c) 2005, 2017, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package org.clover.zojirushi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.clover.zojirushi.dao.PrivilegeDao;
import org.clover.zojirushi.dao.RoleDao;
import org.clover.zojirushi.dao.UserDao;
import org.clover.zojirushi.entity.PrivilegeEntity;
import org.clover.zojirushi.entity.RoleEntity;
import org.clover.zojirushi.service.PrivilegeService;
import org.clover.zojirushi.util.SqlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Pisces Lu
 * @version 1.0
 * @created 2017-5-8 17:34
 */
@Transactional
@Service
public class PrivilegeServiceImpl extends BaseServiceImpl<PrivilegeEntity, Long>
        implements PrivilegeService {

    /**
     * @see PrivilegeDao
     */
    @Autowired
    private PrivilegeDao privilegeDao;

    /**
     * @see RoleDao
     */
    @Autowired
    private RoleDao roleDao;

    /**
     * @see UserDao
     */
    @Autowired
    private UserDao userDao;

    /**
     * 根据app名称来获取权限列表
     *
     * @param appName
     * @return
     */
    @Override
    public List<PrivilegeEntity> listPrivsByAppName(String appName) {
        return privilegeDao.listPrivsByAppName(appName);
    }

    /**
     * 根据用户编码来获取权限列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<PrivilegeEntity> listPrivsByUserId(long userId) {
        return privilegeDao.listPrivsByUserId(userId);
    }

    /**
     * 判断是否拥有该权限
     *
     * @param userId
     * @param privName
     * @return
     */
    @Override
    public boolean hasPrivilege(long userId, String privName) {
        List<PrivilegeEntity> result = privilegeDao.listUserPrivileges(userId, privName);
        return result.size() != 0;
    }

    /**
     * 查询权限列表
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<PrivilegeEntity> listPrivsByPage(int page, int size, SqlFilter sqlFilter) {
        return privilegeDao.listPrivsByPage(page, size, sqlFilter);
    }


    @Override
    public void updateOperations(Long privilegeId, String[] operationIds) {
        privilegeDao.updateOperations(privilegeId, operationIds);
    }

    @Override
    public List<RoleEntity> listTargetRoles(Long privilegeId) {
        if (privilegeId == null) {
            return new ArrayList<>();
        } else {
            return roleDao.listTargetRolesByPrivId(privilegeId);
        }
    }

    @Override
    public List<RoleEntity> listSourceRoles(Long privilegeId) {
        if (privilegeId != null) {
            return roleDao.listSourceRolesByPrivId(privilegeId);
        } else {
            return roleDao.findByEnabled(1L);
        }
    }

    @Override
    public void updateRoles(Long privilegeId, String[] roleIds) {
        privilegeDao.updateRoles(privilegeId, roleIds);
    }

}
